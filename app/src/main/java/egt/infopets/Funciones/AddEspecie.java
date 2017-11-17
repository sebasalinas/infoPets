package egt.infopets.Funciones;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import egt.infopets.MantenedoresWebService.Conexion;
import egt.infopets.MantenedoresWebService.SMantenedorEspecie;
import egt.infopets.R;
import egt.infopets.Clases.Especie;
import egt.infopets.Mantenedores.MantenedorEspecie;

/**
 * Created by cetecom on 05-10-2017.
 */

public class AddEspecie extends AppCompatActivity {
    int var = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_especie);

        //ListView auxListView = (ListView)findViewById(R.id.lvMostrar);

        mostrar();

    }

    public void addEspecie(View view) {
        try {
            Conexion cnn = new Conexion();
            EditText auxEspecie = (EditText) findViewById(R.id.txtNewEspecie);
            RadioButton auxActivo = (RadioButton) findViewById(R.id.rbActivo);
            RadioButton auxInactivo = (RadioButton) findViewById(R.id.rbInactivo);

            Especie newEspecie = new Especie();
            SMantenedorEspecie auxWMantenedor = new SMantenedorEspecie();
            MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);


            String especie = auxEspecie.getText().toString();

            if (!auxEspecie.getText().toString().isEmpty())
            {
                    newEspecie.setSpecie(auxEspecie.getText().toString());
                    if (!cnn.verificaConexion(this)){
                        auxMantenedor.insert(newEspecie);
                    }else {
                        auxWMantenedor.execute(especie);
                    }
                    this.mensaje("Especie: "+ especie +" || Esado: Activo");
                    ((EditText) findViewById(R.id.txtNewEspecie)).setText("");
            }
            else
                {
                this.mensaje("Debes Ingresar una nueva especie");
                }
                mostrar();
        } catch (Exception ex) {
            this.mensaje("Error al ejecutar. codigo:"+ex);
        }

    }

    public void mostrar(){
        final MantenedorEspecie mantenedorEspecie =new MantenedorEspecie(this);

        final List<Especie> auxListaEspecie = mantenedorEspecie.getAll();

        String[] listaString = new String[auxListaEspecie.size()];

        Iterator iter = auxListaEspecie.iterator();

        int pos = 0;

        while (iter.hasNext()){
            Especie auxLista = new Especie();

            auxLista = (Especie) iter.next();

            listaString[pos] = auxLista.getSpecie();
            pos++;
        }

        final ListView auxListView = (ListView)findViewById(R.id.lvMostrar);

        auxListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaString));

        auxListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                parent.getAdapter().toString();

                String itemValue    = (String)   auxListView.getItemAtPosition(position);

                AlertDialog.Builder mBuider = new AlertDialog.Builder(AddEspecie.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_especie,null);
                final EditText mCod = (EditText) mView.findViewById(R.id.txtDialogCodigo);
                final EditText mEspecie = (EditText) mView.findViewById(R.id.txtDialogEspecie);
                final RadioButton mRMacho = (RadioButton) mView.findViewById(R.id.DialogActivo);
                final RadioButton mRHembra = (RadioButton) mView.findViewById(R.id.DialogInactivo);
                ImageButton mButtonEdit = (ImageButton) mView.findViewById(R.id.btnDialogoEdit);
                ImageButton mButtonDelete = (ImageButton) mView.findViewById(R.id.btnDialogoDelete);
                mEspecie.setText(itemValue);
                mBuider.setView(mView);
                final AlertDialog dialog = mBuider.create();
                dialog.show();
                mButtonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mEspecie.getText().toString().isEmpty()){
                            if (mRHembra.isChecked()){
                                updateEspecie(auxListaEspecie.get(position).getId(),mEspecie.getText().toString(),mRHembra.getText().toString());
                                dialog.dismiss();
                            }
                            else if(mRMacho.isChecked()){
                                updateEspecie(auxListaEspecie.get(position).getId(),mEspecie.getText().toString(),mRMacho.getText().toString());
                                dialog.dismiss();
                            }
                            else{
                               mensaje("Debe seleccionar un estado");
                            }
                        }else {
                           mensaje("Identifique Especie");
                        }
                    }
                });

                mButtonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mEspecie.getText().toString().isEmpty()){
                            deleteEspecie((auxListaEspecie.get(position).getId()));
                            mensaje("Mascota eliminada");

                        }else {
                            mensaje("Debe especificar el nombre de la mascota");
                        }
                    }
                });

                mostrar();
            }
        });
    }

    private void deleteEspecie(int id) {
        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);

        auxMantenedor.delete(id);
        mostrar();
    }

    private void updateEspecie(int cod, String especie,String radio) {
        MantenedorEspecie auxMantenedor = new MantenedorEspecie(this);
        Especie auxEspecie = new Especie();


        auxEspecie.setId(cod);
        auxEspecie.setSpecie(especie);
        auxMantenedor.update(auxEspecie);
        mensaje("Nombre Mascota Actualizado");
        mostrar();
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


}