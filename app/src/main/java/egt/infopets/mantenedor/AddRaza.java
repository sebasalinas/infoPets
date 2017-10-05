package egt.infopets.mantenedor;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.clases.Raza;
import egt.infopets.db.MantenedorEspecie;
import egt.infopets.db.MantenedorRaza;

public class AddRaza extends AppCompatActivity {

    Spinner spEspecie;
    List<Especie> auxLista;
    String[] listaString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_raza);

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

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }


    public void addRaza(View view){
        try {
            Spinner auxSpinner = (Spinner) findViewById(R.id.spRazaEspecie);
            EditText auxRaza = (EditText) findViewById(R.id.txtNewRaza);
            RadioButton auxActivo = (RadioButton) findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton) findViewById(R.id.rbInactivo);

            Raza newRaza = new Raza();
            MantenedorRaza auxMantenedor = new MantenedorRaza(this);


            String raza = auxRaza.getText().toString();

            if (!auxRaza.getText().toString().isEmpty())
            {
                if (auxActivo.isChecked())
                {
                    newRaza.setDescripcion(auxRaza.getText().toString());
                    newRaza.setEspecie((Especie) auxSpinner.getSelectedItem());
                    newRaza.setEstado(true);
                    auxMantenedor.insert(newRaza);
                    this.mensaje("Especie: "+ raza +" con esado: Activo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
                } else if (auxInactivo.isChecked())
                {
                    newRaza.setDescripcion(auxRaza.getText().toString());
                    newRaza.setEspecie((Especie) auxSpinner.getSelectedItem());
                    newRaza.setEstado(false);
                    auxMantenedor.insert(newRaza);
                    this.mensaje("Especie: "+ raza +" con esado: Inactivo");
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