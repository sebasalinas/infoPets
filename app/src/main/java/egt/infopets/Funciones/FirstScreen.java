package egt.infopets.Funciones;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.Clases.Visitas;
import egt.infopets.Mantenedores.MantenedorVisitas;
import egt.infopets.R;
import egt.infopets.Clases.Mascota;
import egt.infopets.Mantenedores.DbInfoPet;
import egt.infopets.Mantenedores.MantenedorMascota;

public class FirstScreen extends AppCompatActivity {

    private DbInfoPet conector;
    private Context context;
    private String tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    private void mostrar() {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);

        //int var = Integer.valueOf(spinner);

        final List<Visitas> auxListaVisitas = auxMantenedor.getAll();

        String[] listaString = new String[auxListaVisitas.size()];
        Iterator iter = auxListaVisitas.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Visitas auxLista = new Visitas();

            auxLista = (Visitas) iter.next();

            listaString[pos] = ""+auxLista.getCod();
            pos++;
        }

        ListView auxListView = (ListView) findViewById(R.id.lvEstadistica);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));



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

            EditText auxId = (EditText) findViewById(R.id.txtId);
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
