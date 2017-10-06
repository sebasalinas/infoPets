package egt.infopets.mantenedor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.clases.Duenio;
import egt.infopets.clases.Mascota;
import egt.infopets.db.MantenedorDuenio;
import egt.infopets.db.MantenedorMascota;

public class SearchPet extends AppCompatActivity {
    String auxVar = "";
    String auxVar2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pet);

        EditText auxCod = (EditText)findViewById(R.id.txtCod);

        auxCod.setText(getIntent().getStringExtra("varCod"));
        auxVar = auxCod.getText().toString();

        datosMascota();
        datosDuenio();
    }

    public void datosDuenio(){
        MantenedorDuenio auxMantenedor = new MantenedorDuenio(this);
        EditText auxNombre = (EditText)findViewById(R.id.txtDNombre);
        Duenio auxDuenio = auxMantenedor.getByCodigoTicket(auxVar2);

        auxNombre.setText(auxDuenio.getNombre().toString());
    }

    public void datosMascota(){
        MantenedorMascota auxMantenedor = new MantenedorMascota(this);
        Mascota auxMascota = auxMantenedor.getByCodigo(Integer.valueOf(auxVar));

        EditText auxNombre =(EditText)findViewById(R.id.txtMNombre);
        EditText auxEdad = (EditText)findViewById(R.id.txtFNacimiento);
        EditText auxRut = (EditText)findViewById(R.id.txtDNombre);

        auxNombre.setText(auxMascota.getNombre().toString());
        auxEdad.setText(auxMascota.getfNacimiento().toString());
        auxRut.setText(String.valueOf(auxMascota.getRut()));
        auxVar2 = auxRut.getText().toString();

    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
