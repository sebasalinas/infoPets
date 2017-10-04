package egt.infopets.mantenedor;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.clases.Especie;

public class AddRaza extends AppCompatActivity {
    private RadioGroup gRb;
    private boolean var1 = false;
    private boolean var2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_raza);



    }

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    //public void add(View view){
//
    //    EditText auxNewSpecie = (EditText) findViewById(R.id.txtNewSpecie);
    //    EditText auxNewRace = (EditText) findViewById(R.id.txtNewRace);
    //    EditText auxOldNewRace = (EditText) findViewById(R.id.txtNewRaza);
     //   Spinner auxOldRace = (Spinner) findViewById(R.id.spEspecie);
//
     //   Especie newSpecie = new Especie();
    //    if (var1){

     //       newSpecie.setEstado(true);
     //       newSpecie.setSpecie(auxNewSpecie.getText().toString());
            //newSpecie.setRaza(auxNewRace.getText().toString());
     //       MantenedorEspecie auxMantenderor = new MantenedorEspecie(this);
     //       auxMantenderor.inset(newSpecie);
      //      this.mensaje("guardado");
      //  }
      //  else if (var2){
            //newSpecie.setRace(auxOldNewRace.getText().toString());
      //  }
   // }

    public void addRaza(View view){
        try {
            EditText auxEspecie = (EditText)findViewById(R.id.txtNewRaza);
            RadioButton auxActivo = (RadioButton)findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton)findViewById(R.id.rbInactivo);

            Especie newEspecie = new Especie();
            if (auxActivo.isChecked()){
                mensaje("activo");
            }
            else if(auxInactivo.isChecked()){
                mensaje("Inactivo");
            }
            //if (auxEspecie != null){
            //    newEspecie.setSpecie(auxEspecie.getText().toString());
            //}
        }
        catch (Exception ex){

        }

    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}