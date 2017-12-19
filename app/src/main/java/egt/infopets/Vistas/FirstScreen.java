package egt.infopets.Vistas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.Clases.Especie;
import egt.infopets.Clases.Visitas;
import egt.infopets.Mantenedores.SQLite.MantenedorEspecie;
import egt.infopets.Mantenedores.SQLite.MantenedorVisitas;
import egt.infopets.R;
import egt.infopets.Clases.Mascota;
import egt.infopets.Mantenedores.SQLite.DbInfoPet;
import egt.infopets.Mantenedores.SQLite.MantenedorMascota;

public class FirstScreen extends AppCompatActivity {

    private DbInfoPet conector;
    private Context context;
    private String tabla;
    String[] listaStringSeleccion;
    Spinner spInfoSpinner;String auxVar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        final MantenedorEspecie mantenedorEspecie =new MantenedorEspecie(this);
        final  ImageButton mShowDialog = ( ImageButton) findViewById(R.id.btnInfoEstadistica);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuider = new AlertDialog.Builder(FirstScreen.this);
                final View mView = getLayoutInflater().inflate(R.layout.informe, null);
                final EditText mTextBusca = (EditText) mView.findViewById(R.id.txtInfoBuscars);
                Button mImageButton = (Button) mView.findViewById(R.id.btnInfoBuscars);
                ListView mLista = (ListView) mView.findViewById(R.id.lvInfoListars);

                mBuider.setView(mView);
                final AlertDialog dialog = mBuider.create();
                dialog.show();

                mImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });
            }
        });
    }

    public void mostrar(){

    }

    public void goToInfoEstadisticas(View view){
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }

    public void goToEstadisticas(View view){

    }

    public void goToAddPet(View view) {
        Intent intent = new Intent(this, AddPet.class);
        startActivity(intent);
    }

    public void goToRaza(View view) {
        Intent intent = new Intent(this, AddRaza.class);
        startActivity(intent);
    }

    public void goToEspecie(View view){
        Intent intent = new Intent(this, AddEspecie.class);
        startActivity(intent);
    }

    public void goToSearchPet(View view) {

        try {

            EditText auxId = (EditText) findViewById(R.id.txtInfoBuscars);
            String auxVar = auxId.getText().toString();
            if (searchPetById(auxId.getText().toString())){

                Intent intent = new Intent(this, SearchPet.class);

                this.mensaje("La mascota ha sido encontrada");

                intent.putExtra("varCod",auxVar);

                auxId.setText("");
                startActivity(intent);
            }
            else {
                this.mensaje("No se encontro Mascota");
            }
        }
        catch (Exception ex){
            this.mensaje("Codigo no encontrado ");
        }
    }

    public boolean searchPetById(String id) {
        MantenedorMascota auxMantenedor =new MantenedorMascota(this);
        Mascota auxListaMascota = auxMantenedor.getByCodigo(id);

        if (auxListaMascota.getId()>0){
            return true;
        }
        else return false;

    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

}
