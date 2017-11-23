package egt.infopets.Vistas;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import egt.infopets.R;
import egt.infopets.Clases.Duenio;
import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Mascota;
import egt.infopets.Clases.Raza;
import egt.infopets.Mantenedores.SQLite.MantenedorDuenio;
import egt.infopets.Mantenedores.SQLite.MantenedorEspecie;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;
import egt.infopets.Mantenedores.SQLite.MantenedorRaza;

public class AddPet extends AppCompatActivity {

    Spinner spEspecie;
    Spinner spRaza;
    List<Especie> auxListaEspecie;
    ArrayList<Raza> auxListaRaza;
    String[] listaStringEspecie;
    String[] listaStringRaza;
    Spinner auxEspecie;
    Spinner auxRaza;
    String auxVarRut = "";
    EditText auxNombreMascota;
    EditText auxRut;
    EditText fechaNacimientoMascota;

    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private ImageView mPhotoCapturedImageView;
    private String mImageFileLocation;
    int targetImageViewWidth;
    int targetImageViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }

        fechaNacimientoMascota = (EditText) findViewById(R.id.txtFechaNacimiento);
        fechaNacimientoMascota.setInputType(InputType.TYPE_NULL);


        mPhotoCapturedImageView = (ImageView) findViewById(R.id.btnCamara);

        EditText auxId = (EditText) findViewById(R.id.txtId);
        auxNombreMascota = (EditText) findViewById(R.id.txtNombreMascota);
        auxRut = (EditText) findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText) findViewById(R.id.txtNombreDuenio);
        ImageButton btnEdit = (ImageButton) findViewById(R.id.ibtnAdd_EditPet);

        if (getIntent().getStringExtra("varId") != null) {
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

        ArrayAdapter<CharSequence> adaptadorEspecie = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaStringEspecie);

        spEspecie.setAdapter(adaptadorEspecie);

        consultaListaRaza();

        ArrayAdapter<CharSequence> adaptadorRaza = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaStringRaza);

        spRaza.setAdapter(adaptadorRaza);

        //fin carga spinner

    }

    private void consultaListaEspecies() {
        MantenedorEspecie auxMantenedorEspecie = new MantenedorEspecie(this);

        auxListaEspecie = auxMantenedorEspecie.getAll();

        listaStringEspecie = new String[auxListaEspecie.size() + 1];
        listaStringEspecie[0] = "Seleccione...";
        for (int i = 0; i < auxListaEspecie.size(); i++)
            listaStringEspecie[i + 1] = auxListaEspecie.get(i).getSpecie();
    }

    private void consultaListaRaza() {
        MantenedorRaza auxMantenedorRaza = new MantenedorRaza(this);

        auxListaRaza = auxMantenedorRaza.getAll();

        listaStringRaza = new String[auxListaRaza.size() + 1];
        listaStringRaza[0] = "Seleccione...";
        for (int i = 0; i < auxListaRaza.size(); i++)
            listaStringRaza[i + 1] = auxListaRaza.get(i).getDescripcion();
    }

    public void addDuenio() {

        EditText auxRut = (EditText) findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText) findViewById(R.id.txtNombreDuenio);
        EditText auxDireccion = (EditText) findViewById(R.id.txtDireccion);
        EditText auxNumero = (EditText) findViewById(R.id.txtNumero);

        Duenio newDuenio = new Duenio();
        MantenedorDuenio auxMantenedorDuenio = new MantenedorDuenio(this);

        newDuenio.setRut(auxRut.getText().toString());
        newDuenio.setNombre(auxNombreDuenio.getText().toString());
        newDuenio.setDireccion(auxDireccion.getText().toString());
        newDuenio.setNumero(auxNumero.getText().toString());

        auxMantenedorDuenio.insert(newDuenio);

    }

    public boolean validar() {
        EditText auxId = (EditText) findViewById(R.id.txtId);

        boolean var = false;
        if (auxId.getText().toString().isEmpty()) {
            var = false;
        } else if (Integer.valueOf(auxId.getText().toString()) > 0) {
            var = true;
        }
        return var;
    }

    public boolean validarExtras() {
        auxEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
        auxRaza = (Spinner) findViewById(R.id.spRaza);
        RadioButton auxMacho = (RadioButton) findViewById(R.id.rbMacho);
        RadioButton auxHembra = (RadioButton) findViewById(R.id.rbHembra);

        boolean var = false;
        if (auxEspecie.getSelectedItemPosition() > 0) {
            var = true;
        } else {
            var = false;
        }
        if (auxRaza.getSelectedItemPosition() > 0) {
            var = true;
        } else {
            var = false;
        }
        if (auxMacho.isChecked() || auxHembra.isChecked()) {
            var = true;
        } else {
            var = false;
        }
        return var;
    }

    public void agregarMascota() {

        EditText auxNombreMascota = (EditText) findViewById(R.id.txtNombreMascota);
        EditText auxFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        auxEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
        auxRaza = (Spinner) findViewById(R.id.spRaza);
        RadioButton auxMacho = (RadioButton) findViewById(R.id.rbMacho);
        RadioButton auxHembra = (RadioButton) findViewById(R.id.rbHembra);
        EditText auxColor = (EditText) findViewById(R.id.txtColor);
        EditText auxRut = (EditText) findViewById(R.id.txtRut);

        String mascota = auxNombreMascota.getText().toString();
        String rut = auxRut.getText().toString();

        Mascota newMascota = new Mascota();

        MantenedorMascota auxMantenedorMascota = new MantenedorMascota(this);

        try {
            newMascota.setNombre(auxNombreMascota.getText().toString());
            newMascota.setRut(auxRut.getText().toString());
            newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());

            newMascota.setRaza(auxRaza.getSelectedItemPosition());
            newMascota.setColor(auxColor.getText().toString());
            boolean guarda = false;
            if (auxMacho.isChecked()) {
                newMascota.setSexo("Macho");
            } else if (auxHembra.isChecked()) {
                newMascota.setSexo("Hembra");
            } else {
                guarda = false;
            }

            if (auxEspecie.getSelectedItemPosition() > 0) {
                newMascota.setEspecie(auxEspecie.getSelectedItemPosition());
            } else {
                guarda = false;
            }
            if (auxRaza.getSelectedItemPosition() > 0) {
                newMascota.setEspecie(auxEspecie.getSelectedItemPosition());
                guarda = true;
            } else {
                guarda = false;
            }
            if (guarda) {
                auxMantenedorMascota.insert(newMascota);
                this.mensaje("Mascota:" + mascota + "|| Dueño: " + rut);
            } else {
                this.mensaje("SELECCIONE EL SEXO");
            }
        } catch (Exception ex) {
            this.mensaje("ERRO: " + ex.getMessage().toString());
        }
    }

    public void eliminarMascota(View view) {
        EditText auxId = (EditText) findViewById(R.id.txtId);
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);

        auxMantenedor.delete(Integer.valueOf(auxId.getText().toString()));
        finish();
        mensaje("REGISTRO ELIMINADO");
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void cargaDatos() {

        EditText auxId = (EditText) findViewById(R.id.txtId);
        EditText auxNombreMascota = (EditText) findViewById(R.id.txtNombreMascota);
        EditText auxFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        Spinner auxSpinnerEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
        Spinner auxSpinnerRaza = (Spinner) findViewById(R.id.spRaza);
        RadioButton auxRbMacho = (RadioButton) findViewById(R.id.rbMacho);
        RadioButton auxRbHembra = (RadioButton) findViewById(R.id.rbHembra);
        EditText auxColor = (EditText) findViewById(R.id.txtColor);
        EditText auxRut = (EditText) findViewById(R.id.txtRut);
        EditText auxNombreDuenio = (EditText) findViewById(R.id.txtNombreDuenio);
        EditText auxDireccion = (EditText) findViewById(R.id.txtDireccion);
        EditText auxNumero = (EditText) findViewById(R.id.txtNumero);

        MantenedorMascota auxMantMascota = new MantenedorMascota(this);
        MantenedorDuenio auxMantDuenio = new MantenedorDuenio(this);

        Duenio auxDuenio = auxMantDuenio.getByCodigo(auxRut.getText().toString());


        auxDireccion.setText(auxDuenio.getDireccion());
        auxNumero.setText(auxDuenio.getNumero());

        Mascota auxMascota = auxMantMascota.getByCodigo(auxId.getText().toString());

        auxFechaNacimiento.setText(auxMascota.getfNacimiento());
        auxColor.setText(auxMascota.getColor());

        auxSpinnerEspecie.setSelection(auxMascota.getEspecie());

        auxSpinnerEspecie.setSelection(Integer.valueOf(auxMascota.getEspecie()));
        auxSpinnerRaza.setSelection(Integer.valueOf(auxMascota.getRaza()));


        String auxVar = auxMascota.getSexo().toString();


        if (auxVar.equalsIgnoreCase("Macho")) {
            auxRbMacho.setChecked(true);
        } else {
            auxRbHembra.setChecked(true);
        }

        File imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/InfoPets/" + auxNombreMascota.getText().toString() + "_" + auxRut.getText().toString() + ".jpg");

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.btnCamara);

            myImage.setImageBitmap(myBitmap);

            mPhotoCapturedImageView.setRotation(-90);

        }

    }

    public void updateMascota() {
        try {
            EditText auxId = (EditText) findViewById(R.id.txtId);
            EditText auxNMascota = (EditText) findViewById(R.id.txtNombreMascota);
            EditText auxFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
            Spinner auxSpinnerEspecie = (Spinner) findViewById(R.id.spRazaEspecie);
            Spinner auxSpinnerRaza = (Spinner) findViewById(R.id.spRaza);
            RadioButton auxRbMacho = (RadioButton) findViewById(R.id.rbMacho);
            RadioButton auxRbHembra = (RadioButton) findViewById(R.id.rbHembra);
            EditText auxColor = (EditText) findViewById(R.id.txtColor);
            EditText auxRut = (EditText) findViewById(R.id.txtRut);
            EditText auxNombreDuenio = (EditText) findViewById(R.id.txtNombreDuenio);
            EditText auxNumero = (EditText) findViewById(R.id.txtNumero);
            EditText auxDireccion = (EditText) findViewById(R.id.txtDireccion);

            MantenedorMascota auxMantenedor = new MantenedorMascota(this);
            Mascota newMascota = new Mascota();

            newMascota.setId(Integer.valueOf(auxId.getText().toString()));
            newMascota.setNombre(auxNMascota.getText().toString());
            newMascota.setRut(auxRut.getText().toString());
            newMascota.setfNacimiento(auxFechaNacimiento.getText().toString());
            newMascota.setEspecie(auxSpinnerEspecie.getSelectedItemPosition());
            newMascota.setRaza(auxSpinnerRaza.getSelectedItemPosition());
            newMascota.setColor(auxColor.getText().toString());

            String rut = auxRut.getText().toString();

            if (auxRbMacho.isChecked()) {
                newMascota.setSexo("Macho");

                auxMantenedor.update(newMascota);
                String mascota = auxNMascota.getText().toString();
            } else if (auxRbHembra.isChecked()) {
                newMascota.setSexo("Hembra");

                auxMantenedor.update(newMascota);
                String mascota = auxNMascota.getText().toString();
            } else {
                mensaje("ERROR SELECCION DE SEXO");
            }

            MantenedorDuenio auxMan = new MantenedorDuenio(this);
            Duenio newDuenio = new Duenio();

            newDuenio.setRut(auxRut.getText().toString());
            newDuenio.setNombre(auxNombreDuenio.getText().toString());
            newDuenio.setDireccion(auxDireccion.getText().toString());
            newDuenio.setNumero(auxNumero.getText().toString());

            auxMan.update(newDuenio);

        } catch (Exception ex) {
            this.mensaje("ERROR AL AGREGAR " + ex);
        }
    }

    private boolean validarDigitoVerificador(String rut) {
        boolean auxVar = false;
        if (rut.length() > 1) {
            String rutSinDigito = rut.substring(0, rut.length() - 2);
            String[] rutSplit = rutSinDigito.split("");
            int suma = 0;
            int multiplicador = 2;
            for (int i = rutSplit.length - 1; i > 0; i--) {
                suma += (Integer.parseInt(rutSplit[i]) * multiplicador);
                if (multiplicador == 7)
                    multiplicador = 2;
                else
                    ++multiplicador;
            }
            int resto = suma % 11;
            int digitoNumero = 11 - resto;
            String digito = String.valueOf(digitoNumero);

            if (digitoNumero == 11) digito = "0";
            if (digitoNumero == 10) digito = "K";

            if (digito.equals(rut.substring(rut.length() - 1)))
                auxVar = true;

        }
        return auxVar;
    }

    public void addMascota(View view) {
        EditText auxRut = (EditText) findViewById(R.id.txtRut);
        EditText auxNombre = (EditText) findViewById(R.id.txtNombreDuenio);

        auxVarRut = auxRut.getText().toString();
        if (validar()) {
            updateMascota();
            mensaje("REGISTROS ACTUALIZADOS");
            finish();
        } else {
            if (validarDigitoVerificador(auxVarRut) && !auxNombre.getText().toString().isEmpty() && validarExtras() && mPhotoCapturedImageView.getDrawable() != null) {
                addDuenio();
                agregarMascota();
                mensaje("MASCOTA AGREGADA ");
                finish();
            } else {
                mensaje("FALTAN DATOS");
            }

        }
    }

    public void takePhoto(View view) {
        try {


            if (!auxRut.getText().toString().isEmpty() || !auxNombreMascota.getText().toString().isEmpty()) {
                Intent callCameraApplicationIntent = new Intent();
                callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                File photoFile = null;
                try {
                    photoFile = createImageFile();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                String authorities = getApplicationContext().getPackageName() + ".fileprovider";
                Uri imageUri = FileProvider.getUriForFile(this, authorities, photoFile);
                callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);

            } else {
                mensaje("Ingrese el nombre de la mascota y el rut de la persona");
            }
        } catch (Exception ex) {
            mensaje("Ha ocurrido un erro al intentar sacar una foto");
        }

    }

    File createImageFile() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/InfoPets/");
        file.mkdirs();
        String imageFileName = auxNombreMascota.getText().toString() + "_" + auxRut.getText().toString() + ".jpg";
        File storageDirectory = new File(Environment.getExternalStorageDirectory().getPath(), "/InfoPets");
        File image = new File(storageDirectory, imageFileName);
        mImageFileLocation = image.getAbsolutePath();

        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "Picture taken successfully", Toast.LENGTH_SHORT).show();
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            //Bundle extras = data.getBundleExtra("InfoPets");
            //Bitmap photoCapturedBitmap = (Bitmap) extras.get("data");
            //mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
            //photoCapturedBitmap = BitmapFactory.decodeFile(mImageFileLocation);
            //mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
        }
        setReducedImageSize();
        targetImageViewWidth = mPhotoCapturedImageView.getWidth();
        targetImageViewHeight = mPhotoCapturedImageView.getHeight();
    }

    void setReducedImageSize() {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        int cameraImageWidth = 150;
        int cameraImageHeight = 168;

        int scaleFactor = Math.min(cameraImageWidth, cameraImageHeight );
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inJustDecodeBounds = false;

        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        mPhotoCapturedImageView.setImageBitmap(photoReducedSizeBitmp);


    }

    public void selectDate(View view) {
        Calendar cal = Calendar.getInstance();
        int auxDay = cal.get(Calendar.DAY_OF_MONTH);
        int auxMonth = cal.get(Calendar.MONTH);
        int auxYear = cal.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(AddPet.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                fechaNacimientoMascota.setText(day + "-" + month + "-" + year);
            }
        }, auxYear, auxMonth, auxDay);
        dpd.show();

    }


}
