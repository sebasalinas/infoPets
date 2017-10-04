package egt.infopets.mantenedor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.R;
import egt.infopets.clases.Especie;
import egt.infopets.db.MantenedorEspecie;

/**
 * Created by cetecom on 05-10-2017.
 */

public class AddEspecie extends AppCompatActivity {

    String var = "0";
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
            MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);


            String especie = auxEspecie.getText().toString();

            if (!auxEspecie.getText().toString().isEmpty())
            {
                if (auxActivo.isChecked())
                {
                    newEspecie.setId(var);
                    newEspecie.setEstado(true);
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    auxMantenedor.insert(newEspecie);
                    this.mensaje("Especie: "+ especie +" || Esado: Activo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
                    int auxVar = Integer.parseInt(var);
                    var = String.valueOf(auxVar+1);
                } else if (auxInactivo.isChecked())
                {
                    newEspecie.setId(var);
                    newEspecie.setEstado(false);
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    auxMantenedor.insert(newEspecie);
                    this.mensaje("Especie: "+ auxEspecie +" || Esado: Inactivo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
                    int auxVar = Integer.parseInt(var);
                    var = String.valueOf(auxVar+1);
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