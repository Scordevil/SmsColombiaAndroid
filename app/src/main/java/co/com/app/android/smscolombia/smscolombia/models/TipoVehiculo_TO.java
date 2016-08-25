package co.com.app.android.smscolombia.smscolombia.models;

/**
 *
 * Objeto de negocios que modelo un Tipo de Vehiculo
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

public class TipoVehiculo_TO {

    /**
     *
     * Columna idTipoVehiculo
     */
    private int idTipoVehiculo;

    /**
     *
     * Columna nombre
     */
    private String nombre;

    /**
     *
     * Columna descripcion
     */
    private String descripcion;

    //Constructores


    public TipoVehiculo_TO() {
    }

    public TipoVehiculo_TO(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public TipoVehiculo_TO(int idTipoVehiculo, String nombre, String descripcion) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getter and Setters

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoVehiculo_TO{" +
                "idTipoVehiculo=" + idTipoVehiculo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
