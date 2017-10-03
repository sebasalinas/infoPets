package egt.infopets.mantenedor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import egt.infopets.R;
import egt.infopets.db.dbInfoPet;
import egt.infopets.firstScreen;
import egt.infopets.clases.mascota;

public class addPet extends AppCompatActivity {

    private dbInfoPet conector;
    private Context context;
    private ArrayList<String> columnas;
    private String tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
    }

    public void backToFirstScreen(View view){
        Intent intent = new Intent(this, firstScreen.class);
        startActivity(intent);
    }

    public void addPet(mascota newMascota){
            this.conector = new dbInfoPet(this.context);
            ArrayList<String> valores = this.valores(newMascota);
            this.conector.insert(tabla, columnas, valores);
            conector.close();
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> valores(mascota newMascota){
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(newMascota.getmNombre());
        valores.add(newMascota.getfNacimiento());
        valores.add(newMascota.getEspecie());
        valores.add(newMascota.getRaza());
        valores.add(newMascota.getSexo());
        valores.add(newMascota.getColor());
        valores.add(newMascota.getNombre());
        valores.add(newMascota.getDireccion());
        valores.add(newMascota.getNumero());
        return valores;
    }
}
