package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.Servicio_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by VaioDevelopment on 18/08/2016.
 */
public interface RegistrarServicio {
    @GET("/registrarServicios/")
    void registrarServicio(@Query("puntoinicioLong") String puntoinicioLong,
                           @Query("puntofinalLong") String puntofinalLong, @Query("puntoinicioLat") String puntoinicioLat,
                           @Query("puntofinalLat") String puntofinalLat, @Query("lugarinicio") String lugarinicio,
                           @Query("lugardestino") String lugardestino, @Query("placa") String placa,
                           @Query("costo") String costo, @Query("idusuario") int idusuario,
                           @Query("idempresa") int idempresaa, Callback<Servicio_TO> callback);
}
