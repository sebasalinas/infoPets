package egt.infopets.mantenedor;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Duenio;
import egt.infopets.clases.Especie;
import egt.infopets.clases.Mascota;
import egt.infopets.clases.Raza;
import egt.infopets.db.MantenedorDuenio;
import egt.infopets.db.MantenedorEspecie;
import egt.infopets.db.MantenedorMascota;
import egt.infopets.db.MantenedorRaza;

public class AddPet extends AppCompatActivity {

    Spinner spEspecie;
    Spinner spRaza;
    List<Especie> auxListaEspecie;
    ArrayList<Raza> auxListaRaza;
    String[] listaStringEspecie;
    String[] listaStringRaza;
    //el codigo debe ser de  de maximo 8 digitos comenzando por el 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        //carga de los spinner
        spEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
        spRaza = (Spinner) findViewById(R.id.spRaza);

        consultaListaEspecies();

        ArrayAdapter<CharSequence> adaptadorEspecie = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaStringEspecie);

        spEspecie.setAdapter(adaptadorEspecie);

        consultaListaRaza();

        ArrayAdapter<CharSequence> adaptadorRaza = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaStringRaza);

        spRaza.setAdapter(adaptadorRaza);
        //fin carga spinner
        mostrar();
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

    public void addDuenio(){

        EditText auxRut = (EditText)findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText)findViewById(R.id.txtNombreDuenio);
        EditText auxDireccion = (EditText)findViewById(R.id.txtDireccion);
        EditText auxNumero = (EditText)findViewById(R.id.txtNumero);

        Duenio newDuenio = new Duenio();
        MantenedorDuenio auxMantenedorDuenio = new MantenedorDuenio(this);

        newDuenio.setRut(auxRut.getText().toString());
        newDuenio.setNombre(auxNombreDuenio.getText().toString());
        newDuenio.setDireccion(auxDireccion.getText().toString());
        newDuenio.setNumero(auxNumero.getText().toString());
        auxMantenedorDuenio.insert(newDuenio);

    }

    public void agregarMascota(){

        EditText auxNombreMascota = (EditText)findViewById(R.id.txtNombreMascota);
        EditText auxFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        Spinner auxEspecie = (Spinner)findViewById(R.id.spRazaEspecie);
        Spinner auxRaza = (Spinner)findViewById(R.id.spRaza);
        RadioButton auxMacho = (RadioButton)findViewById(R.id.rbMacho);
        RadioButton auxHembra = (RadioButton)findViewById(R.id.rbHembra);
        EditText auxColor = (EditText)findViewById(R.id.txtColor);
        EditText auxRut = (EditText)findViewById(R.id.txtRut);
        String mascota = auxNombreMascota.getText().toString();
        String rut = auxRut.getText().toString();
        Mascota newMascota = new Mascota();
        MantenedorMascota auxMantenedorMascota = new MantenedorMascota(this);

        try {
            if (auxMacho.isChecked()) {
                newMascota.setNombre(auxNombreMascota.getText().toString());
                newMascota.setRut(Integer.valueOf(auxRut.getText().toString()));
                newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());
                newMascota.setEspecie(auxEspecie.getSelectedItemPosition());
                newMascota.setRaza(auxRaza.getSelectedItemPosition());
                newMascota.setSexo("Macho");
                newMascota.setColor(auxColor.getText().toString());
                auxMantenedorMascota.insert(newMascota);
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            } else if (auxHembra.isChecked()) {
                newMascota.setNombre(auxNombreMascota.getText().toString());
                newMascota.setRut(Integer.valueOf(auxRut.getText().toString()));
                newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());
                newMascota.setEspecie(auxEspecie.getSelectedItemPosition());
                newMascota.setRaza(auxRaza.getSelectedItemPosition());
                newMascota.setSexo("Hembra");
                newMascota.setColor(auxColor.getText().toString());
                auxMantenedorMascota.insert(newMascota);
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            } else {
                this.mensaje("Debe Seleccionar el Sexo del Animal");
            }
        }catch (Exception ex){
            this.mensaje("Error: "+ex.getMessage().toString());
        }

    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void mostrar(){
        MantenedorMascota auxMantenedor =new MantenedorMascota(this);

        List<Mascota> auxListaMascota = auxMantenedor.getAll();

        String[] listaString = new String[auxListaMascota.size()];

        Iterator iter = auxListaMascota.iterator();

        int pos = 0;

        while (iter.hasNext()){
            Mascota auxLista = new Mascota();

            auxLista = (Mascota) iter.next();

            listaString[pos] = auxLista.getId()+"       "+auxLista.getNombre()+"            "+auxLista.getRut();
            pos++;
        }

        ListView auxListView = (ListView)findViewById(R.id.lvTest);

        auxListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaString));
    }

    public void addMascota(View view) {
        addDuenio();
        agregarMascota();
    }
}
