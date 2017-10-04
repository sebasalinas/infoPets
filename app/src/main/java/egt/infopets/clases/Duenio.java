package egt.infopets.clases;

import java.io.Serializable;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Duenio implements Serializable{

    private String rut;
    private String nombre;
    private String numero;
    private String direccion;

    public Duenio() {
    }

    public Duenio(String rut, String nombre, String numero, String direccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.numero = numero;
        this.direccion = direccion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
