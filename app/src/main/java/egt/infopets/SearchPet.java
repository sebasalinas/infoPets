package egt.infopets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class SearchPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pet);

    }
    public void mensaje(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
