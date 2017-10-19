package egt.infopets.Mantenedores;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import egt.infopets.Clases.Mascota;

/**
 * Created by Administrador on 05-10-2017.
 */

public class MantenedorMascota {

    private DbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    public MantenedorMascota(Context context){

        this.context = context;
        tabla = "mascota";
        columnas = new ArrayList<String>();
        columnas.add("nombre");
        columnas.add("rut");
        columnas.add("fNacimiento");
        columnas.add("especie");
        columnas.add("raza");
        columnas.add("sexo");
        columnas.add("color");
    }

    public void insert(Mascota mascota) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(mascota);
        this.conector.insert(tabla, columnas, valores);
        conector.close();
    }

    public ArrayList<Mascota> getAll() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla;
        Cursor resultado = this.conector.select(query);
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        if (resultado.moveToFirst()) {
            do {
                Mascota mascota = this.setMascota(resultado);
                mascotas.add(mascota);
            } while (resultado.moveToNext());
        }
        conector.close();
        return mascotas;
    }

    private ArrayList<String> valores(Mascota mascota){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(mascota.getNombre());
        valores.add(mascota.getRut());
        valores.add(mascota.getfNacimiento());
        valores.add(Integer.toString(mascota.getEspecie()));
        valores.add(Integer.toString(mascota.getRaza()));
        valores.add(mascota.getSexo());
        valores.add(mascota.getColor());
        return valores;
    }

    public Mascota getByCodigo(String cod) {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id_Mascota = " + cod;
        Cursor resultado = this.conector.select(query);
        Mascota mascota = new Mascota();
        if (resultado.moveToFirst()) {
            mascota = this.setMascota(resultado);
        }
        conector.close();
        return mascota;
    }

    public void update(Mascota mascota) {
        this.conector = new DbInfoPet(this.context);
        ArrayList<String> valores = this.valores(mascota);
        String condicion = "id_Mascota = " + mascota.getId();
        this.conector.update(tabla, columnas, valores, condicion);
        conector.close();
    }

    public void delete(int cod) {
        this.conector = new DbInfoPet(this.context);
        String condicion = "id_Mascota = " + cod;
        this.conector.delete(tabla, condicion);
        conector.close();
    }

    private Mascota setMascota(Cursor resultado){
        Mascota mascota = new Mascota();
        mascota.setId(resultado.getInt(0));
        mascota.setNombre(resultado.getString(1));
        mascota.setRut(resultado.getString(2));
        mascota.setfNacimiento(resultado.getString(3));
        mascota.setEspecie(resultado.getInt(4));
        mascota.setRaza(resultado.getInt(5));
        mascota.setSexo(resultado.getString(6));
        mascota.setColor(resultado.getString(7));
        return mascota;
    }

    public ArrayList<Integer> getAllRut() {
        this.conector = new DbInfoPet(this.context);
        String query = "SELECT COUNT(rut) FROM " + tabla + " GROUP BY rut";
        Cursor resultado = this.conector.select(query);
        ArrayList<Integer> mascotas = new ArrayList<Integer>();
        if (resultado.moveToFirst()) {
            do {
                Integer mascota = resultado.getInt(0);
                mascotas.add(mascota);
            } while (resultado.moveToNext());
        }
        conector.close();
        return mascotas;
    }
}
