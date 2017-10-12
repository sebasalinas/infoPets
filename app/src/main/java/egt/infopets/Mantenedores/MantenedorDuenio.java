package egt.infopets.Mantenedores;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.Clases.Duenio;

/**
 * Created by Administrador on 05-10-2017.
 */

public class MantenedorDuenio {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    public MantenedorDuenio(Context context){

        this.context = context;
        tabla = "duenio";
        columnas = new ArrayList<String>();
        columnas.add("rut");
        columnas.add("nombre");
        columnas.add("direccion");
        columnas.add("numero");
    }

    public void insert(Duenio duenio) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(duenio);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Duenio> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Duenio> duenios = new ArrayList<Duenio>();
        if (resultado.moveToFirst()) {
            do {
                Duenio duenio = this.setDuenio(resultado);
                duenios.add(duenio);
            } while (resultado.moveToNext());
        }
        conector.close();
        return duenios;
    }

    private ArrayList<String> valores(Duenio duenio){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(duenio.getRut());
        valores.add(duenio.getNombre());
        valores.add(duenio.getDireccion());
        valores.add(duenio.getNumero());
        return valores;
    }

    public Duenio getByCodigo(String rut) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE rut = " + rut;
        Cursor resultado = this.conector.select(query);
        Duenio duenio = new Duenio();
        if (resultado.moveToFirst()) {
            duenio = this.setDuenio(resultado);
        }
        conector.close();
        return duenio;
    }

    public void update(Duenio duenio) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(duenio);
        String condicion = "rut = " + duenio.getRut();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int rut) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "rut = " + rut;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Duenio setDuenio(Cursor resultado){
        Duenio duenio = new Duenio();
        duenio.setRut(resultado.getString(0));
        duenio.setNombre(resultado.getString(1));
        duenio.setDireccion(resultado.getString(2));
        duenio.setNumero(resultado.getString(3));
        return duenio;
    }
}
