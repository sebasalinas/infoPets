package egt.infopets.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Soporte on 03-10-2017.
 */

public class Especie implements Serializable {

    private int id;
    private boolean estado;
    private String specie;
   // private ArrayList<Raza> races;

    public Especie(){
        //this.races = new ArrayList<Raza>();
    }

    public Especie(int id, boolean estado, String specie) {

        this.id = id;
        this.estado = estado;
        this.specie = specie;
        //this.races = new ArrayList<Raza>();
    }

    public Especie(int id, boolean estado, String specie, ArrayList<Raza> races) {

        this.id = id;
        this.estado = estado;
        this.specie = specie;
       //this.races = races;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    //public ArrayList<Raza> getRaces() {
     //   return races;
    //}

    //public void setRaces(ArrayList<Raza> races) {
    //    this.races = races;
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
