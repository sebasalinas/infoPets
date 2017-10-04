package egt.infopets.mantenedor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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

    public void mostrar(View view){

        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        List<Especie> auxListaEspecies = auxMantenedor.getAll();

        String[] listaString = new String[auxListaEspecies.size()];

        Iterator iter = auxListaEspecies.iterator();

        int pos = 1;

        while (iter.hasNext()){

            Especie auxEspecies = new Especie();

            auxEspecies = (Especie) iter.next();

            listaString[pos] = auxEspecies.getId()+" "+auxEspecies.getSpecie();

            pos++;
        }
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}