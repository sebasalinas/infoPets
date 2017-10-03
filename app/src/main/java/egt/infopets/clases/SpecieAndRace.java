package egt.infopets.clases;

import java.io.Serializable;

/**
 * Created by Soporte on 03-10-2017.
 */

public class SpecieAndRace implements Serializable {

    private String id;
    private boolean estado;
    private String specie;
    private String race;


    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
