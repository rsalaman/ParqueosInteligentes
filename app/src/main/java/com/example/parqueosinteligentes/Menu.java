package com.example.parqueosinteligentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    private TextView textViewNombre;
    private FirebaseAuth mAuth;
    private Button btnMapa;
    private Button cerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        InitializateComponents();

        FirebaseUser user = mAuth.getCurrentUser();
        textViewNombre.setText(user.getEmail());
        cerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Menu.this,MainActivity.class));
                finish();
            }
        });

    }

    private void InitializateComponents(){
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
    }
    public void revisarMapa(View v) {
        Intent mapa = new Intent(this, Parqueadero.class);
        startActivity(mapa);
    }
}