package egt.infopets.mantenedor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.db.DbInfoPet;

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

            String idAux = auxId.toString();

            if (searchPetById(auxId.getText().toString())){

                Intent intent = new Intent(this, SearchPet.class);

                this.mensaje("La Mascota ha sido encontrada");

                intent.putExtra("varCod",auxId.getText().toString());

                startActivity(intent);
            }
            else {
                this.mensaje("No se encontro Mascota");
            }
        }
        catch (Exception ex){
            this.mensaje("Codigo no encontrado "+ex);
        }
    }

    public boolean searchPetById(String id) {

        this.conector = new DbInfoPet(this.context);
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