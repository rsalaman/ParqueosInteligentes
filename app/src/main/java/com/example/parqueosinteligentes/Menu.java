package com.example.parqueosinteligentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView textViewNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String usuario_correo = getIntent().getStringExtra("correo");

        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        textViewNombre.setText(usuario_correo);
    }
}