package egt.infopets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import egt.infopets.db.dbInfoPet;
import egt.infopets.mantenedor.addPet;
import egt.infopets.mantenedor.mantenedorSpeciesAndRace;

public class firstScreen extends AppCompatActivity {

    private dbInfoPet conector;
    private Context context;
    private String tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void goToAddPet(View view) {
        Intent intent = new Intent(this, addPet.class);
        startActivity(intent);
    }

    public void goToSpeciesAndRace(View view) {
        Intent intent = new Intent(this, mantenedorSpeciesAndRace.class);
        startActivity(intent);
    }

    public void goToSearchPet(View view) {

        try {

            EditText auxId = (EditText) findViewById(R.id.txtId);

            String idAux = auxId.toString();

            if (searchPetById(auxId.getText().toString())){

                Intent intent = new Intent(this, searchPet.class);

                this.mensaje("La mascota ha sido encontrada");

                intent.putExtra("varCod",auxId.getText().toString());

                startActivity(intent);
            }
            else {
                this.mensaje("No se encontro mascota");
            }
        }
        catch (Exception ex){
            this.mensaje("Codigo no encontrado "+ex);
        }
    }

    public boolean searchPetById(String id) {

        this.conector = new dbInfoPet(this.context);
        String query = "SELECT * FROM " + tabla + " WHERE id = " + id;
        Cursor resultado = this.conector.select(query);
        if (resultado.moveToFirst()){
            this.mensaje("Paso");
            return true;
        }
        return false;
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}
