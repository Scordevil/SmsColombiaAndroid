/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


/**
 *
 * Objeto de negocios que modelo un Rol
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */
public class Rol_TO {

    /**
     *
     * Columna idrol
     */
    private int idRol;

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
    public Rol_TO() {
    }

    public Rol_TO(int idRol) {
        this.idRol = idRol;
    }
    
    

    public Rol_TO(int idRol, String nombre, String descripcion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getters and Setters
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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
        return "Rol_TO{" + "idRol=" + idRol + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
