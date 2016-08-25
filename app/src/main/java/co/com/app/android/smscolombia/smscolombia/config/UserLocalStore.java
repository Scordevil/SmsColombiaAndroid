package co.com.app.android.smscolombia.smscolombia.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import co.com.app.android.smscolombia.smscolombia.models.Empresa_TO;
import co.com.app.android.smscolombia.smscolombia.models.Lugares_TO;
import co.com.app.android.smscolombia.smscolombia.models.Rol_TO;
import co.com.app.android.smscolombia.smscolombia.models.Usuario_TO;


/**
 * Created by ContabilidadPC on 24/02/2016.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){

        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(Usuario_TO user){
        Log.i("Gustavo Store: ", user.toString());
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("nombre",user.getNombre());
        spEditor.putString("cc", user.getCc());
        spEditor.putString("telefono",user.getTelefono());
        spEditor.putString("movil", user.getMovil());
        spEditor.putString("correo", user.getCorreo());
        spEditor.putString("usuario", user.getUsuario());
        spEditor.putString("contrasena", user.getContrasena());
        spEditor.putInt("idUsuario", user.getIdUsuario());
        spEditor.putInt("idRol", user.getIdRol().getIdRol());
        spEditor.putInt("idEmpresa", user.getEmpresa().getIdEmpresa());
        spEditor.putInt("idLugar", user.getIdLugar().getIdLugares());

        spEditor.commit();
    }

    public Usuario_TO getLoggedInUser(){

        String nombre = userLocalDatabase.getString("nombre", "");
        String cc = userLocalDatabase.getString("cc","");
        String telefono = userLocalDatabase.getString("telefono","");
        String movil = userLocalDatabase.getString("movil","");
        String correo = userLocalDatabase.getString("correo", "");
        int idUsuario = userLocalDatabase.getInt("idUsuario", 0);
        String usuario = userLocalDatabase.getString("usuario", "");
        String contrasena = userLocalDatabase.getString("contrasena","");
        int idRol = userLocalDatabase.getInt("idRol", 0);
        int idEmpresa = userLocalDatabase.getInt("idEmpresa", 0);
        int idLugar = userLocalDatabase.getInt("idLugar", 0);



        Usuario_TO storedUser =
                new Usuario_TO(idUsuario, cc, telefono, movil, correo, usuario, contrasena, new Empresa_TO(idEmpresa), new Rol_TO(idRol), new Lugares_TO(idLugar), nombre);

        return storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn",false) == true){
            return true;
        }else{
            return false;
        }
    }

    public  void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
