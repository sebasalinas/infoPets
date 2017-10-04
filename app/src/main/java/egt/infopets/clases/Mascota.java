package egt.infopets.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Mascota implements Serializable{

    private String id;
    private Duenio mNombre;
    private String fNacimiento;
    private Especie especie;
    private Raza raza;
    private String sexo;
    private String color;
    private ArrayList<Visitas> visita;

    public Mascota() {
        visita = new ArrayList<Visitas>();
    }

    public Mascota(String id, Duenio mNombre, String fNacimiento, Especie especie, Raza raza, String sexo, String color) {
        this.id = id;
        this.mNombre = mNombre;
        this.fNacimiento = fNacimiento;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        visita = new ArrayList<Visitas>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Duenio getmNombre() {
        return mNombre;
    }

    public void setmNombre(Duenio mNombre) {
        this.mNombre = mNombre;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
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

    public ArrayList<Visitas> getVisita() {
        return visita;
    }

    public void setVisita(ArrayList<Visitas> visita) {
        this.visita = visita;
    }
}
