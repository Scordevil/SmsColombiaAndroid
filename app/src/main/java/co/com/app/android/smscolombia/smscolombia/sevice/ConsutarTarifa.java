package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.Tarifa_TO;
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by VaioDevelopment on 18/08/2016.
 */
public interface ConsutarTarifa {
    @GET("/consultarTarifa")
    void consultarTarifa(@Query("idTipoVehiculo") int idTipoVehiculo, Callback<Tarifa_TO> callback);
}
