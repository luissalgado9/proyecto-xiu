package com.example.evelin.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void nuevocliente (View view){
        startActivity(new Intent(this,Menu_cliente.class));
    }
    public void cliente (View view){startActivity(new Intent(this,Cliente.class));}
    public void producto (View view){startActivity(new Intent(this,Producto.class));}
    public void nuevoarticulo (View view){
        startActivity(new Intent(this,Menu_articulo.class));
    }
}
