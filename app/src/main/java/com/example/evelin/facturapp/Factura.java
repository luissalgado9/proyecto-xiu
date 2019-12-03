package com.example.evelin.facturapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Factura extends AppCompatActivity {
    int pos_art,pos_cli;
    TextView imprime_pos;
    Spinner list_art, list_cli;
    String[] Subject = new String[]{
            "Maths",
            "Hindi",
            "English",
            "Computer"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        imprime_pos = (TextView)findViewById(R.id.textView3);

        //Spinner lista articulos
        list_art = (Spinner) findViewById(R.id.l_art);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        list_art.setAdapter(adapter);


        Integer asu =list_art.getSelectedItemPosition();


        list_art.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos_art = list_art.getSelectedItemPosition()+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner de lista clientes
        list_cli = (Spinner) findViewById(R.id.l_cli);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this, R.array.opciones_cl, android.R.layout.simple_spinner_item);
        list_cli.setAdapter(adapt);

    }
    public void entrar (){
        startActivity(new Intent(this,Menu.class));
    }
    public void crearFactura(View view) {

        String texto = list_art.getSelectedItem().toString();
        int idProducto = 0;

        if(texto.equals("Television 32 Samsung")){
            idProducto = 1;
        }
        if(texto.equals("Refrigerador Mabe")){
            idProducto = 2;
        }
        if(texto.equals("Sillon 3 piezas Cafe")){
            idProducto = 3;
        }
        int idCliente = 0;

        String cliente = list_cli.getSelectedItem().toString();

        if(cliente.equals("Luis Abraham Salgado Ascencio")){
            idCliente = 1;
        }
        if(cliente.equals("Eveling Jimenez Ramirez")){
            idCliente = 2;
        }
        if(cliente.equals("Carlos Efren Dzul Hau")){
            idCliente = 3;
        }
        String correo = "luissalgado9@hotmail.com";


        int idFactura = (int) (Math.random() * 10000) + 1;

        new Factura.CargarDatos().execute("http://172.20.10.2/facturap/crear-factura.php?id="+idFactura+"correo=luissalgado9@hotmail.com&producto="+ idProducto+"&cliente="+idCliente);
        Toast.makeText(getApplicationContext(), "Se ha generado la factura", Toast.LENGTH_LONG).show();

        String strPhone = "9981875356";

        String direccion = "http://172.20.10.2/facturap/ver-factura.php?id="+idFactura;

        String strMessage = "Su factura se ha creado. Puede verlo en: "+ direccion;

        SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage(strPhone, null, strMessage, null, null);

        enviar(idFactura);

        Toast.makeText(this, "Sent.", Toast.LENGTH_SHORT).show();
        //entrar();

    }


    public void enviar(int idFactura){
        //obtenemos los datos para el envío del correo
        /*EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etSubject = (EditText) findViewById(R.id.etSubject);
        EditText etBody = (EditText) findViewById(R.id.etBody);
        CheckBox chkAttachment = (CheckBox) findViewById(R.id.chkAttachment);*/

        String direccion = "http://172.20.10.2/facturap/ver-factura.php?id="+idFactura;

        String strMessage = "Su factura se ha creado. Puede verlo en: "+ direccion;

        //es necesario un intent que levante la actividad deseada
        Intent itSend = new Intent(android.content.Intent.ACTION_SEND);

        //vamos a enviar texto plano a menos que el checkbox esté marcado
        itSend.setType("plain/text");

        //colocamos los datos para el envío
        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "luissalgado9@hotmail.com"});
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, "Factura: "+idFactura);
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, strMessage);

        startActivity(itSend);

    }

    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Datos Guardados",
                    Toast.LENGTH_LONG).show();

        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL", "" + myurl);
        myurl = myurl.replace(" ", "%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


    protected void sendEmail() {
        String[] TO = {"luissalgado9@hotmail.com"}; //aquí pon tu correo
        String[] CC = {"lumiel801@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        // Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Factura.this,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();

        }
    }
}
