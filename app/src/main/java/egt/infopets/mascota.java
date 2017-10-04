package egt.infopets;

import java.io.Serializable;

/**
 * Created by Soporte on 02-10-2017.
 */

public class mascota implements Serializable{

    private String id;
    private String mNombre;
    private String fNacimiento;
    private String especie;
    private String raza;
    private String sexo;
    private String color;
    private visitas[] diagnostico;
    private String nombre;
    private String direccion;
    private String numero;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
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

    public visitas getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(visitas diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
