package co.com.app.android.smscolombia.smscolombia.sevice;

import java.util.List;

import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import co.com.app.android.smscolombia.smscolombia.models.Lugares_TO;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Jose on 23/08/2016.
 */
public interface ConsultarAeropuertos {
    @GET("/consultarAeropuertos/")
    void consultarAeropuertos(Callback<List<Lugares_TO>> callback);
}
