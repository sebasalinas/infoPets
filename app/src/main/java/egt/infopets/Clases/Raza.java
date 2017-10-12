package egt.infopets.Clases;

import java.io.Serializable;

/**
 * Created by null on 04-10-2017.
 */

public class Raza implements Serializable {

    private int id;
    private String descripcion;
    private int especie;
    private boolean estado;

    public Raza() {
    }

    public Raza(int id, String descripcion, boolean estado, int especie) {

        this.id = id;
        this.descripcion = descripcion;
        this.setEspecie(especie);
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }
}
