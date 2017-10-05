package egt.infopets.mantenedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.db.MantenedorEspecie;

public class AddPet extends AppCompatActivity {

    Spinner spEspecie;
    List<Especie> auxLista;
    String[] listaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        spEspecie = (Spinner) findViewById(R.id.spRazaEspecie);

        consultaListaEspecies();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaString);
        spEspecie.setAdapter(adaptador);
    }

    private void consultaListaEspecies() {
        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        auxLista =  auxMantenedor.getAll();

        listaString = new String[auxLista.size()];

        for(int i=0;i<auxLista.size();i++)
            listaString[i]=auxLista.get(i).getSpecie();
    }

    public void backToFirstScreen(View view){
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }


    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }


}
