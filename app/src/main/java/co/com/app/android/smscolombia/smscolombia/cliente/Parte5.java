package co.com.app.android.smscolombia.smscolombia.cliente;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;
import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import co.com.app.android.smscolombia.smscolombia.models.Servicio_TO;
import co.com.app.android.smscolombia.smscolombia.models.Tarifa_TO;
import co.com.app.android.smscolombia.smscolombia.models.TipoVehiculo_TO;
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.CalcularTarifa;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarTipoVehiculos;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsutarTarifa;
import co.com.app.android.smscolombia.smscolombia.sevice.RegistrarServicio;
import co.com.app.android.smscolombia.smscolombia.sevice.ServerRequests;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Parte5 extends AppCompatActivity implements View.OnClickListener {

    UserLocalStore userLocalStore;

    Button bAceptar, bCancelar;
    EditText etInicio, etDestino, etCosto, etRecibido, etCambio, etPlaca;
    Spinner spTVehiculo;

    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    String  cc, costo, nombreCliente;
    Servicio_TO servicio = new Servicio_TO();
    int idTipoVehiculo = 0, kilometros = 0, idempresa = 0;

    List<String> tipoVehiculo = new ArrayList<String>();
    private AlertDialog.Builder dialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLocalStore = new UserLocalStore(this);

        cc = getIntent().getExtras().getString("cc");
        kilometros = getIntent().getExtras().getInt("kilometros");
        servicio = (Servicio_TO)getIntent().getExtras().getSerializable("servicio");

        etInicio = (EditText) findViewById(R.id.etInicio);
        etDestino = (EditText) findViewById(R.id.etDestino);
        etCosto = (EditText) findViewById(R.id.etCosto);
        etRecibido = (EditText) findViewById(R.id.etRecibido);
        etCambio = (EditText) findViewById(R.id.etCambio);
        etPlaca = (EditText) findViewById(R.id.etPlaca);
        spTVehiculo = (Spinner) findViewById(R.id.spTVehiculo);

        bAceptar = (Button) findViewById(R.id.bAceptar);

        bAceptar.setOnClickListener(this);

        bCancelar = (Button) findViewById(R.id.bCancelar);

        bCancelar.setOnClickListener(this);

        //Llenar Formulario
        etInicio.setText(servicio.getLugarInicio());
        etDestino.setText(servicio.getLugarDestino());

        ConsultarTipoVehiculos();

        spTVehiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String strServicio = (adapterView.getItemAtPosition(position)).toString();

                if (!strServicio.equals("Seleccione")) {

                    String[] Hora = strServicio.split(" - ");

                    idTipoVehiculo = Integer.parseInt(Hora[0].toString());

                    if((etDestino.getText().toString().equals("Aeropuerto Internacional El Dorado - Calle 26, Bogotá, Colombia"))) {
                        ConsultarTarifa();
                    }else {
                        CalcularCosto(idTipoVehiculo);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nothing
            }
        });

        etRecibido.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String recibido = "";
                if(etRecibido.getText().toString().equals("")) {
                    etRecibido.setText("0");
                    recibido = etRecibido.getText().toString();
                }else{
                    recibido = etRecibido.getText().toString();
                }
                CalcularCambio(recibido);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bAceptar:

                if(!etPlaca.getText().toString().equals("")) {

                    if(!etRecibido.getText().toString().equals("")) {

                        RegistrarServicio();
                    }else{
                        Toast.makeText(this, "Debe ingresar el monto recibido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Debe ingresar la Placa", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.bCancelar:

                Intent intent1 = new Intent(Parte5.this, MainActivity.class);
                //       intent.putExtra("pedido", pedido);
                //     intent.putExtra("productos", (Serializable) productos);
                startActivity(intent1);
                break;
        }

    }

    private void RegistrarServicio(){

        String puntoinicioLong = servicio.getPuntoInicioLong();
        String puntofinalLong = servicio.getPuntoFinalLong();
        String puntoinicioLat = servicio.getPuntoInicioLat();
        String puntofinalLat = servicio.getPuntoFinalLat();
        String lugarinicio = servicio.getLugarInicio();
        String lugardestino = servicio.getLugarDestino();
        String placa = etPlaca.getText().toString();
        int idusuario = userLocalStore.getLoggedInUser().getIdUsuario();

        servicio.setCosto(costo);
        servicio.setPlaca(placa);
        Empresa_TO emp = new Empresa_TO(idempresa);
        servicio.setEmpresa(emp);
        Usuario_TO usu = new Usuario_TO("", cc, nombreCliente);
        servicio.setUsuario(usu);

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        final RegistrarServicio servicios = restAdapter.create(RegistrarServicio.class);

        servicios.registrarServicio(puntoinicioLong, puntofinalLong, puntoinicioLat, puntofinalLat, lugarinicio,
                lugardestino, placa, costo, idusuario, idempresa,nombreCliente, cc, new Callback<Servicio_TO>() {
                    @Override
                    public void success(Servicio_TO service, Response response) {

                        Intent intent = new Intent(Parte5.this, Recibo.class);
                        intent.putExtra("servicio", servicio);
                        startActivity(intent);

                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        //     acceso = 0;
                        Log.i("Error: ", retrofitError.getMessage());
                    }
                });

    }

    private void CalcularCambio(String recibido){

        int cambio = 0;

            if (Integer.parseInt(costo) <= Integer.parseInt(recibido)) {
                cambio = Integer.parseInt(recibido) - Integer.parseInt(costo);
                etCambio.setText("$"+cambio);
            } else {
                etCambio.setText("");
            }


    }

    private void CalcularCosto(final int idTipoVehiculo){

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        CalcularTarifa servicio = restAdapter.create(CalcularTarifa.class);

        Log.i("CalcularCosto: ", idTipoVehiculo + "y" + kilometros );

        servicio.calcularTarifa(kilometros, idTipoVehiculo, new Callback<Tarifa_TO>() {
            @Override
            public void success(Tarifa_TO tarifa, Response response) {

                    etCosto.setText("$" + tarifa.getCosto() + "");
                    costo = tarifa.getCosto() + "";
                    Log.i("destino1: ",etDestino.getText().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.i("Error: ", retrofitError.getMessage());
            }
        });

    }

    private void ConsultarTarifa(){

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsutarTarifa servicio = restAdapter.create(ConsutarTarifa.class);

        servicio.consultarTarifa(idTipoVehiculo, new Callback<Tarifa_TO>() {
            @Override
            public void success(Tarifa_TO tarifa, Response response) {

                Log.i("tarifa: ", tarifa.toString());

                if (idTipoVehiculo == 1) {
                    etCosto.setText("$" + tarifa.getServicioAeropuerto() + "");
                    costo = tarifa.getServicioAeropuerto() + "";
                } else {
                    if (idTipoVehiculo == 2) {
                        int newCosto = tarifa.getCosto() + Integer.parseInt(tarifa.getServicioAeropuerto());
                        etCosto.setText("$" + newCosto + "");
                        costo = newCosto + "";
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.i("Error: ", retrofitError.getMessage());
            }
        });

    }

    private void ConsultarTipoVehiculos(){

        tipoVehiculo.clear();

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarTipoVehiculos servicio = restAdapter.create(ConsultarTipoVehiculos.class);

        servicio.consultarTipoVehiculos( new Callback<List<TipoVehiculo_TO>>() {
            @Override
            public void success(List<TipoVehiculo_TO> tVehiculos, Response response) {

                Log.i("tVehiculo: ", tVehiculos.toString());

                if (tVehiculos.size() != 0 && tVehiculos != null) {
                    tipoVehiculo.add("Seleccione");
                    for (int i = 0; i < tVehiculos.size(); i++) {
                        tipoVehiculo.add(tVehiculos.get(i).getIdTipoVehiculo() + " - " + tVehiculos.get(i).getNombre());
                    }
                    LlenarTipoVehiculo();
                } else {
                    tipoVehiculo.clear();
                    tipoVehiculo.add("Seleccione");

                    LlenarTipoVehiculo();
                }


            }

            @Override
            public void failure(RetrofitError retrofitError) {

                tipoVehiculo.clear();
                tipoVehiculo.add("Seleccione");

                LlenarTipoVehiculo();
                //     acceso = 0;
                Log.i("Error: ", retrofitError.getMessage());
            }
        });

    }

    private void LlenarTipoVehiculo(){

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoVehiculo);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTVehiculo.setAdapter(adapter1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userLocalStore.getUserLoggedIn() == true) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_main2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_Sesion) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            startActivity(new Intent(this, MainActivity.class));
        }

        if (id == R.id.iniciar_Sesion) {

            startActivity(new Intent(this, InicioSesion.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
