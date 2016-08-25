/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


import java.io.Serializable;

/**
 *
 * Objeto de negocios que modelo un usuario
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

@SuppressWarnings("serial")
public class Usuario_TO implements Serializable {

    /**
     *
     * Columna idUsuario
     */
    private int idUsuario;

    /**
     *
     * Columna cc
     */
    private String cc;

    /**
     *
     * Columna telefono
     */
    private String telefono;

    /**
     *
     * Columna movil
     */
    private String movil;

    /**
     *
     * Columna correo
     */
    private String correo;

    /**
     *
     * Columna usuario
     */
    private String usuario;

    /**
     *
     * Columna contrasena
     */
    private String contrasena;

    /**
     *
     * Columna idEmpresa
     */
    private Empresa_TO empresa;

    /**
     *
     * Columna idRol
     */
    private Rol_TO idRol;

    /**
     *
     * Columna idLugar
     */
    private Lugares_TO idLugar;
    
    
    /**
     *
     * Columna nombre
     */
    private String nombre;
    
    //Constructores

    public Usuario_TO() {
    }



    public Usuario_TO(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario_TO(String var, String cc, String nombre) {
        this.cc = cc;
        this.nombre = nombre;
    }

    public Usuario_TO(int idUsuario, String cc, String telefono, String movil, String correo, String usuario, String contrasena, Empresa_TO empresa, Rol_TO idRol, String nombre) {
        this.idUsuario = idUsuario;
        this.cc = cc;
        this.telefono = telefono;
        this.movil = movil;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.empresa = empresa;
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Usuario_TO(int idUsuario, String cc, String telefono, String movil, String correo, String usuario, String contrasena, Empresa_TO empresa, Rol_TO idRol, Lugares_TO idLugar, String nombre) {
        this.idUsuario = idUsuario;
        this.cc = cc;
        this.telefono = telefono;
        this.movil = movil;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.empresa = empresa;
        this.idRol = idRol;
        this.idLugar = idLugar;
        this.nombre = nombre;
    }

    public Usuario_TO(String cc, String telefono, String movil, String correo, String usuario, String contrasena, Empresa_TO empresa, Rol_TO idRol, String nombre) {
        this.cc = cc;
        this.telefono = telefono;
        this.movil = movil;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.empresa = empresa;
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Usuario_TO(int idUsuario, String cc) {
        this.idUsuario = idUsuario;
        this.cc = cc;
    }

    public Usuario_TO(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
    
    
    //Getters and Setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Empresa_TO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa_TO empresa) {
        this.empresa = empresa;
    }

    public Rol_TO getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol_TO idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lugares_TO getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Lugares_TO idLugar) {
        this.idLugar = idLugar;
    }

    @Override
    public String toString() {
        return "Usuario_TO{" +
                "idUsuario=" + idUsuario +
                ", cc='" + cc + '\'' +
                ", telefono='" + telefono + '\'' +
                ", movil='" + movil + '\'' +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", empresa=" + empresa +
                ", idRol=" + idRol +
                ", idLugar=" + idLugar +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
