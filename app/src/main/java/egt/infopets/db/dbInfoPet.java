package egt.infopets.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Soporte on 02-10-2017.
 */

public class dbInfoPet extends SQLiteOpenHelper{

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "infoPets";
    private static final String TABLA_MASCOTA = "CREATE TABLE mascota (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "rut TEXT," +
            "mNombre TEXT, " +
            "fNacimiento TEXT, " +
            "especie TEXT, " +
            "raza TEXT, " +
            "sexo TEXT, " +
            "color TEXT," +
            "diagnostico TEXT," +
            "dNombre TEXT," +
            "direccion TEXT," +
            "numero TEXT," +
            "FOREIGN KEY (diagnostico) REFERENCES visitas(cod)," +
            "FOREIGN KEY (rut) REFERENCES duenio(rut))";
    private static final String TABLA_DUENIO = "CREATE TABLE duenio (" +
            "rut TEXT PRIMARY KEY," +
            "nombre TEXT," +
            "direccion TEXT," +
            "numero TEXT )";
    private static final String TABLA_SPECIEANDRACE = "CREATE TABLE specieandrace(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "estado BOOLEAN," +
            "especie TEXT, " +
            "raza TEXT)";
    private static final String TABLA_VISITAS = "CREATE TABLE visitas (" +
            "cod INTEGER AUTOINCREMENT," +
            "fVisita TEXT," +
            "descripcion TEXT)";

    private SQLiteDatabase db = null;


    public dbInfoPet(Context context){
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_DUENIO);
        db.execSQL(TABLA_VISITAS);
        db.execSQL(TABLA_MASCOTA);
        db.execSQL(TABLA_SPECIEANDRACE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_DUENIO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_VISITAS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_SPECIEANDRACE);
        onCreate(db);
    }

    public Cursor select(String sql) throws SQLiteException{
        return db.rawQuery(sql, null);
    }

    public long insert(String tabla , ArrayList<String> columnas, ArrayList<String> valores) throws SQLiteException {
        ContentValues insert = new ContentValues();
        for(int z=0;z<columnas.size();z++)
            insert.put(columnas.get(z),valores.get(z));
        return db.insert(tabla, null, insert);
    }

    public void update(String tabla ,ArrayList<String> columnas, ArrayList<String> valores, String condicion) throws SQLiteException {
        ContentValues update = new ContentValues();
        for(int z=0;z<columnas.size();z++)
            update.put(columnas.get(z),valores.get(z));
        db.update(tabla, update, condicion, null);
    }

    public void delete(String tabla, String condicion) throws SQLiteException {
        db.delete(tabla, condicion, null);
    }

    @Override
    public void close() {
        db.close();
    }
}
