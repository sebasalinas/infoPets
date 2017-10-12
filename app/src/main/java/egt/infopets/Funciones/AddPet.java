package egt.infopets.Funciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import egt.infopets.R;
import egt.infopets.Clases.Duenio;
import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Raza;
import egt.infopets.Mantenedores.MantenedorDuenio;
import egt.infopets.Mantenedores.MantenedorEspecie;
import egt.infopets.Mantenedores.MantenedorMascota;
import egt.infopets.Mantenedores.MantenedorRaza;

public class AddPet extends AppCompatActivity {

    Spinner spEspecie;
    Spinner spRaza;
    List<Especie> auxListaEspecie;
    ArrayList<Raza> auxListaRaza;
    String[] listaStringEspecie;
    String[] listaStringRaza;
    Spinner auxEspecie;
    String auxVarRut = "";

    //el codigo debe ser de  de maximo 8 digitos comenzando por el 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        EditText auxId = (EditText)findViewById(R.id.txtId);
        EditText auxNombreMascota = (EditText)findViewById(R.id.txtNombreMascota);
        EditText auxRut = (EditText)findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText)findViewById(R.id.txtNombreDuenio);
        ImageButton btnEdit = (ImageButton)findViewById(R.id.ibtnAdd_EditPet);

        if (getIntent().getStringExtra("varId")!=null){
            auxId.setText(getIntent().getStringExtra("varId"));
            auxNombreMascota.setText(getIntent().getStringExtra("varMascota"));
            auxRut.setText(getIntent().getStringExtra("varRut"));
            auxNombreDuenio.setText(getIntent().getStringExtra("varDuenio"));
            auxRut.setEnabled(false);
            btnEdit.setVisibility(View.VISIBLE);


            cargaDatos();
        }

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

    public boolean validar(){
        EditText auxId = (EditText)findViewById(R.id.txtId);

        boolean var = false;
        if (auxId.getText().toString().isEmpty()){
            var = false;
        }
        else if(Integer.valueOf(auxId.getText().toString())>0){
            var = true;
        }
        return var;
    }

    public void agregarMascota(){

        EditText auxNombreMascota = (EditText)findViewById(R.id.txtNombreMascota);
        EditText auxFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        auxEspecie = (Spinner)findViewById(R.id.spRazaEspecie);
        Spinner auxRaza = (Spinner)findViewById(R.id.spRaza);
        RadioButton auxMacho = (RadioButton)findViewById(R.id.rbMacho);
        RadioButton auxHembra = (RadioButton)findViewById(R.id.rbHembra);
        EditText auxColor = (EditText)findViewById(R.id.txtColor);
        EditText auxRut = (EditText)findViewById(R.id.txtRut);

        String mascota = auxNombreMascota.getText().toString();
        String rut = auxRut.getText().toString();

        Mascota newMascota = new Mascota();

        MantenedorMascota auxMantenedorMascota = new MantenedorMascota(this);

        try
        {
            newMascota.setNombre(auxNombreMascota.getText().toString());
            newMascota.setRut(auxRut.getText().toString());
            newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());
            newMascota.setEspecie(auxEspecie.getSelectedItemPosition());
            newMascota.setRaza(auxRaza.getSelectedItemPosition());
            newMascota.setColor(auxColor.getText().toString());

            if (auxMacho.isChecked()){
                newMascota.setSexo("Macho");
                auxMantenedorMascota.insert(newMascota);
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            }
            else if(auxHembra.isChecked()){
                newMascota.setSexo("Hembra");
                auxMantenedorMascota.insert(newMascota);
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            }
            else {
                this.mensaje("Debe Seleccionar el Sexo del Animal");
            }
            finish();
        }
        catch (Exception ex)
        {
        this.mensaje("Error: "+ex.getMessage().toString());
        }
    }

    public void eliminarMascota(View view){
        EditText auxId = (EditText)findViewById(R.id.txtId);
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);

        auxMantenedor.delete(Integer.valueOf(auxId.getText().toString()));
        finish();
        mensaje("Mascota eliminada");
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void cargaDatos(){

        EditText auxId = (EditText)findViewById(R.id.txtId);
        EditText auxNombreMascota = (EditText)findViewById(R.id.txtMNombre);
        EditText auxFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        Spinner auxSpinnerEspecie = (Spinner)findViewById(R.id.spRazaEspecie);
        Spinner auxSpinnerRaza = (Spinner)findViewById(R.id.spRaza);
        RadioButton auxRbMacho = (RadioButton)findViewById(R.id.rbMacho);
        RadioButton auxRbHembra = (RadioButton)findViewById(R.id.rbHembra);
        EditText auxColor = (EditText)findViewById(R.id.txtColor);
        EditText auxRut = (EditText)findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText)findViewById(R.id.txtDNombre);
        EditText auxDireccion = (EditText)findViewById(R.id.txtDireccion) ;
        EditText auxNumero = (EditText)findViewById(R.id.txtNumero);

        MantenedorMascota auxMantMascota = new MantenedorMascota(this);
        MantenedorDuenio auxMantDuenio = new MantenedorDuenio(this);

        Duenio auxDuenio = auxMantDuenio.getByCodigo(auxRut.getText().toString());


        auxDireccion.setText(auxDuenio.getDireccion());
        auxNumero.setText(auxDuenio.getNumero());

        Mascota auxMascota = new Mascota();
        auxMascota = auxMantMascota.getByCodigo(auxId.getText().toString());

        auxFechaNacimiento.setText(auxMascota.getfNacimiento());
        auxColor.setText(auxMascota.getColor());

        auxSpinnerEspecie.setSelection(auxMascota.getEspecie());

        auxSpinnerEspecie.setSelection(Integer.valueOf(auxMascota.getEspecie()));
        auxSpinnerRaza.setSelection(Integer.valueOf(auxMascota.getRaza()));


        String auxVar = auxMascota.getSexo().toString();


        if ( auxVar.equalsIgnoreCase("Macho")){
            auxRbMacho.setChecked(true);
        }
        else {
            auxRbHembra.setChecked(true);
        }


    }

    public void updateMascota(){

        EditText auxId = (EditText)findViewById(R.id.txtId);
        EditText auxNombreMascota = (EditText)findViewById(R.id.txtNombreMascota);
        EditText auxFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        Spinner auxSpinnerEspecie = (Spinner)findViewById(R.id.spRazaEspecie);
        Spinner auxSpinnerRaza = (Spinner)findViewById(R.id.spRaza);
        RadioButton auxRbMacho = (RadioButton)findViewById(R.id.rbMacho);
        RadioButton auxRbHembra = (RadioButton)findViewById(R.id.rbHembra);
        EditText auxColor = (EditText)findViewById(R.id.txtColor);
        EditText auxRut = (EditText)findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText)findViewById(R.id.txtNombreDuenio);
        EditText auxNumero = (EditText)findViewById(R.id.txtNumero);
        EditText auxDireccion= (EditText)findViewById(R.id.txtDireccion);

        MantenedorMascota auxMantenedor = new MantenedorMascota(this);
        Mascota newMascota = new Mascota();

        MantenedorDuenio auxMan = new MantenedorDuenio(this);
        Duenio newDuenio = new Duenio();

        newMascota.setId(Integer.valueOf(auxId.getText().toString()));
        newMascota.setNombre(auxNombreMascota.getText().toString());
        newMascota.setRut(auxRut.getText().toString());
        newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());
        newMascota.setEspecie(auxSpinnerEspecie.getSelectedItemPosition());
        newMascota.setRaza(auxSpinnerRaza.getSelectedItemPosition());
        newMascota.setColor(auxColor.getText().toString());
        newDuenio.setRut(auxRut.getText().toString());
        newDuenio.setNombre(auxNombreDuenio.getText().toString());
        newDuenio.setDireccion(auxDireccion.getText().toString());
        newDuenio.setNumero(auxNumero.getText().toString());

        auxMan.update(newDuenio);


        String rut = auxRut.getText().toString();
        auxMantenedor.update(newMascota);
        try {
            if (auxRbMacho.isChecked()) {
                newMascota.setSexo("Macho");

                auxMantenedor.update(newMascota);
                String mascota = auxNombreMascota.getText().toString();
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            } else if (auxRbHembra.isChecked()) {
                newMascota.setSexo("Hembra");

                auxMantenedor.update(newMascota);
                String mascota = auxNombreMascota.getText().toString();
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            } else {
                mensaje("Error al seleccionar el sexo");
            }
        }
        catch (Exception ex){
            this.mensaje("Erro al intentar agregar "+ex);
        }
    }

    private boolean validarDigitoVerificador(String rut){
        boolean auxVar = false;
        if(rut.length() > 1) {
            String rutSinDigito = rut.substring(0, rut.length() - 2);
            String[] rutSplit = rutSinDigito.split("");
            int suma = 0;
            int multiplicador = 2;
            for(int i = rutSplit.length - 1; i > 0; i--){
                suma += (Integer.parseInt(rutSplit[i]) * multiplicador);
                if(multiplicador == 7)
                    multiplicador = 2;
                else
                    ++multiplicador;
            }
            int resto = suma % 11;
            int digitoNumero = 11 - resto;
            String digito = String.valueOf(digitoNumero);

            if(digitoNumero == 11) digito = "0";
            if(digitoNumero == 10) digito = "K";

            if(digito.equals(rut.substring(rut.length()-1)))
                auxVar = true;

        }
        return auxVar;
    }

    public void addMascota(View view) {
        EditText auxRut = (EditText) findViewById(R.id.txtRut);
        EditText auxNombre = (EditText)findViewById(R.id.txtNombreDuenio);
        auxVarRut = auxRut.getText().toString();


        if (validar()) {
            updateMascota();
            finish();
        }
        else {
            if (validarDigitoVerificador(auxVarRut) && !auxNombre.getText().toString().isEmpty()){
                addDuenio();
                agregarMascota();
                finish();
            }
            else {
                mensaje("falta o erro de datos");
            }

        }
    }
}
