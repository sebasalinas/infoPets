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

        public static boolean verificaConexion(Context ctx) {
                boolean auxConec = false;
                ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                                auxConec = true;
                        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                                auxConec = true;
                        }
                }
                else
                {
                        auxConec = false;
                }
                return auxConec;
        }
}
