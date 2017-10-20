package egt.infopets.Funciones;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Raza;
import egt.infopets.Mantenedores.MantenedorEspecie;
import egt.infopets.Mantenedores.MantenedorRaza;

public class AddRaza extends AppCompatActivity {

    Spinner spEspecie;
    List<Especie> auxLista;
    String[] listaString;
    Boolean var =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_raza);

        ListView auxListView = (ListView) findViewById(R.id.lvMostrarRaza);
        ScrollView auxScroll = (ScrollView) findViewById(R.id.scView);

        auxScroll.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.lvMostrarRaza).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        auxListView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mostrar();
        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        spEspecie = (Spinner) findViewById(R.id.spRazaEspecie);

        consultaListaEspecies();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaString);
        spEspecie.setAdapter(adaptador);


    }

    public void mostrar() {
        MantenedorRaza mantenedorRaza = new MantenedorRaza(this);

        List<Raza> auxListaRaza = mantenedorRaza.getAll();

        String[] listaString = new String[auxListaRaza.size()];

        Iterator iter = auxListaRaza.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Raza auxLista = new Raza();
            String auxVar = "";
            auxLista = (Raza) iter.next();
            if (auxLista.getDescripcion()==""){
                break;
            }
            if (auxLista.isEstado()){
                 auxVar = "Activo";
            }
            else {
                 auxVar = "Inactivo";
            }
            listaString[pos] = auxLista.getDescripcion();
            pos++;
        }

        ListView auxListView = (ListView) findViewById(R.id.lvMostrarRaza);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }

    private boolean consultaListaEspecies() {
        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);
        Boolean var = false;
        auxLista =  auxMantenedor.getAll();

        listaString = new String[auxLista.size()];

        for(int i=0;i<auxLista.size();i++) {
            listaString[i] = auxLista.get(i).getSpecie();
            if (auxLista.get(0).getSpecie() == "") {
                var = true;
            } else {
                var = true;
            }
        }
        return var;
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
            if (!consultaListaEspecies()){
                mensaje("Debe haber una raza previamente cargada");
            }
            else {
                if (!auxRaza.getText().toString().isEmpty()) {
                    if (auxActivo.isChecked()) {
                        newRaza.setDescripcion(auxRaza.getText().toString());
                        newRaza.setEspecie(auxSpinner.getSelectedItemPosition());
                        newRaza.setEstado(true);
                        auxMantenedor.insert(newRaza);
                        this.mensaje("Especie: " + raza + " || Esado: Activo");
                        ((EditText) findViewById(R.id.txtNewRaza)).setText("");
                    } else if (auxInactivo.isChecked()) {
                        newRaza.setDescripcion(auxRaza.getText().toString());
                        newRaza.setEspecie(auxSpinner.getSelectedItemPosition());
                        newRaza.setEstado(false);
                        auxMantenedor.insert(newRaza);
                        this.mensaje("Especie: " + raza + " || Esado: Inactivo");
                    } else {
                        mensaje("Debe seleccionar el estado de la especie");
                    }
                } else {
                    this.mensaje("Debes Ingresar una nueva especie");
                }
            }
            mostrar();
        } catch (Exception ex) {
            this.mensaje("Error al ejecutar. codigo:"+ex);
        }
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}