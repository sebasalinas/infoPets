package egt.infopets.mantenedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import egt.infopets.R;
import egt.infopets.FirstScreen;

public class AddPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
    }

    public void backToFirstScreen(View view){
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }


    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }


}
