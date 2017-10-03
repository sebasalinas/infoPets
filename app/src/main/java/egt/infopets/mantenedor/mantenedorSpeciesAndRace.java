package egt.infopets.mantenedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import egt.infopets.R;
import egt.infopets.firstScreen;

public class mantenedorSpeciesAndRace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_species_and_race);
    }

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this, firstScreen.class);
        startActivity(intent);
    }
}
