package egt.infopets.db;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.clases.Especie;
import egt.infopets.clases.Raza;

/**
 * Created by Soporte on 04-10-2017.
 */

public class MantenedorRaza {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    public MantenedorRaza(Context context){
        this.context = context;
        tabla = "raza";
        columnas = new ArrayList<String>();
        columnas.add("id_Raza");
        columnas.add("descipcion");
        columnas.add("id_Especie");
        columnas.add("estado");
    }

    public void insert(Raza raza) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(raza);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Raza> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Raza> razas = new ArrayList<Raza>();
        if (resultado.moveToFirst()) {
            do {
                Raza raza = this.setRaza(resultado);
                razas.add(raza);
            } while (resultado.moveToNext());
        }
        conector.close();
        return razas;
    }

    private ArrayList<String> valores(Raza raza){
        ArrayList<String> valores = new ArrayList<String>();
        ArrayList<String> razas = new ArrayList<String>();
        valores.add(raza.getDescripcion());
        valores.add(raza.getEspecie());
        valores.add(Boolean.toString(raza.isEstado()));
        return valores;
    }

    public Raza getByCodigoTicket(String id) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id = " + id;
        Cursor resultado = this.conector.select(query);
        Raza raza = new Raza();
        if (resultado.moveToFirst()) {
            raza = this.setRaza(resultado);
        }
        conector.close();
        return raza;
    }

    public void update(Raza raza) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(raza);
        String condicion = "id = " + raza.getId();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int codEspecie) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "id = " + codEspecie;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Raza setRaza(Cursor resultado){
        Raza raza = new Raza();
        raza.setId(resultado.getInt(0));
        raza.setDescripcion(resultado.getString(1));
        raza.setEspecie((null));
        raza.setEstado(Boolean.valueOf(resultado.getString(3)));
        return raza;
    }
}
