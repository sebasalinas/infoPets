package egt.infopets.clases;

import java.io.Serializable;

/**
 * Created by null on 04-10-2017.
 */

public class Raza implements Serializable {

    private int id;
    private String descripcion;
    private Especie especie;
    private boolean estado;

    public Raza() {
    }

    public Raza(int id, String descripcion, boolean estado, Especie especie) {

        this.id = id;
        this.descripcion = descripcion;
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

}
