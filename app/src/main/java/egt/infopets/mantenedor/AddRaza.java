package egt.infopets.mantenedor;



import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.db.MantenedorEspecie;

public class AddRaza extends AppCompatActivity {

    Spinner spEspecie;
    List<Especie> auxLista;
    String[] listaString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_raza);

        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        spEspecie = (Spinner) findViewById(R.id.spEspecie);

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
        /*
        Iterator iter = auxLista.iterator();

        int pos = 0;

        while (iter.hasNext()){

                Especie auxEspecie = new Especie();

                auxEspecie = (Especie) iter.next();

                listaString[pos] = auxEspecie.getSpecie();

                auxLista.add(auxEspecie);

                pos++;
        }*/
    }

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }


    public void addRaza(View view){
        try {
            EditText auxEspecie = (EditText) findViewById(R.id.txtNewEspecie);
            RadioButton auxActivo = (RadioButton) findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton) findViewById(R.id.rbInactivo);

            Especie newEspecie = new Especie();
            MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);


            String especie = auxEspecie.getText().toString();

            if (!auxEspecie.getText().toString().isEmpty())
            {
                if (auxActivo.isChecked())
                {
                    newEspecie.setEstado(true);
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    auxMantenedor.insert(newEspecie);
                    this.mensaje("Especie: "+ especie +" con esado: Activo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
                } else if (auxInactivo.isChecked())
                {
                    newEspecie.setEstado(false);
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    this.mensaje("Especie: "+ auxEspecie +" con esado: Inactivo");
                }
                else
                {
                    mensaje("Debe seleccionar el estado de la especie");
                }
            }
            else
            {
                this.mensaje("Debes Ingresar una nueva especie");
            }
        } catch (Exception ex) {
            this.mensaje("Error al ejecutar. codigo:"+ex);
        }

    }


    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}