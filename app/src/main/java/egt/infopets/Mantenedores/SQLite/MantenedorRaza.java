package egt.infopets.Mantenedores.SQLite;


import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.Clases.Raza;

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
        columnas.add("descripcion");
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
        valores.add(raza.getDescripcion());
        valores.add(Integer.toString(raza.getEspecie()));
        valores.add(Boolean.toString(raza.isEstado()));
        return valores;
    }

    public Raza getByCodigo(int id) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id_Raza = " + id;
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
        String condicion = "id_Raza = " + raza.getId();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int cod) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "id_Raza = " + cod;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Raza setRaza(Cursor resultado){
        Raza raza = new Raza();
        raza.setId(resultado.getInt(0));
        raza.setDescripcion(resultado.getString(1));
        raza.setEspecie(resultado.getInt(2));
        raza.setEstado(Boolean.valueOf(resultado.getString(3)));
        return raza;
    }
}
