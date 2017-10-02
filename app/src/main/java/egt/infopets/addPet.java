package egt.infopets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class addPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
    }

    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
    }


}
