package co.com.app.android.smscolombia.smscolombia.sevice;

/**
 * Created by VaioDevelopment on 29/02/2016.
 */
public class ServerRequests {

    private String ruta;

    public ServerRequests(String ruta) {
        this.ruta = ruta;
    }

    public ServerRequests() {
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "ServerRequests{" +
                "ruta='" + ruta + '\'' +
                '}';
    }

    public String BuscarRuta(){

      //  ruta = "http://192.168.0.128:8084/SMS_rentas_servicio/resources";
   //     ruta = "http://192.168.0.133:8084/SMS_rentas_servicio/resources";
    //    ruta = "http://10.0.2.2:8084/LavappService/webresources";
 //       ruta = "http://192.168.100.251:8080/LavappService/webresources"; //publica

     //   ruta = "http://190.146.144.81:5432/LavappService/webresources"; //publica


       // ruta = "http://10.0.2.2:8084/SMS_colombia_service/webresources"; //publica

        ruta = "http://192.168.100.74:8084/SMS_colombia_service/webresources"; //publica

      //  ruta = "http://10.127.127.1:8084/SMS_colombia_service/webresources"; //publica

      //  ruta = "http://192.168.100.74:8084/SMS_colombia_service/webresources"; //publica

     //  ruta = "http://www.app.smsrenta.com:8080/SMS_rentas_servicio/resources";
        return ruta;
    }
}
