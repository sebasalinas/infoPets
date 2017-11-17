package egt.infopets.MantenedoresWebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Soporte on 13-11-2017.
 */

public class WServiceRaza {

    //----Modificar
    private static String NAMESPACE = "http://tempuri.org/";
    private static String URL = "http://172.24.15.186:8073/WebServiceMongoDB.asmx";
    private static String SOAP_ACTION = "http://tempuri.org/consultaClienteJson";
    private static String TOKEN = "hqcRzb987vi2Tdl/h1mz0w==";



    //----modificar
    public static String agregarMascota() {

        //--Modificar
        String nomMetodo = "consultaClienteJson";
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
    public static String verMascota() {

        //--Modificar
        String nomMetodo = "consultaClienteJson";
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


}
