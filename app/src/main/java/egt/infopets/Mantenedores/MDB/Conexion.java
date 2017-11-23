package egt.infopets.Mantenedores.MDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

/**
 * Created by Soporte on 14-11-2017.
 */

public class Conexion extends AppCompatActivity{

        private static String NAMESPACE = "http://tempuri.org/";
        private static String URL = "http://172.24.15.186:8013/WebServiceMDB.asmx";
        private static String SOAP_ACTION = "http://tempuri.org/insertarEspecie";
        private static String TOKEN = "hqcRzb987vi2Tdl/h1mz0w==";

        public static boolean verificaConexion(Context ctx) {
                boolean auxConec = false;
                ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                                // connected to wifi
                                auxConec = true;
                                // Toast.makeText(ctx, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                                // connected to the mobile provider's data plan
                                auxConec = true;
                                // Toast.makeText(ctx, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                        }
                }
                else
                {
                        auxConec = false;
                }
                return auxConec;
        } //Fin verficicar
}
