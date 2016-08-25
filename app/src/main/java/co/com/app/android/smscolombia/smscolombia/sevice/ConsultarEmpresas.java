package co.com.app.android.smscolombia.smscolombia.sevice;

import java.util.List;

import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by VaioDevelopment on 18/08/2016.
 */
public interface ConsultarEmpresas {
    @GET("/consultarEmpresas/")
    void consultarEmpresas(Callback<List<Empresa_TO>> callback);
}
