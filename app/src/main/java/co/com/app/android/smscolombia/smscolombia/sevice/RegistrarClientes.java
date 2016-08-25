package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by VaioDevelopment on 18/08/2016.
 */
public interface RegistrarClientes {
    @GET("/registrarClientes/")
    void registrarClientes(@Query("cc") String cc,
                           @Query("telefono") String telefono,
                           @Query("movil") String movil,
                           @Query("correo") String correo,
                           @Query("usuario") String usuario,
                           @Query("contrasena") String contrasena,
                           @Query("idEmpresa") int idEmpresa,
                           @Query("idRol") int idRol,
                           @Query("idLugar") int idLugar,
                           @Query("nombre") String nombre, Callback<Usuario_TO> callback);
}
