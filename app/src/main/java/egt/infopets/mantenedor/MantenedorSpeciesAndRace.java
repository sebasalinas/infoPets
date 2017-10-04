package egt.infopets.mantenedor;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.clases.Especie;
import egt.infopets.db.DbInfoPet;

/**
 * Created by Soporte on 03-10-2017.
 */

public class MantenedorSpeciesAndRace {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla = "specieandrace";

    public MantenedorSpeciesAndRace(Context context){
        this.context = context;
        tabla = "Mascota";
        columnas = new ArrayList<String>();
        columnas.add("ID");
        columnas.add("Estado");
        columnas.add("Especie");
        columnas.add("Raza");
    }

    public void inset(Especie tipo) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(tipo);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Especie> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Especie> specieandraces = new ArrayList<Especie>();
        if (resultado.moveToFirst()) {
            do {
                //Especie specie = this.setTicket(resultado);
                //specieandraces.add(specie);
            } while (resultado.moveToNext());
        }
        conector.close();
        return specieandraces;
    }

    private ArrayList<String> valores(Especie valor){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(valor.getId());
        valores.add(Boolean.toString(valor.isEstado()));
        valores.add(valor.getSpecie());
        valores.add(valor.getRace());
        return valores;
    }
}
