package egt.infopets.mantenedor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import egt.infopets.R;
import egt.infopets.db.dbInfoPet;
import egt.infopets.firstScreen;
import egt.infopets.clases.mascota;

public class addPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
    }

    public void backToFirstScreen(View view){
        Intent intent = new Intent(this, firstScreen.class);
        startActivity(intent);
    }


    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }


}
