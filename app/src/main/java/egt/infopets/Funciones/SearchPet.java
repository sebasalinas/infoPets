package egt.infopets.Funciones;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import egt.infopets.R;
import egt.infopets.Clases.Duenio;
import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Visitas;
import egt.infopets.Mantenedores.MantenedorDuenio;
import egt.infopets.Mantenedores.MantenedorMascota;
import egt.infopets.Mantenedores.MantenedorVisitas;

public class SearchPet extends AppCompatActivity {

    String auxVar = "";
    String auxVar2 = "";
    int varCodVisita = 0;
    String varCodMascota = "";
    String fechaDefault = "";
    EditText auxNombreM;
    ImageView mPhotoCapturedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pet);


        mPhotoCapturedImageView = (ImageView) findViewById(R.id.ibtnImagen);

        ListView auxListView = (ListView)findViewById(R.id.lvMedicamentos);
        ScrollView auxScroll = (ScrollView) findViewById(R.id.scBuscar);

        auxScroll.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.lvMedicamentos).getParent()
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

        final EditText auxCod = (EditText) findViewById(R.id.txtVisitaId);

        auxCod.setText(getIntent().getStringExtra("varCod"));

        auxVar = auxCod.getText().toString();
        varCodMascota = auxVar;

        datosMascota();
        datosDuenio();
        mostrar();

        final Button mShowDialog = (Button) findViewById(R.id.btnAgregarVisita);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuider = new AlertDialog.Builder(SearchPet.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText mCodVisita = (EditText) mView.findViewById(R.id.txtCodVisita);
                final EditText mDescripcionVisita = (EditText) mView.findViewById(R.id.txtDescripcionVisita);
                final EditText mMotivio = (EditText) mView.findViewById(R.id.txtDialogMotivo);
                final EditText mMedicamento = (EditText) mView.findViewById(R.id.txtDialogMedicamento);
                final EditText mMg = (EditText) mView.findViewById(R.id.txtDialogMg);
                final EditText mCod = (EditText) mView.findViewById(R.id.txtVisitaId);

                Button mImageButton = (Button) mView.findViewById(R.id.btnAgregarVisita);

                mBuider.setView(mView);
                final AlertDialog dialog = mBuider.create();
                dialog.show();

                mImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mDescripcionVisita.getText().toString().isEmpty()) {
                            cargaVisita(mMotivio.getText().toString() + " - " + mMedicamento.getText().toString() + " - " + mMg.getText().toString() + " - " + mDescripcionVisita.getText().toString());
                            dialog.dismiss();
                        } else {
                            mensaje("Agrege Descripcion");
                        }
                    }
                });
            }
        });
    }

    private void cargaVisita(String descripcion) {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);

        Visitas auxVisita = new Visitas();

        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));

        int year = calendarNow.get(Calendar.YEAR);
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH);

        fechaDefault = monthDay + "-" + month + "-" + year;

        auxVisita.setFechaVisita(fechaDefault);
        auxVisita.setDescripcion(descripcion);
        auxVisita.setMascota(Integer.valueOf(auxVar));

        auxMantenedor.insert(auxVisita);
        mensaje("visita agregada");
        mostrar();

    }

    public void datosDuenio() {
        MantenedorDuenio auxMantenedor = new MantenedorDuenio(this);
        EditText auxNombre = (EditText) findViewById(R.id.txtDNombre);

        Duenio auxDuenio = auxMantenedor.getByCodigo(auxVar2);

        auxNombre.setText(auxDuenio.getNombre().toString());

        File imgFile = new  File(Environment.getExternalStorageDirectory().getPath()+"/InfoPets/");

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()+"/"+auxNombreM.getText().toString()+"_"+auxVar2+".jpg");

            ImageView myImage = (ImageView) findViewById(R.id.ibtnImagen);

            myImage.setImageBitmap(myBitmap);

            mPhotoCapturedImageView.setRotation(-90);

        }
    }

    public void datosMascota() {
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);
        Mascota auxMascota = auxMantenedor.getByCodigo(auxVar);

        auxNombreM = (EditText) findViewById(R.id.txtMNombre);
        EditText auxEdad = (EditText) findViewById(R.id.txtFNacimiento);
        EditText auxRut = (EditText) findViewById(R.id.txtDNombre);

        String imgpath = auxMascota.getfNacimiento().toString();

        String anioNaciemiento = imgpath.substring(imgpath.lastIndexOf("-") + 1);
        String mesAnio = imgpath.substring(imgpath.indexOf("-") + 1, 5);

        Calendar calendarNow = new GregorianCalendar();


        int year = calendarNow.get(Calendar.YEAR);
        int month = 1 + calendarNow.get(Calendar.MONTH);
        int mes = 0;
        int anio = 0;

        if (year - (Integer.valueOf(anioNaciemiento)) <= 0) {
            mes = month - (Integer.valueOf(mesAnio));
        } else {
            anio = year - (Integer.valueOf(anioNaciemiento));
            mes = month - (Integer.valueOf(mesAnio));
        }
        String Edad = anio + "-" + mes;

        auxNombreM.setText(auxMascota.getNombre().toString());
        auxEdad.setText(Edad);
        auxRut.setText(String.valueOf(auxMascota.getRut()));

        auxVar2 = auxRut.getText().toString();

    }

    public void envioMascota(View view) {

        EditText auxCod = (EditText) findViewById(R.id.txtVisitaId);
        EditText auxNombre = (EditText) findViewById(R.id.txtMNombre);
        EditText auxNombreD = (EditText) findViewById(R.id.txtDNombre);


        Intent intent = new Intent(this, AddPet.class);

        intent.putExtra("varId", auxCod.getText().toString());
        intent.putExtra("varMascota", auxNombre.getText().toString());
        intent.putExtra("varRut", auxVar2);
        intent.putExtra("varDuenio", auxNombreD.getText().toString());
        finish();
        startActivity(intent);


    }

    public void mostrar() {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);

        int var = Integer.valueOf(auxVar);

        final List<Visitas> auxListaVisitas = auxMantenedor.getByCodigo(var);
        //final List<Visitas> auxListaVisitas = auxMantenedor.getAll();

        String[] listaString = new String[auxListaVisitas.size()];
        Iterator iter = auxListaVisitas.iterator();

        int pos = 0;

        while (iter.hasNext()) {
            Visitas auxLista = new Visitas();

            auxLista = (Visitas) iter.next();

            listaString[pos] = auxLista.getCod() + " || " + auxLista.getFechaVisita() + " || " + auxLista.getDescripcion();

            pos++;
        }

        final ListView auxListView = (ListView) findViewById(R.id.lvMedicamentos);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));

        auxListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                parent.getAdapter().toString();

                String itemValue = (String) auxListView.getItemAtPosition(position);

                AlertDialog.Builder mBuider = new AlertDialog.Builder(SearchPet.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText mCodVisita = (EditText) mView.findViewById(R.id.txtCodVisita);
                final EditText mCodMascota = (EditText) mView.findViewById(R.id.txtCodMascota);
                final EditText mDescripcionVisita = (EditText) mView.findViewById(R.id.txtDescripcionVisita);
                final EditText mMotivoo = (EditText) mView.findViewById(R.id.txtDialogMotivo);
                final EditText mMedicamento = (EditText) mView.findViewById(R.id.txtDialogMedicamento);
                final EditText mMg = (EditText) mView.findViewById(R.id.txtDialogMg);
                //final EditText mCod = (EditText) mView.findViewById(R.id.txtVisitaId);
                final ImageButton mEdit = (ImageButton) mView.findViewById(R.id.ibtnEdit);
                final ImageButton mEliminar = (ImageButton) mView.findViewById(R.id.ibtnEliminar);
                final Button mAdd = (Button) mView.findViewById(R.id.btnAgregarVisita);

                mBuider.setView(mView);
                final AlertDialog dialog = mBuider.create();
                dialog.show();

                mMg.setVisibility(View.GONE);
                mMedicamento.setVisibility(View.GONE);
                mMotivoo.setVisibility(View.GONE);


                varCodVisita = auxListaVisitas.get(position).getCod();


                String imgpath = itemValue;

                final String result = imgpath.substring(imgpath.lastIndexOf("|") + 2);


                mDescripcionVisita.setText(result);


                mEdit.setVisibility(View.VISIBLE);
                mEliminar.setVisibility(View.VISIBLE);
                mAdd.setVisibility(View.GONE);

                mEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mDescripcionVisita.getText().toString().isEmpty()) {
                            updateVisita(mDescripcionVisita.getText().toString(), varCodVisita, varCodMascota);
                            mostrar();
                            dialog.dismiss();
                        } else {
                            mensaje("Agregue descripcion de visita");
                        }
                    }
                });

                mEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mDescripcionVisita.getText().toString().isEmpty()) {
                            deleteVisita((auxListaVisitas.get(position).getCod()));
                            mostrar();
                            dialog.dismiss();
                        } else {
                            mensaje("Debe especificar el nombre de la mascota");
                        }
                    }
                });


            }
        });
    }

    private void deleteVisita(int id) {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);

        auxMantenedor.delete(id);
    }

    private void updateVisita(String Descripcion, int CodVisita, String codMascota) {
        MantenedorVisitas auxMantenedor = new MantenedorVisitas(this);
        Visitas auxVisita = new Visitas();

        auxVisita.setCod(CodVisita);
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));

        int year = calendarNow.get(Calendar.YEAR);
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH);

        fechaDefault = monthDay + "-" + month + "-" + year;
        auxVisita.setFechaVisita(fechaDefault);
        auxVisita.setDescripcion(Descripcion);
        auxVisita.setMascota(Integer.valueOf(codMascota));
        auxMantenedor.update(auxVisita);
        mensaje("Se actualizo visita correctamente");
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
