package egt.infopets.mantenedor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.db.MantenedorEspecie;

/**
 * Created by cetecom on 05-10-2017.
 */

public class AddEspecie extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_especie);

        ListView auxListView = (ListView)findViewById(R.id.lvMostrar);

        mostrar();
    }

    public void addEspecie(View view) {
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
                    this.mensaje("Especie: "+ especie +" || Esado: Activo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
                } else if (auxInactivo.isChecked())
                {
                    newEspecie.setEstado(false);
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    auxMantenedor.insert(newEspecie);
                    this.mensaje("Especie: "+ especie +" || Esado: Inactivo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
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
                mostrar();
        } catch (Exception ex) {
            this.mensaje("Error al ejecutar. codigo:"+ex);
        }

    }

    public void mostrar(){
        MantenedorEspecie mantenedorEspecie =new MantenedorEspecie(this);

        List<Especie> auxListaEspecie = mantenedorEspecie.getAll();

        String[] listaString = new String[auxListaEspecie.size()];

        Iterator iter = auxListaEspecie.iterator();

        int pos = 0;

        while (iter.hasNext()){
            Especie auxLista = new Especie();

            auxLista = (Especie) iter.next();

            listaString[pos] = auxLista.getId() +"         "+ auxLista.getSpecie()+"         "+auxLista.isEstado();
            pos++;
        }

        ListView auxListView = (ListView)findViewById(R.id.lvMostrar);

        auxListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaString));
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}