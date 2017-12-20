package egt.infopets.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.Clases.Mascota;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

    }

    public void goToEstadisticas(View view){
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
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

            EditText auxId = (EditText) findViewById(R.id.txtInfoBuscars);
            String auxVar = auxId.getText().toString();
            if (searchPetById(auxId.getText().toString())){

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

    public boolean searchPetById(String id) {
        MantenedorMascota auxMantenedor =new MantenedorMascota(this);
        Mascota auxListaMascota = auxMantenedor.getByCodigo(id);

        if (auxListaMascota.getId()>0){
            return true;
        }
        else return false;

    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}
