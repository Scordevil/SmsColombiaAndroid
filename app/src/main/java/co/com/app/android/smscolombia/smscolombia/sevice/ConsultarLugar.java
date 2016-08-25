package co.com.app.android.smscolombia.smscolombia.sevice;

import java.util.List;

import co.com.app.android.smscolombia.smscolombia.models.Lugares_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ScorTech on 24/08/2016.
 */
public interface ConsultarLugar {
    @GET("/consultarLugar/")
    void consultarLugar(@Query("idLugar") int idLugar, Callback<Lugares_TO> callback);
}

