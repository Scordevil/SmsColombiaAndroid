package co.com.app.android.smscolombia.smscolombia.sevice;

import co.com.app.android.smscolombia.smscolombia.models.Lugares_TO;
import co.com.app.android.smscolombia.smscolombia.models.Servicio_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ScorTech on 25/08/2016.
 */
public interface ConsultarServicio {
    @GET("/consultarServicio/")
    void consultarServicio(@Query("idServicio") int idServicio, Callback<Servicio_TO> callback);
}
