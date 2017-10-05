package egt.infopets.mantenedor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.clases.Especie;

/**
 * Created by cetecom on 05-10-2017.
 */

public class AddEspecie extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_raza);
    }

    public void addEspecie(View view) {
        try {
            EditText auxEspecie = (EditText) findViewById(R.id.txtNewEspecie);
            RadioButton auxActivo = (RadioButton) findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton) findViewById(R.id.rbInactivo);

            Especie newEspecie = new Especie();
            if (auxActivo.isChecked()) {
                mensaje("activo");
            } else if (auxInactivo.isChecked()) {
                mensaje("Inactivo");
            }
            //if (auxEspecie != null){
            //    newEspecie.setSpecie(auxEspecie.getText().toString());
            //}
        } catch (Exception ex) {

        }

    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}