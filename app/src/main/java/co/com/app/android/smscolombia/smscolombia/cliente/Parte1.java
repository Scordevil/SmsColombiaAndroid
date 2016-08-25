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
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarCliente;
import co.com.app.android.smscolombia.smscolombia.sevice.ServerRequests;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Parte1 extends AppCompatActivity implements View.OnClickListener{

    Button bContinuar;
    EditText etCC;

    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bContinuar = (Button) findViewById(R.id.bContinuar);
        etCC = (EditText) findViewById(R.id.etCC);

        bContinuar.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bContinuar:

                Log.i("Gustavo ", etCC.getText().toString());
                String cc;
                if(!etCC.getText().toString().equals("")) {
                    cc = etCC.getText().toString();
                    ConsultarCC(cc);
                }else{
                    Toast toast1 =  Toast.makeText(Parte1.this, "Debe escribir alguna Identificación", Toast.LENGTH_SHORT);

                    toast1.show();
                }

                break;
        }

    }

    private void ConsultarCC(final String cc){


        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarCliente servicio = restAdapter.create(ConsultarCliente.class);

        Log.i("CC: ", cc);

        servicio.consultarCliente(cc, new Callback<Usuario_TO>() {
            @Override
            public void success(Usuario_TO usuario, Response response) {

                Log.i("Usuario: ",usuario.toString());

                if(usuario.getIdUsuario() != 0) {

                    if (!usuario.getCc().toString().equals("")) {

                        Log.i("cedula usuario: ", "diferente de vacio");

                        Intent intent = new Intent(Parte1.this, Parte4.class);
                        intent.putExtra("cc", usuario.getCc().toString());
                        //     intent.putExtra("productos", (Serializable) productos);
                        startActivity(intent);

                    }

                }else {

                    Log.i("cedula usuario: ", "igual de vacio");

                    if (userLocalStore.getUserLoggedIn() == true) {
                        Log.i("cedula usuario: ", "Asesor con sesion iniciada");
                        Intent intent = new Intent(Parte1.this, Parte2.class);
                        intent.putExtra("cc", cc);
                        startActivity(intent);
                    } else {
                        Log.i("cedula usuario: ", "no esta registrado y depende del asesor");
                        Toast toast1 = Toast.makeText(Parte1.this, "Debe ser registrado por un Asesor", Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //     acceso = 0;
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
