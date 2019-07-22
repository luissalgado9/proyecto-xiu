package com.example.evelin.facturapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Factura extends AppCompatActivity {

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

        list_art =(Spinner)findViewById(R.id.l_art);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones,android.R.layout.simple_spinner_item);
        list_art.setAdapter(adapter);

        list_cli =(Spinner)findViewById(R.id.l_cli);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this, R.array.opciones_cl,android.R.layout.simple_spinner_item);
        list_cli.setAdapter(adapt);
    }
}
