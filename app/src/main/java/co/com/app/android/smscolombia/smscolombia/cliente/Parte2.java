package co.com.app.android.smscolombia.smscolombia.cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;
import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarEmpresa;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarEmpresas;
import co.com.app.android.smscolombia.smscolombia.sevice.RegistrarClientes;
import co.com.app.android.smscolombia.smscolombia.sevice.ServerRequests;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Parte2 extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog.Builder dialogBuilder;
    Button bRegistrar;
    EditText etCC, etNombre, etApellidos, etTelefono, etEmail, etMovil;
    String cc;
    TextView tvConvenio;
    int idEmpresa = 0;
    int idLugar = 0;
    int idRol = 2 ; //Cliente
    List<String> empresa = new ArrayList<String>();

    UserLocalStore userLocalStore;


    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convenio();

            }
        });


        userLocalStore = new UserLocalStore(this);

        bRegistrar = (Button) findViewById(R.id.bRegistrar);
        etCC = (EditText) findViewById(R.id.etCC);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etMovil = (EditText) findViewById(R.id.etMovil);
        etEmail = (EditText) findViewById(R.id.etEmail);
        tvConvenio = (TextView) findViewById(R.id.tvConvenio);

        cc = getIntent().getExtras().getString("cc");

        etCC.setText(cc.toString());

        bRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bRegistrar:

                if(!etCC.getText().toString().equals("")&&!etNombre.getText().toString().equals("")&&!etApellidos.getText().toString().equals("")&&!etMovil.getText().toString().equals("")) {

                    RegistrarUsuario();

                }else{
                    Toast toast1 = Toast.makeText(Parte2.this, "No puede tener campos obligatorios vacios", Toast.LENGTH_SHORT);

                    toast1.show();
                }

                break;
        }

    }

    private void Convenio(){

        final Spinner spEmpresas = new Spinner(this);

        //variables
        dialogBuilder = new AlertDialog.Builder(this);
        ConsultarEmpresas(spEmpresas);


        //Proccess
        dialogBuilder.setTitle("Convenio");
        dialogBuilder.setMessage("Seleccione la empresa a la que pertenece");
        dialogBuilder.setView(spEmpresas);
        dialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String Tempresa = spEmpresas.getSelectedItem().toString();

                String[] emp = Tempresa.toString().split(" - ");

                idEmpresa = Integer.parseInt(emp[0].toString());

                ConsultarEmpresa(idEmpresa);

                Toast.makeText(getApplicationContext(), "Empresa seleccionada", Toast.LENGTH_SHORT);
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Empresa no seleccionada", Toast.LENGTH_SHORT).show();
            }
        });

        //Output
        AlertDialog dialogCantidadPrendas = dialogBuilder.create();
        dialogCantidadPrendas.show();
    }

    private void RegistrarUsuario(){

        String telefono = etTelefono.getText().toString();
        String movil = etMovil.getText().toString();
        String correo = etEmail.getText().toString();
        String usuario = "";
        String contrasena = "";
        String nombre = etNombre.getText().toString() + " " + etApellidos.getText().toString();


        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        RegistrarClientes servicio = restAdapter.create(RegistrarClientes.class);

        servicio.registrarClientes(cc, telefono, movil, correo, usuario, contrasena, idEmpresa, idRol,idLugar, nombre, new Callback<Usuario_TO>() {
            @Override
            public void success(Usuario_TO usuario, Response response) {

                Intent intent = new Intent(Parte2.this, Parte4.class);
                intent.putExtra("cc", cc);
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

    private void ConsultarEmpresas(final Spinner spEmpresas){

        empresa.clear();

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarEmpresas servicio = restAdapter.create(ConsultarEmpresas.class);

        servicio.consultarEmpresas(new Callback<List<Empresa_TO>>() {
            @Override
            public void success(List<Empresa_TO> empresas, Response response) {

                if (empresas.size() != 0 && empresas != null) {
                    empresa.add("Seleccione");
                    for (int i = 0; i < empresas.size(); i++) {
                        empresa.add(empresas.get(i).getIdEmpresa() + " - " + empresas.get(i).getNombre());
                    }
                    Log.i("Gustavo", "llenar Barrios1: " + empresa);
                    LlenarEmpresas(spEmpresas);
                } else {
                    empresa.clear();
                    empresa.add("Seleccione");

                    Log.i("Gustavo", "llenar Barrios2: " + empresa);
                    LlenarEmpresas(spEmpresas);
                }


            }

            @Override
            public void failure(RetrofitError retrofitError) {

                empresa.clear();
                empresa.add("Seleccione");

                Log.i("Gustavo", "llenar Barrios3: " + empresa);
                LlenarEmpresas(spEmpresas);
                //     acceso = 0;
                Log.i("Error: ", retrofitError.getMessage());
            }
        });

    }

    private void LlenarEmpresas(Spinner spEmpresas){

        Log.i("Gustavo", "llenar Barrios: " + empresa);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, empresa);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEmpresas.setAdapter(adapter1);

    }

    private void ConsultarEmpresa( int idEmpresa){

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarEmpresa servicio = restAdapter.create(ConsultarEmpresa.class);

        servicio.consultarEmpresa(idEmpresa, new Callback<Empresa_TO>() {
            @Override
            public void success(Empresa_TO empresa, Response response) {

                if (empresa.getIdEmpresa() > 0) {
                    tvConvenio.setText("Tiene convenio con " + empresa.getNombre());
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
