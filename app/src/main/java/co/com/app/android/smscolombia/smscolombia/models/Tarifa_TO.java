/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;


/**
 *
 * Objeto de negocios que modelo una Tarifa
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

public class Tarifa_TO {

    /**
     *
     * Columna idtarifa
     */
    private int idTarifa;

    /**
     *
     * Columna minima
     */
    private String minima;

    /**
     *
     * Columna kilometrosmenorque
     */
    private String kilometrosMenorQue;

    /**
     *
     * Columna kilometrosMayorQue
     */
    private String kilometrosMayorQue;

    /**
     *
     * Columna minutos
     */
    private String minutos;

    /**
     *
     * Columna servicioHora
     */
    private String servicioPorHora;

    /**
     *
     * Columna aeropuerto
     */
    private String servicioAeropuerto;

    /**
     *
     * Columna arranqueobanderazopico
     */
    private String arranqueOBanderazoPico;

    /**
     *
     * Columna arranqueobanderazovalle
     */
    private String arranqueOBanderazoValle;

    /**
     *
     * Columna tipoVehiculo
     */
    private Vehiculo_TO vehiculo;

    /**
     *
     * Columna costo
     */
    private int costo;

    //Constructores
    public Tarifa_TO() {
    }

    public Tarifa_TO(int idTarifa, String minima, String kilometrosMenorQue, String kilometrosMayorQue, String minutos, String servicioPorHora, String servicioAeropuerto, String arranqueOBanderazoPico, String arranqueOBanderazoValle, Vehiculo_TO vehiculo, int costo) {
        this.idTarifa = idTarifa;
        this.minima = minima;
        this.kilometrosMenorQue = kilometrosMenorQue;
        this.kilometrosMayorQue = kilometrosMayorQue;
        this.minutos = minutos;
        this.servicioPorHora = servicioPorHora;
        this.servicioAeropuerto = servicioAeropuerto;
        this.arranqueOBanderazoPico = arranqueOBanderazoPico;
        this.arranqueOBanderazoValle = arranqueOBanderazoValle;
        this.vehiculo = vehiculo;
        this.costo = costo;
    }

    //Getters and Setters
    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

    public String getKilometrosMenorQue() {
        return kilometrosMenorQue;
    }

    public void setKilometrosMenorQue(String kilometrosMenorQue) {
        this.kilometrosMenorQue = kilometrosMenorQue;
    }

    public String getKilometrosMayorQue() {
        return kilometrosMayorQue;
    }

    public void setKilometrosMayorQue(String kilometrosMayorQue) {
        this.kilometrosMayorQue = kilometrosMayorQue;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getServicioPorHora() {
        return servicioPorHora;
    }

    public void setServicioPorHora(String servicioPorHora) {
        this.servicioPorHora = servicioPorHora;
    }

    public String getServicioAeropuerto() {
        return servicioAeropuerto;
    }

    public void setServicioAeropuerto(String servicioAeropuerto) {
        this.servicioAeropuerto = servicioAeropuerto;
    }

    public String getArranqueOBanderazoPico() {
        return arranqueOBanderazoPico;
    }

    public void setArranqueOBanderazoPico(String arranqueOBanderazoPico) {
        this.arranqueOBanderazoPico = arranqueOBanderazoPico;
    }

    public String getArranqueOBanderazoValle() {
        return arranqueOBanderazoValle;
    }

    public void setArranqueOBanderazoValle(String arranqueOBanderazoValle) {
        this.arranqueOBanderazoValle = arranqueOBanderazoValle;
    }

    public Vehiculo_TO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo_TO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Tarifa_TO{" +
                "idTarifa=" + idTarifa +
                ", minima='" + minima + '\'' +
                ", kilometrosMenorQue='" + kilometrosMenorQue + '\'' +
                ", kilometrosMayorQue='" + kilometrosMayorQue + '\'' +
                ", minutos='" + minutos + '\'' +
                ", servicioPorHora='" + servicioPorHora + '\'' +
                ", servicioAeropuerto='" + servicioAeropuerto + '\'' +
                ", arranqueOBanderazoPico='" + arranqueOBanderazoPico + '\'' +
                ", arranqueOBanderazoValle='" + arranqueOBanderazoValle + '\'' +
                ", vehiculo=" + vehiculo +
                ", costo=" + costo +
                '}';
    }
}
