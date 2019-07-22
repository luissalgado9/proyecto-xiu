package com.example.evelin.facturapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        TextView art1 = (TextView)findViewById(R.id.art1_txt);
        TextView art2 = (TextView)findViewById(R.id.art2_txt);
        TextView art3 = (TextView)findViewById(R.id.art3_txt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones,android.R.layout.simple_spinner_item);

        art1.setText(adapter.getItem(1));
        art2.setText(adapter.getItem(2));
        art3.setText(adapter.getItem(3));
    }
}
