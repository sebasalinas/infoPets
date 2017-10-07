package egt.infopets.mantenedor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.clases.Mascota;
import egt.infopets.clases.Visitas;
import egt.infopets.db.DbInfoPet;
import egt.infopets.db.MantenedorMascota;
import egt.infopets.db.MantenedorVisitas;

public class FirstScreen extends AppCompatActivity {

    private DbInfoPet conector;
    private Context context;
    private String tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


    }

    public void goToAddPet(View view) {
        Intent intent = new Intent(this, AddPet.class);
        startActivity(intent);
    }

    public void goToRaza(View view) {
        Intent intent = new Intent(this, AddRaza.class);
        startActivity(intent);
    }

    public void goToEspecie(View view){
        Intent intent = new Intent(this, AddEspecie.class);
        startActivity(intent);
    }

    public void goToSearchPet(View view) {

        try {

            EditText auxId = (EditText) findViewById(R.id.txtId);
            String auxVar = auxId.getText().toString();
            if (searchPetById(Integer.valueOf( auxId.getText().toString()))){

                Intent intent = new Intent(this, SearchPet.class);

                this.mensaje("La mascota ha sido encontrada");

                intent.putExtra("varCod",auxVar);

                auxId.setText("");
                startActivity(intent);
            }
            else {
                this.mensaje("No se encontro Mascota");
            }
        }
        catch (Exception ex){
            this.mensaje("Codigo no encontrado ");
        }
    }

    public boolean searchPetById(int id) {
        MantenedorMascota auxMantenedor =new MantenedorMascota(this);
        Mascota auxListaMascota = auxMantenedor.getByCodigo(id);

        if (auxListaMascota.getId()>0){
            return true;
        }
        else return false;

    }

    public void touch() {

    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}
