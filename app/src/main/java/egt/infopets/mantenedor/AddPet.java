package egt.infopets.mantenedor;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.clases.Raza;
import egt.infopets.db.MantenedorEspecie;
import egt.infopets.db.MantenedorRaza;

public class AddPet extends AppCompatActivity {

    Spinner spEspecie;
    Spinner spRaza;
    List<Especie> auxListaEspecie;
    ArrayList<Raza> auxListaRaza;
    String[] listaStringEspecie;
    String[] listaStringRaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);



        spEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
        spRaza = (Spinner) findViewById(R.id.spRaza);

        //carga Spinner Especies
        consultaListaEspecies();
        ArrayAdapter<CharSequence> adaptadorEspecie = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaStringEspecie);
        spEspecie.setAdapter(adaptadorEspecie);

        //carga Spinner Raza
        consultaListaRaza();

        ArrayAdapter<CharSequence> adaptadorRaza = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaStringRaza);

        spRaza.setAdapter(adaptadorRaza);
    }

    private void consultaListaEspecies() {
        MantenedorEspecie auxMantenedorEspecie = new MantenedorEspecie(this);

        auxListaEspecie =  auxMantenedorEspecie.getAll();

        listaStringEspecie = new String[auxListaEspecie.size()];

        for(int i=0;i<auxListaEspecie.size();i++)
            listaStringEspecie[i]=auxListaEspecie.get(i).getSpecie();
    }

    private void consultaListaRaza() {
        MantenedorRaza auxMantenedorRaza = new MantenedorRaza(this);

        auxListaRaza = auxMantenedorRaza.getAll();

        listaStringRaza = new String[auxListaRaza.size()];

        for(int i=0;i<auxListaRaza.size();i++)
            listaStringRaza[i]=auxListaRaza.get(i).getDescripcion();
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }



}
