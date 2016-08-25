package co.com.app.android.smscolombia.smscolombia.sevice;

import java.util.List;

import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by VaioDevelopment on 18/08/2016.
 */
public interface ConsultarEmpresa {
    @GET("/consultarEmpresa/")
    void consultarEmpresa(@Query("idEmpresa") int idEmpresa, Callback<Empresa_TO> callback);
}
