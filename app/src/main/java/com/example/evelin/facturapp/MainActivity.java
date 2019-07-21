package com.example.evelin.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String webhook = "https://5d647e89.ngrok.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void entrar (View view) {
        startActivity(new Intent(this, Menu.class));
        String text = "PRUEBA";

        //int[] list_data = new int[0];
        //list_data[0] = 0;
        List list_data = new ArrayList();

        list_data.add("element 1");
        list_data.add("element 2");
        list_data.add("element 3");

        ArrayList<String> datos = (ArrayList<String>) list_data;
        JSONObject jsonObject = null;
        String response = "";
        int status = 0;


        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", datos.get(0));
            jsonObject.put("descripcion", datos.get(1));
            jsonObject.put("usuario", datos.get(2));

            String asd = "{\"menu\":{\"id\": \"file\",\"value\": \"File\"}}";

            Toast.makeText(getApplicationContext(), asd, Toast.LENGTH_LONG).show();
            HttpRequest request = HttpRequest.get(webhook+"/facturapp/").header("Content-Type", "application/json").send(jsonObject.toString());
            Toast.makeText(getApplicationContext(), "prueba 2", Toast.LENGTH_LONG).show();
            status = request.code();
            response = request.body();
            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + "json" + "\"");
            text = "ERROR";
            //Toast.makeText(getApplicationContext(), t, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

    }
}

