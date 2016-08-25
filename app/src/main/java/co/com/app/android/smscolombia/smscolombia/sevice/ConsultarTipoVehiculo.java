package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.TipoVehiculo_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Scortech on 25/08/2016.
 */
public interface ConsultarTipoVehiculo {
    @GET("/consultarTipoVehiculo/")
    void consultarTipoVehiculo(@Query("idTipoVehiculo") int idTipoVehiculo, Callback<TipoVehiculo_TO> callback);
}
