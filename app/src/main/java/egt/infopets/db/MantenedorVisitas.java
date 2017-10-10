package egt.infopets.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import egt.infopets.clases.Visitas;

/**
 * Created by Administrador on 06-10-2017.
 */

public class MantenedorVisitas {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    public MantenedorVisitas(Context context){
        this.context = context;
        tabla = "visitas";
        columnas = new ArrayList<String>();
        columnas.add("fVisita");
        columnas.add("id_Mascota");
        columnas.add("descripcion");
    }

    public void insert(Visitas visita) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(visita);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Visitas> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Visitas> visitas = new ArrayList<Visitas>();
        if (resultado.moveToFirst()) {
            do {
                Visitas visita = this.setVisita(resultado);
                visitas.add(visita);
            } while (resultado.moveToNext());
        }
        conector.close();
        return visitas;
    }

    private ArrayList<String> valores(Visitas visita){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(visita.getFechaVisita());
        valores.add(Integer.toString(visita.getMascota()));
        valores.add(visita.getDescripcion());
        return valores;
    }

    public ArrayList<Visitas> getByCodigo(int id) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE cod_Visita = "+id;
        Log.i("Query", query);
        Cursor resultado = this.conector.select(query);
        ArrayList<Visitas> visitas = new ArrayList<Visitas>();
        if (resultado.moveToFirst()) {
            do {
                Visitas visita = this.setVisita(resultado);
                visitas.add(visita);
            } while (resultado.moveToNext());
        }
        conector.close();
        return visitas;
    }

    public void update(Visitas visitas) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(visitas);
        String condicion = "cod_Visita = " + visitas.getCod();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int cod) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "cod_Visita = " + cod;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Visitas setVisita(Cursor resultado){
        Visitas visitas = new Visitas();
        visitas.setCod(resultado.getInt(0));
        visitas.setFechaVisita(resultado.getString(1));
        visitas.setMascota(resultado.getInt(2));
        visitas.setDescripcion(resultado.getString(3));
        return visitas;
    }
}