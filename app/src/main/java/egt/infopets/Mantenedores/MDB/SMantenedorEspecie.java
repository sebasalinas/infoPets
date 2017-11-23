package egt.infopets.Mantenedores.MDB;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Soporte on 15-11-2017.
 */

public class SMantenedorEspecie extends AsyncTask<String, Void, Void> {

    String resultado = "";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String URL = "http://172.24.15.186:8013/WebServiceMDB.asmx";
    private static String SOAP_ACTION = "http://tempuri.org/insertarEspecie";
    private static String TOKEN = "hqcRzb987vi2Tdl/h1mz0w==";

    public static String agregarMascota() {

        //--Modificar
        String nomMetodo = "insertarEspecie";
        //Modificar
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, nomMetodo);
        // Property which holds input parameters
        PropertyInfo tokenWS = new PropertyInfo();
        tokenWS.setName("token");
        tokenWS.setValue(TOKEN);
        tokenWS.setType(String.class);
        request.addProperty(tokenWS);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //Set envelope as dotNet
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try
        {
            androidHttpTransport.debug = true;

            //Log.d("dump Request: " ,androidHttpTransport.requestDump);
            //Log.d("dump response: " ,androidHttpTransport.responseDump);
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.bodyIn;
            /*SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable*/
            resTxt = androidHttpTransport.responseDump;
        }
        catch (Exception e)
        {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            resTxt = e.toString();
        }
        //Return resTxt to calling object
        return resTxt;
    } //Fin devuelve cliente


    @Override
    protected Void doInBackground(String... params)
    {
        try {

            //---------------Modificar
            // WebService ws = new WebService();
            String NAMESPACE = "http://tempuri.org/";
            //modificar server.
            String URL="http://172.24.15.186:8013/WebServiceMDB.asmx";
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
                resultado = "funciona";

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
