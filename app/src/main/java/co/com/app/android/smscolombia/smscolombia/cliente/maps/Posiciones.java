package co.com.app.android.smscolombia.smscolombia.cliente.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Posiciones {   //Nueva clase Posiciones
    //Constante de Posición del marcador
    public static final LatLng SAGRADA_FAMILIA = new LatLng(41.40347, 2.17432);
    //Constante de Opciones de Polilínea.
    public static final PolylineOptions POLILINEA = new PolylineOptions()
            .add(new LatLng(10.48072, -66.90349),
            (new LatLng(10.48065, -66.90341)),
            (new LatLng(10.48013, -66.90283)),
            (new LatLng(10.47993, -66.90261)),
            (new LatLng(10.47965, -66.9023)),
            (new LatLng(10.47934, -66.90194)),
            (new LatLng(10.47894, -66.90149)),
            (new LatLng(10.47887, -66.90141)),
            (new LatLng(10.47886, -66.9014)),
            (new LatLng(10.47885, -66.90139)),
            (new LatLng(10.47848, -66.90097)));




}