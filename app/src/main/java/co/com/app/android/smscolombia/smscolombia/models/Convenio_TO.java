/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


/**
 *
 * Objeto de negocios que modelo un Convenio
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

public class Convenio_TO {

    /**
     *
     * Columna idconvenio
     */
    private int idConvenio;

    /**
     *
     * Columna nombre
     */
    private String nombre;

    /**
     *
     * Columna descuento
     */
    private int descuento;
    
    //Constructores

    public Convenio_TO() {
    }

    public Convenio_TO(int idConvenio, String nombre, int descuento) {
        this.idConvenio = idConvenio;
        this.nombre = nombre;
        this.descuento = descuento;
    }
    
    //Setters and Getters

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Convenio_TO{" + "idConvenio=" + idConvenio + ", nombre=" + nombre + ", descuento=" + descuento + '}';
    }
    
    
    
}
