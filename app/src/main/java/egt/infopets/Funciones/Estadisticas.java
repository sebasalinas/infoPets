package egt.infopets.Funciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Visitas;
import egt.infopets.Mantenedores.MantenedorMascota;
import egt.infopets.Mantenedores.MantenedorVisitas;
import egt.infopets.R;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        mostrar();
    }

    private void mostrar() {
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);

        //int var = Integer.valueOf(spinner);

        final List<Integer> auxListaVisitas = auxMantenedor.getAllRut();

        String[] listaString = new String[auxListaVisitas.size()];
        Iterator iter = auxListaVisitas.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Mascota auxLista = new Mascota();

            auxLista = (Mascota) iter.next();

            listaString[pos] = "Dueño: "+auxLista.getRut()+" Nombre Mascota: "+auxLista.getNombre();
            pos++;
        }

        ListView auxListView = (ListView) findViewById(R.id.lvEstadisticas);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));



    }
}
