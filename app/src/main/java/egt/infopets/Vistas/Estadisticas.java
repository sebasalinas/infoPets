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

import egt.infopets.Clases.Duenio;
import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Raza;
import egt.infopets.Clases.Visitas;
import egt.infopets.Mantenedores.SQLite.MantenedorDuenio;
import egt.infopets.Mantenedores.SQLite.MantenedorEspecie;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;
import egt.infopets.Mantenedores.SQLite.MantenedorRaza;
import egt.infopets.Mantenedores.SQLite.MantenedorVisitas;
import egt.infopets.R;

public class Estadisticas extends AppCompatActivity {
    String[] listaString;
    Spinner seleccion;
    ListView list;
    private ArrayList<String> listaVisitas;
    private ArrayAdapter<String> adaptador1;

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
            case 3:
                list.setAdapter(null);
                cargarDuenio();
                break;
            case 4:
                list.setAdapter(null);
                cargarVisitas();
                break;
            case 5:
                list.setAdapter(null);
                cargarMascota();
                break;
            default:
                list.setAdapter(null);
                mensaje("Ha ocurrido un error inesperado");
        }
    }

    private void cargarDuenio() {

        final MantenedorDuenio mantenedorDuenio = new MantenedorDuenio(this);

        final List<Duenio> auxListaDuenio = mantenedorDuenio.getAll();

        String[] listaString = new String[auxListaDuenio.size()];

        Iterator iter = auxListaDuenio.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Duenio auxLista = new Duenio();

            auxLista = (Duenio) iter.next();

            int pos2 = conteo("Dueño",auxLista.getRut());

            listaString[pos] = auxLista.getNombre() +" || "+pos2;
            pos++;
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }

    private void cargarVisitas() {
        MantenedorVisitas mantenedorVisitas = new MantenedorVisitas(this);

        List<Visitas> auxListaVisitas = mantenedorVisitas.getAll();

        listaVisitas = new ArrayList<String>();

        listaVisitas.add("Total de visitas atendidas: "+ auxListaVisitas.size());

        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaVisitas);

        list.setAdapter(adaptador1);
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

        int pos = 1;

        while (iter.hasNext()) {
            Especie auxLista = new Especie();

            auxLista = (Especie) iter.next();

            //int pos2 = conteo("Raza",Integer.toString(auxLista.getId()));

            listaString[pos] = auxLista.getSpecie() /*+" || "+ pos2 */;
            pos++;
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));

    }

    private void cargarMascota() {
        final MantenedorMascota mantenedorMascota = new MantenedorMascota(this);

        final List<Mascota> auxListaMascota = mantenedorMascota.getAll();

        String[] listaString = new String[auxListaMascota.size()];

        Iterator iter = auxListaMascota.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Mascota auxLista = new Mascota();

            auxLista = (Mascota) iter.next();

            int pos2 = conteo("Mascota",Integer.toString(auxLista.getId()));

            listaString[pos] = "Nombre: "+auxLista.getNombre() +"      ||      "+ "Visitas: "+ pos2;
            pos++;
        }

        if (pos == 0){
            mensaje("No hay Mascotas registradas");
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvInfoListar);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));

    }

    private int conteo(String tipo,String id){
        int count = 0;

        MantenedorVisitas mantenedorVisitas = new MantenedorVisitas(this);
        MantenedorMascota mantenedorMascota = new MantenedorMascota(this);
        MantenedorRaza mantenedorRaza = new MantenedorRaza(this);

        List<Visitas> auxListaVisitas;
        List<Mascota> auxListaMascota;
        List<Raza> auxListaRaza;

        switch (tipo){
            case "Mascota":

                auxListaVisitas = mantenedorVisitas.getByCodigo(Integer.parseInt(id));
                count = auxListaVisitas.size();
                break;

            case "Raza":

                auxListaRaza = mantenedorRaza.getById(Integer.parseInt(id));
                count = auxListaRaza.size();
                break;

            case "Especie":


                break;

            case "Dueño":

                auxListaMascota = mantenedorMascota.getByRut(id.toString());
                count = auxListaMascota.size();
                break;

            case "Visitas":

                auxListaVisitas = mantenedorVisitas.getAll();
                count = auxListaVisitas.size();
                break;
        }
       return count;
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
