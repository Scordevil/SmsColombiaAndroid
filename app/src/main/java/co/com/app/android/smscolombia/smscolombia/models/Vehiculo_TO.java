/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.app.android.smscolombia.smscolombia.models;

/**
 *
 * Objeto de negocios que modelo un Tipode Vehiculo
 *
 * Tabla Relacionada smscolombia
 *
 * @author ScorTech
 */

public class Vehiculo_TO {
    
    /**
     *
     * Columna idTipoVehiculo
     */
    private int idVehiculo;

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
     * Columna modelo
     */
    private String modelo;
    
    /**
     *
     * Columna marca
     */
    private String marca;
    
    /**
     *
     * Columna placa
     */
    private String placa;
    
    //Constructores

    public Vehiculo_TO() {
    }

    public Vehiculo_TO(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    public Vehiculo_TO(int idVehiculo, String nombre, String descripcion, String modelo, String marca, String placa) {
        this.idVehiculo = idVehiculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
    }
    
    //Setters and Getters

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Vehiculo_TO{" + "idVehiculo=" + idVehiculo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", modelo=" + modelo + ", marca=" + marca + ", placa=" + placa + '}';
    }
    
    
    
    
    
}
