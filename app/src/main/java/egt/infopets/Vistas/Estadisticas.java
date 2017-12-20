package egt.infopets.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Raza;
import egt.infopets.Mantenedores.SQLite.MantenedorEspecie;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;
import egt.infopets.Mantenedores.SQLite.MantenedorRaza;
import egt.infopets.R;

public class Estadisticas extends AppCompatActivity {
    String[] listaString;
    Spinner seleccion;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        seleccion = (Spinner) findViewById(R.id.spSeleccion);
        list = (ListView) findViewById(R.id.lvInfoListar);

        cargarSpinner();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaString);
        seleccion.setAdapter(adaptador);

    }

    private void cargarSpinner() {

        listaString = new String[6];
        listaString[0] = "Seleccion";
        listaString[1] = "Especie";
        listaString[2] = "Raza";
        listaString[3] = "Dueño";
        listaString[4] = "Visitas";
        listaString[5] = "Mascotas";
    }

    private void mostrar(int posicion) {
        switch (posicion) {
            case 1:
                list.setAdapter(null);
                cargarEspecies();
                break;
            case 2:
                list.setAdapter(null);
                cargarRazas();
                break;
            default:
                list.setAdapter(null);
                mensaje("Ha ocurrido un error inesperado");
        }
    }

    private void cargarRazas() {
        final MantenedorRaza mantenedorEspecie = new MantenedorRaza(this);

        final List<Raza> auxListaRaza = mantenedorEspecie.getAll();

        String[] listaString = new String[auxListaRaza.size()];

        Iterator iter = auxListaRaza.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Raza auxLista = new Raza();

            auxLista = (Raza) iter.next();

            listaString[pos] = auxLista.getDescripcion();
            pos++;
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }

    private void cargarEspecies() {
        final MantenedorEspecie mantenedorEspecie = new MantenedorEspecie(this);

        final List<Especie> auxListaEspecie = mantenedorEspecie.getAll();

        String[] listaString = new String[auxListaEspecie.size()];

        Iterator iter = auxListaEspecie.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Especie auxLista = new Especie();

            auxLista = (Especie) iter.next();

            listaString[pos] = auxLista.getSpecie();
            pos++;
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));

    }

    public void buscar(View view) {
        if (seleccion.getSelectedItemPosition() == 0) {
            list.setAdapter(null);
            mensaje("Debe seleccionar lo que desea buscar");
        } else {
            mostrar(seleccion.getSelectedItemPosition());
        }
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
