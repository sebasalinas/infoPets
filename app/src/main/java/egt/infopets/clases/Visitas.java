package egt.infopets.clases;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Soporte on 02-10-2017.
 */

public class Visitas implements Serializable {

    private String cod;
    private String descripcion;
    private Date fechaVisita;
    private Date fechaCreacion;

    public Visitas() {
    }

    public Visitas(String cod, String descripcion, Date fechaVisita, Date fechaCreacion) {
        this.cod = cod;
        this.descripcion = descripcion;
        this.fechaVisita = fechaVisita;
        this.fechaCreacion = fechaCreacion;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
