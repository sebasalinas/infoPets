package egt.infopets.MantenedoresWebService;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import egt.infopets.Clases.Especie;
import egt.infopets.Mantenedores.DbInfoPet;

/**
 * Created by Soporte on 15-11-2017.
 */

public class SMantenedorEspecie extends AsyncTask<String, Void, Void> {

    String resultado = "";



    @Override
    protected Void doInBackground(String... params)
    {
        try {

            //---------------Modificar
            // WebService ws = new WebService();
            String NAMESPACE = "http://tempuri.org/";
            String URL="http://192.168.78.1:8013/WebServiceMDB.asmx";
            String METHOD_NAME = "insertarEspecie";
            String SOAP_ACTION = "http://tempuri.org/insertarEspecie";
            //------------------------
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //----------Modificar
            //request.addProperty("rut", params[0].toString());
            request.addProperty("descripcion", params[0].toString());
            //------------------------


            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                //Se procesa el resultado devuelto
                //...
            }
            catch (Exception e)
            {
                // this.mensaje("error");
                resultado = "error";
            }

            String res = "";
            try {

                //   SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                //  if (resultado_xml != null)
                //    res = resultado_xml.toString();
            }
            catch(Exception ex)
            {
                // this.mensaje("error");

            }

            // if (res != "")
            // if(res.equals("1"))
            // this.mensaje("Insertado OK");
            resultado = "Datos Guardados";
            //   else
            //     resultado = "Datos Guardados";







        }
        catch (final Exception ex)
        {
            // runOnUiThread(new Runnable() {
            //   public void run() {

            //  TextView aux = (TextView)findViewById(R.id.txtTest);
            //  aux.setText(ex.getMessage());
            // }
            // });
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
/*          pro.setVisibility(View.VISIBLE);
            btnIngresar.setEnabled(false);
            btnCancelar.setEnabled(false);
            txtRut.setEnabled(false);
            txtContra.setEnabled(false); */
    }

    @Override
    protected void onPostExecute(Void args)
    {
        try {

            switch (resultado){
                case "true":

                    break;
                default:
                    // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    break;
            }

        }catch (final Exception ex)
        {
            //  runOnUiThread(new Runnable() {
            //    public void run() {
            //TextView aux = (TextView)findViewById(R.id.txtTest);
            //aux.setText(ex.getMessage());
            //  }
            //});
        }
    }



}
