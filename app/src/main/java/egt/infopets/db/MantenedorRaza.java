package egt.infopets.db;


import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

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
        columnas.add("ID");
        columnas.add("Descipcion");
        columnas.add("Especie");
        columnas.add("Estado");
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
                //Raza raza = this.setRaza(resultado);
                //razas.add(raza);
            } while (resultado.moveToNext());
        }
        conector.close();
        return razas;
    }

    private ArrayList<String> valores(Raza raza){
        ArrayList<String> valores = new ArrayList<String>();
        //valores.add(raza.getId());
        valores.add(Boolean.toString(raza.isEstado()));
        //valores.add(raza.getSpecie());
        return valores;
    }

    public Raza getByCodigoTicket(String id) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id = " + id;
        Cursor resultado = this.conector.select(query);
        Raza raza = new Raza();
        if (resultado.moveToFirst()) {
            //raza = this.setRaza(resultado);
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

    private Raza setRaza(Raza resultado){
        Raza raza = new Raza();
        //raza.setDescripcion(resultado.getString(1));
        //raza.setEstado(Boolean.valueOf(resultado.getDescripcion()));
        //raza.setEstado(Boolean.valueOf(resultado.getString(2)));
       // raza.setDescripcion(resultado.getString(2));
        return raza;
    }
}
