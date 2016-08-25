/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


import java.io.Serializable;

/**
 *
 * Objeto de negocios que modelo un Servicio
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

@SuppressWarnings("serial")
public class Servicio_TO implements Serializable {

    /**
     *
     * Columna idservicio
     */
    private int idServicio;
    
    /**
     *
     * Columna puntoiniciolong
     */
    private String puntoInicioLong;
    
    /**
     *
     * Columna puntoinicioLat
     */
    private String puntoInicioLat;
    
    /**
     *
     * Columna puntofinallong
     */
    private String puntoFinalLong;
    
    /**
     *
     * Columna puntofinallat
     */
    private String puntoFinalLat;
    
    /**
     *
     * Columna lugarinicio
     */
    private String lugarInicio;
    
    /**
     *
     * Columna lugardestino
     */
    private String lugarDestino;
    
    /**
     *
     * Columna placa
     */
    private String placa;
    
    /**
     *
     * Columna costo
     */
    private String costo;
    
    /**
     *
     * Columna idUsuario
     */
    private Usuario_TO usuario;
    
    /**
     *
     * Columna idEmpresa
     */
    private Empresa_TO empresa;
    
    //Constructores

    public Servicio_TO() {
    }

    public Servicio_TO(int idServicio, String puntoInicioLong, String puntoInicioLat, String puntoFinalLong, String puntoFinalLat, String lugarInicio, String lugarDestino, String placa, String costo, Usuario_TO usuario, Empresa_TO empresa) {
        this.idServicio = idServicio;
        this.puntoInicioLong = puntoInicioLong;
        this.puntoInicioLat = puntoInicioLat;
        this.puntoFinalLong = puntoFinalLong;
        this.puntoFinalLat = puntoFinalLat;
        this.lugarInicio = lugarInicio;
        this.lugarDestino = lugarDestino;
        this.placa = placa;
        this.costo = costo;
        this.usuario = usuario;
        this.empresa = empresa;
    }

    public Servicio_TO(String puntoInicioLong, String puntoInicioLat, String puntoFinalLong, String puntoFinalLat, String lugarInicio, String lugarDestino, String placa, String costo, Usuario_TO usuario, Empresa_TO empresa) {
        this.puntoInicioLong = puntoInicioLong;
        this.puntoInicioLat = puntoInicioLat;
        this.puntoFinalLong = puntoFinalLong;
        this.puntoFinalLat = puntoFinalLat;
        this.lugarInicio = lugarInicio;
        this.lugarDestino = lugarDestino;
        this.placa = placa;
        this.costo = costo;
        this.usuario = usuario;
        this.empresa = empresa;
    }
        
    
    //Getters and Setters

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getPuntoInicioLong() {
        return puntoInicioLong;
    }

    public void setPuntoInicioLong(String puntoInicioLong) {
        this.puntoInicioLong = puntoInicioLong;
    }

    public String getPuntoInicioLat() {
        return puntoInicioLat;
    }

    public void setPuntoInicioLat(String puntoInicioLat) {
        this.puntoInicioLat = puntoInicioLat;
    }

    public String getPuntoFinalLong() {
        return puntoFinalLong;
    }

    public void setPuntoFinalLong(String puntoFinalLong) {
        this.puntoFinalLong = puntoFinalLong;
    }

    public String getPuntoFinalLat() {
        return puntoFinalLat;
    }

    public void setPuntoFinalLat(String puntoFinalLat) {
        this.puntoFinalLat = puntoFinalLat;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public Usuario_TO getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario_TO usuario) {
        this.usuario = usuario;
    }

    public Empresa_TO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa_TO empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Servicio_TO{" + "idServicio=" + idServicio + ", puntoInicioLong=" + puntoInicioLong + ", puntoInicioLat=" + puntoInicioLat + ", puntoFinalLong=" + puntoFinalLong + ", puntoFinalLat=" + puntoFinalLat + ", lugarInicio=" + lugarInicio + ", lugarDestino=" + lugarDestino + ", placa=" + placa + ", costo=" + costo + ", usuario=" + usuario + ", empresa=" + empresa + '}';
    }
    
    
    

}
