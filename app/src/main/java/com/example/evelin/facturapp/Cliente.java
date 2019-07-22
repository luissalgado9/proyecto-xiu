package com.example.evelin.facturapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        TextView cli1 = (TextView)findViewById(R.id.cli1_txt);
        TextView cli2 = (TextView)findViewById(R.id.cli2_txt);
        TextView cli3 = (TextView)findViewById(R.id.cli3_txt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_cl,android.R.layout.simple_spinner_item);

        cli1.setText(adapter.getItem(1));
        cli2.setText(adapter.getItem(2));
        cli3.setText(adapter.getItem(3));
    }
}
