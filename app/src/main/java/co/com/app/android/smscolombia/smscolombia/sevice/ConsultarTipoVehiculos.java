package co.com.app.android.smscolombia.smscolombia.sevice;

import java.util.List;

import co.com.app.android.smscolombia.smscolombia.models.TipoVehiculo_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Scortech on 25/08/2016.
 */
public interface ConsultarTipoVehiculos {
    @GET("/consultarTipoVehiculos/")
    void consultarTipoVehiculos(Callback<List<TipoVehiculo_TO>> callback);
}
