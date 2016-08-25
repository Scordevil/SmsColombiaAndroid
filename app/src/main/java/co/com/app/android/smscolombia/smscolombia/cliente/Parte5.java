package co.com.app.android.smscolombia.smscolombia.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;
import co.com.app.android.smscolombia.smscolombia.models.Servicio_TO;
import co.com.app.android.smscolombia.smscolombia.models.Tarifa_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.CalcularTarifa;
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

    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    String  cc, costo;
    Servicio_TO servicio = new Servicio_TO();
    int idTipoVehiculo = 0, kilometros = 0;


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

        bAceptar = (Button) findViewById(R.id.bAceptar);

        bAceptar.setOnClickListener(this);

        bCancelar = (Button) findViewById(R.id.bCancelar);

        bCancelar.setOnClickListener(this);

        //Llenar Formulario
        etInicio.setText(servicio.getLugarInicio());
        etDestino.setText(servicio.getLugarDestino());


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

        String puntoinicioLong = "";
        String puntofinalLong = "";
        String puntoinicioLat = "";
        String puntofinalLat = "";
        String lugarinicio = "";
        String lugardestino = "";
        String placa = "";
        costo = "";
        int idusuario = 0;
        int idempresa = 0;


        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        RegistrarServicio servicio = restAdapter.create(RegistrarServicio.class);

        servicio.registrarServicio(puntoinicioLong,puntofinalLong, puntoinicioLat, puntofinalLat, lugarinicio,
                lugardestino, placa, costo, idusuario, idempresa, new Callback<Servicio_TO>() {
                    @Override
                    public void success(Servicio_TO servicio, Response response) {

                        Intent intent = new Intent(Parte5.this, Recibo.class);
                        //     intent.putExtra("productos", (Serializable) productos);
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

        int cambio = Integer.parseInt(recibido) - Integer.parseInt(costo);
        etCambio.setText(cambio+"");

    }

    private void CalcularCosto(){

        int idLugar = userLocalStore.getLoggedInUser().getIdLugar().getIdLugares();

        Log.i("idlugar: ", idLugar + "");

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        CalcularTarifa servicio = restAdapter.create(CalcularTarifa.class);

        servicio.calcularTarifa(kilometros, idTipoVehiculo, new Callback<Tarifa_TO>() {
            @Override
            public void success(Tarifa_TO tarifa, Response response) {

                if (tarifa.getIdTarifa() > 0) {
                    //   tvConvenio.setText("Tiene convenio con " + usuario.getNombre());
                    Log.i("Descripcion: ", tarifa.getIdTarifa()+"");

                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.i("Error: ", retrofitError.getMessage());
            }
        });

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
