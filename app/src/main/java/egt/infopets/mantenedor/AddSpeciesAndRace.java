package egt.infopets.mantenedor;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.clases.SpecieAndRace;
import egt.infopets.FirstScreen;

public class AddSpeciesAndRace extends AppCompatActivity {
    private RadioGroup gRb;
    private boolean var1 = false;
    private boolean var2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_species_and_race);


        gRb = (RadioGroup) findViewById(R.id.gRb);
        gRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.rbNewSpecie){
                    findViewById(R.id.txtNewRaceOldSpecie).setEnabled(false);
                    findViewById(R.id.txtNewSpecie).setEnabled(true);
                    findViewById(R.id.txtNewRace).setEnabled(true);
                    var1 = true;
                    var2 = false;
                }
                else{
                    if(i == R.id.rbOldSpecie)
                    {
                        findViewById(R.id.txtNewRaceOldSpecie).setEnabled(true);
                        findViewById(R.id.txtNewSpecie).setEnabled(false);
                        findViewById(R.id.txtNewRace).setEnabled(false);
                        var1 = false;
                        var2 = true;
                    }
                }
            }
        });

    }

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void add(View view){

        EditText auxNewSpecie = (EditText) findViewById(R.id.txtNewSpecie);
        EditText auxNewRace = (EditText) findViewById(R.id.txtNewRace);
        EditText auxOldNewRace = (EditText) findViewById(R.id.txtNewRaceOldSpecie);
        Spinner auxOldRace = (Spinner) findViewById(R.id.spEspecie);

        SpecieAndRace newSpecie = new SpecieAndRace();
        if (var1){

            newSpecie.setEstado(true);
            newSpecie.setSpecie(auxNewSpecie.getText().toString());
            newSpecie.setRace(auxNewRace.getText().toString());
            MantenedorSpeciesAndRace auxMantenderor = new MantenedorSpeciesAndRace(this);
            auxMantenderor.inset(newSpecie);
            this.mensaje("guardado");
        }
        else if (var2){
            newSpecie.setRace(auxOldNewRace.getText().toString());
        }
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}