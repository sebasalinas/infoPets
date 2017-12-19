package egt.infopets.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Mascota;
import egt.infopets.Mantenedores.SQLite.MantenedorEspecie;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;
import egt.infopets.R;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
    }

    private void mostrar() {
        final MantenedorEspecie mantenedorEspecie =new MantenedorEspecie(this);

        final List<Especie> auxListaEspecie = mantenedorEspecie.getAll();

        String[] listaString = new String[auxListaEspecie.size()];

        Iterator iter = auxListaEspecie.iterator();

        int pos = 0;

        while (iter.hasNext()){
            Especie auxLista = new Especie();

            auxLista = (Especie) iter.next();

            listaString[pos] = auxLista.getSpecie();
            pos++;
        }

        final ListView auxListView = (ListView)findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaString));

    }

    public void buscar(View view) {
        mostrar();
    }
}
