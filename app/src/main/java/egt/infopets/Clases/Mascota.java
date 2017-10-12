package egt.infopets.Clases;

import java.io.Serializable;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Mascota implements Serializable{

    private int id;
    private String nombre;
    private String rut;
    private String fNacimiento;
    private int especie;
    private int raza;
    private String sexo;
    private String color;

    public Mascota() {
    }

    public Mascota(int id,String nombre,String rut, String fNacimiento, int especie, int raza, String sexo, String color) {
        this.setId(id);
        this.setNombre(nombre);
        this.setRut(rut);
        this.setfNacimiento(fNacimiento);
        this.setEspecie(especie);
        this.setRaza(raza);
        this.setSexo(sexo);
        this.setColor(color);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public int getRaza() {
        return raza;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
