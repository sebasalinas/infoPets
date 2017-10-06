package egt.infopets.clases;

import java.io.Serializable;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Mascota implements Serializable{

    private int id;
    private String nombre;
    private int rut;
    private String fNacimiento;
    private int especie;
    private int raza;
    private String sexo;
    private String color;
    //private ArrayList<Visitas> visita;

    public Mascota() {
       // visita = new ArrayList<Visitas>();
    }

    public Mascota(int id,String nombre,int rut, String fNacimiento, int especie, int raza, String sexo, String color) {
        this.setId(id);
        this.setNombre(nombre);
        this.setRut(rut);
        this.setfNacimiento(fNacimiento);
        this.setEspecie(especie);
        this.setRaza(raza);
        this.setSexo(sexo);
        this.setColor(color);
        //visita = new ArrayList<Visitas>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
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

    /*public ArrayList<Visitas> getVisita() {
        return visita;
    }

    public void setVisita(ArrayList<Visitas> visita) {
        this.visita = visita;
    }*/
}
