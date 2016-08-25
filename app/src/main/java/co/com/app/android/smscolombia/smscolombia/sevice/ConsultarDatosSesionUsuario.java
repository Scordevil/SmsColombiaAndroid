package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by VaioDevelopment on 20/06/2016.
 */
public interface ConsultarDatosSesionUsuario {
    @GET("/consultarDatosSesion/")
    void consultarDatosSesionUsuario(@Query("usu") String usu, @Query("contrasena") String contrasena,Callback<Usuario_TO> callback);
}