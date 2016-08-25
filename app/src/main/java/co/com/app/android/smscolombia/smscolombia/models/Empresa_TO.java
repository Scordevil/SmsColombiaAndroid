/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


import java.io.Serializable;

/**
 *
 * Objeto de negocios que modelo una Empresa
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */
@SuppressWarnings("serial")
public class Empresa_TO implements Serializable {

    /**
     *
     * Columna idempresa
     */
    private int idEmpresa;

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
    
        /**
     *
     * Columna nit
     */
    private String nit;
    
        /**
     *
     * Columna telefono
     */
    private String telefono;
    
        /**
     *
     * Columna correo
     */
    private String correo;
    
        /**
     *
     * Columna paginaWeb
     */
    private String paginaWeb;
    
        /**
     *
     * Columna direccion
     */
    private String direccion;
    
        /**
     *
     * Columna idConvenio
     */
    private Convenio_TO convenio;
    
    //Constructores

    public Empresa_TO() {
    }

    public Empresa_TO(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    

    public Empresa_TO(int idEmpresa, String nombre, String descripcion, String nit, String telefono, String correo, String paginaWeb, String direccion, Convenio_TO convenio) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
        this.direccion = direccion;
        this.convenio = convenio;
    }
    
    //Getters and Setters

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Convenio_TO getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio_TO convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return "Empresa_TO{" + "idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nit=" + nit + ", telefono=" + telefono + ", correo=" + correo + ", paginaWeb=" + paginaWeb + ", direccion=" + direccion + ", convenio=" + convenio + '}';
    }
    
    
    

}
