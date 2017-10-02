package egt.infopets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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

    public void goToSearchPet(View view) {

        Intent intent = new Intent(this, searchPet.class);

        EditText auxId = (EditText) findViewById(R.id.txtId);



        intent.putExtra("varCod",auxId.getText().toString());

        try {
            this.conector = new dbInfoPet(this.context);
            String query = "SELECT * FROM mascota WHERE id = " + view;
            Cursor resultado = this.conector.select(query);
            mascota auxMascota = new mascota();
            if (resultado.moveToFirst()){
                auxMascota = this.
            }
        }
        catch (Exception ex){
            this.mensaje("Codigo no encontrado");
        }

        startActivity(intent);
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
    }
}
