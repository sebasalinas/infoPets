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
        setContentView(R.layout.add_especie);
    }

    public void addEspecie(View view) {
        try {
            EditText auxEspecie = (EditText) findViewById(R.id.txtNewEspecie);
            RadioButton auxActivo = (RadioButton) findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton) findViewById(R.id.rbInactivo);

            Especie newEspecie = new Especie();

            if (!auxEspecie.getText().toString().isEmpty())
            {
                if (auxActivo.isChecked())
                {

                } else if (auxInactivo.isChecked())
                {

                }
                else
                    {
                        mensaje("Debe seleccionar el estado de la especie");
                    }
            }
            else
                {
                this.mensaje("Debes Ingresar una nueva especie");
                }

        } catch (Exception ex) {
            this.mensaje("Error al ejecutar. codigo:"+ex);
        }

    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}