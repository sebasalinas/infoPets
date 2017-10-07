package egt.infopets.clases;

import java.io.Serializable;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Visitas implements Serializable {

    private int cod;
    private String descripcion;
    private String fechaVisita;
    private int mascota;

    public Visitas() {
    }

    public Visitas(int cod, String descripcion, String fechaVisita, int mascota) {
        this.setCod(cod);
        this.setDescripcion(descripcion);
        this.setFechaVisita(fechaVisita);
        this.setMascota(mascota);
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public int getMascota() {
        return mascota;
    }

    public void setMascota(int mascota) {
        this.mascota = mascota;
    }
}
