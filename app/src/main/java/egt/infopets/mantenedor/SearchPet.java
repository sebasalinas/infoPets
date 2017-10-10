package egt.infopets.mantenedor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import egt.infopets.R;
import egt.infopets.clases.Duenio;
import egt.infopets.clases.Mascota;
import egt.infopets.clases.Visitas;
import egt.infopets.db.MantenedorDuenio;
import egt.infopets.db.MantenedorMascota;
import egt.infopets.db.MantenedorVisitas;

public class SearchPet extends AppCompatActivity {
    String auxVar = "";
    String auxVar2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pet);

        final EditText auxCod = (EditText)findViewById(R.id.txtVisitaId);

        auxCod.setText(getIntent().getStringExtra("varCod"));

        auxVar = auxCod.getText().toString();

        datosMascota();
        datosDuenio();
        mostrar();
        final Button mShowDialog = (Button) findViewById(R.id.btnAgregarVisita);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuider = new AlertDialog.Builder(SearchPet.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog,null);
                final EditText mCodVisita = (EditText)mView.findViewById(R.id.txtCodVisita);
                final EditText mDescripcionVisita = (EditText)mView.findViewById(R.id.txtDescripcionVisita);
                final EditText mMotivio = (EditText)mView.findViewById(R.id.txtDialogMotivo);
                final EditText mMedicamento = (EditText)mView.findViewById(R.id.txtDialogMedicamento);
                final EditText mMg = (EditText)mView.findViewById(R.id.txtDialogMg);
                final EditText mCod = (EditText)mView.findViewById(R.id.txtVisitaId);

                Button mImageButton = (Button)mView.findViewById(R.id.btnAgregarVisita);

                mImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mDescripcionVisita.getText().toString().isEmpty()){
                            cargaVisita(mDescripcionVisita.getText().toString());

                        }else {
                            mensaje("Agrege Descripcion");
                        }
                    }
                });
                mBuider.setView(mView);
                AlertDialog dialog = mBuider.create();
                dialog.show();
            }
        });
    }

    private void cargaVisita(String descripcion) {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);

        Visitas auxVisita = new Visitas();

        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));

        int year = calendarNow.get(Calendar.YEAR);
        int monthDay =calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH);

        String fechaDefault = monthDay+"-"+month+"-"+year;

        auxVisita.setFechaVisita(fechaDefault);
        auxVisita.setDescripcion(descripcion);
        auxVisita.setMascota(Integer.valueOf(auxVar));

        auxMantenedor.insert(auxVisita);
        mensaje("visita agregada");
        mostrar();

    }

    public void datosDuenio(){
        MantenedorDuenio auxMantenedor = new MantenedorDuenio(this);
        EditText auxNombre = (EditText)findViewById(R.id.txtDNombre);

        Duenio auxDuenio = auxMantenedor.getByCodigo(auxVar2);

        auxNombre.setText(auxDuenio.getNombre().toString());
    }

    public void datosMascota() {
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);
        Mascota auxMascota = auxMantenedor.getByCodigo(Integer.valueOf(auxVar));

        EditText auxNombre = (EditText) findViewById(R.id.txtMNombre);
        EditText auxEdad = (EditText) findViewById(R.id.txtFNacimiento);
        EditText auxRut = (EditText) findViewById(R.id.txtDNombre);


        auxNombre.setText(auxMascota.getNombre().toString());
        auxEdad.setText(auxMascota.getfNacimiento().toString());
        auxRut.setText(String.valueOf(auxMascota.getRut()));

        auxVar2 = auxRut.getText().toString();

    }

    public void envioMascota(View view) {

        EditText auxCod =(EditText)findViewById(R.id.txtVisitaId);
        EditText auxNombre = (EditText)findViewById(R.id.txtMNombre);
        EditText auxNombreD = (EditText)findViewById(R.id.txtDNombre);


        Intent intent = new Intent(this,AddPet.class);

        intent.putExtra("varId",auxCod.getText().toString());
        intent.putExtra("varMascota",auxNombre.getText().toString());
        intent.putExtra("varRut",auxVar2);
        intent.putExtra("varDuenio",auxNombreD.getText().toString());
        finish();
        startActivity(intent);


    }

    public void mostrar(){
        MantenedorVisitas auxMantenedor =new MantenedorVisitas(this);

        int var = Integer.valueOf( auxVar );

        final List<Visitas> auxListaVisitas = auxMantenedor.getByCodigo(var);

        String[] listaString = new String[auxListaVisitas.size()];
        Iterator iter = auxListaVisitas.iterator();

        int pos = 0;

        while (iter.hasNext()){
            Visitas auxLista = new Visitas();

            auxLista = (Visitas) iter.next();

            listaString[pos] = auxLista.getCod() + " || " + auxLista.getFechaVisita()+ " || "+auxLista.getDescripcion();
            pos++;
        }

        final ListView auxListView = (ListView)findViewById(R.id.lvMedicamentos);

        auxListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaString));

        auxListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                parent.getAdapter().toString();

                String itemValue    = (String)   auxListView.getItemAtPosition(position);

                AlertDialog.Builder mBuider = new AlertDialog.Builder(SearchPet.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog,null);
                final EditText mCodVisita = (EditText)mView.findViewById(R.id.txtCodVisita);
                final EditText mDescripcionVisita = (EditText)mView.findViewById(R.id.txtDescripcionVisita);
                final EditText mMotivio = (EditText)mView.findViewById(R.id.txtDialogMotivo);
                final EditText mMedicamento = (EditText)mView.findViewById(R.id.txtDialogMedicamento);
                final EditText mMg = (EditText)mView.findViewById(R.id.txtDialogMg);
                final EditText mCod = (EditText)mView.findViewById(R.id.txtVisitaId);
                final ImageButton mEdit = (ImageButton) mView.findViewById(R.id.ibtnEdit);
                final ImageButton mEliminar = (ImageButton) mView.findViewById(R.id.ibtnEliminar);
                final Button mAdd = (Button) mView.findViewById(R.id.btnAgregarVisita);

                mMg.setVisibility(View.GONE);
                mMedicamento.setVisibility(View.GONE);
                mMotivio.setVisibility(View.GONE);

                mDescripcionVisita.setText(itemValue);


                mEdit.setVisibility(View.VISIBLE);
                mEliminar.setVisibility(View.VISIBLE);
                mAdd.setVisibility(View.GONE);

                mEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mDescripcionVisita.getText().toString().isEmpty()){
                            updateVisita(mDescripcionVisita.getText().toString(),auxListaVisitas.get(position).getCod());
                        }else {
                            mensaje("Agregue descripcion de visita");
                        }
                    }
                });

                mEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mDescripcionVisita.getText().toString().isEmpty()){
                            //deleteEspecie((auxListaEspecie.get(position).getId()));
                            mensaje("Mascota eliminada");

                        }else {
                            mensaje("Debe especificar el nombre de la mascota");
                        }
                    }
                });
                mBuider.setView(mView);
                AlertDialog dialog = mBuider.create();
                dialog.show();
                mostrar();
            }
        });
    }

    private void updateVisita(String Descripcion,int Cod) {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);
        Visitas auxVisita = new Visitas();

        auxVisita.setCod(Cod);
        auxVisita.setDescripcion(Descripcion);

        auxMantenedor.update(auxVisita);
        mostrar();
        mensaje("Se actualizo visita correctamente");
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
