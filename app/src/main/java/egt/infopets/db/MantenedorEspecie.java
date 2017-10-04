package egt.infopets.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.clases.Especie;
import egt.infopets.db.DbInfoPet;

/**
 * Created by Soporte on 03-10-2017.
 */

public class MantenedorEspecie {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    public MantenedorEspecie(Context context){
        this.context = context;
        tabla = "especie";
        columnas = new ArrayList<String>();
        columnas.add("id_Especie");
        columnas.add("estado");
        columnas.add("especie");
    }

    public void insert(Especie especie) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(especie);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Especie> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Especie> especies = new ArrayList<Especie>();
        if (resultado.moveToFirst()) {
            do {
                Especie especie = this.setEspecie(resultado);
                especies.add(especie);
            } while (resultado.moveToNext());
        }
        conector.close();
        return especies;
    }

    private ArrayList<String> valores(Especie especie){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(especie.getId());
        valores.add(Boolean.toString(especie.isEstado()));
        valores.add(especie.getSpecie());
        return valores;
    }

    public Especie getByCodigoTicket(String codEspecie) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id = " + codEspecie;
        Cursor resultado = this.conector.select(query);
        Especie especie = new Especie();
        if (resultado.moveToFirst()) {
            especie = this.setEspecie(resultado);
        }
        conector.close();
        return especie;
    }

    public void update(Especie especie) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(especie);
        String condicion = "id = " + especie.getId();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int codEspecie) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "id = " + codEspecie;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Especie setEspecie(Cursor resultado){
        Especie especie = new Especie();
        especie.setId(resultado.getString(0));
        especie.setEstado(Boolean.valueOf(resultado.getString(1)));
        especie.setSpecie(resultado.getString(2));
        return especie;
    }

}
