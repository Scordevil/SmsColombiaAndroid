package co.com.app.android.smscolombia.smscolombia.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;
import co.com.app.android.smscolombia.smscolombia.config.md5;
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import co.com.app.android.smscolombia.smscolombia.sevice.ConsultarDatosSesionUsuario;
import co.com.app.android.smscolombia.smscolombia.sevice.ServerRequests;
import common.logger.Log;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InicioSesion extends AppCompatActivity  implements View.OnClickListener{

    TextView resultadoTextView;
    Button bLogin;
    EditText etEmail, etPassword;
    UserLocalStore userLocalStore;
    ServerRequests serverRequests = new ServerRequests();
    String ruta = serverRequests.BuscarRuta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

        resultadoTextView = (TextView) findViewById(R.id.resultado);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bLogin:

                String user = etEmail.getText().toString();
                String contrasena = etPassword.getText().toString();

                Usuario_TO usuario = new Usuario_TO(user,contrasena);


                if(!user.equals("")&&!contrasena.equals("")){


                    this.IniciarSersion(usuario);


                }else{
                    Toast toast1 = Toast.makeText(InicioSesion.this, "Debe ser Introducir Usuario y Contraseña", Toast.LENGTH_SHORT);

                    toast1.show();
                }

                break;


        }

    }

    private void IniciarSersion(Usuario_TO user){

        userLocalStore = new UserLocalStore(this);

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ruta).build();

        ConsultarDatosSesionUsuario servicio = restAdapter.create(ConsultarDatosSesionUsuario.class);

        servicio.consultarDatosSesionUsuario(user.getUsuario(),user.getContrasena(), new Callback<Usuario_TO>() {
            @Override
            public void success(Usuario_TO usuario, Response response) {

                if (usuario.getIdUsuario() > 0) {

                    String Contrasena = etPassword.getText().toString();

                    md5 md5 = new md5();

                    Contrasena = md5.getMD5(Contrasena);

                    if ( usuario.getContrasena().toString().equals(Contrasena)){

                        userLocalStore.storeUserData(usuario);

                        resultadoTextView.setText("Usuario correcto");


                        Toast toast1 = Toast.makeText(InicioSesion.this, "Iniciando sesion, por favor, espere", Toast.LENGTH_SHORT);

                        toast1.show();


                        logUserIn();

                    }

                } else {

                    Toast toast1 = Toast.makeText(InicioSesion.this, "Usuario o Contraseña Invalida", Toast.LENGTH_SHORT);

                    toast1.show();

                    etPassword.setText("");
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //     acceso = 0;
                resultadoTextView.setText("Error: " + retrofitError.getMessage());
            }
        });

    }

    private void logUserIn(){

        userLocalStore.setUserLoggedIn(true);

            startActivity(new Intent(this, Parte1.class));

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
