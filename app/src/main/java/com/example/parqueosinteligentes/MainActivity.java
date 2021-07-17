package com.example.parqueosinteligentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txt_email,txt_pass;

    private Button btn_iniciar_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        InitializateComponents();

    }

    private void InitializateComponents(){
        txt_email = (EditText) findViewById(R.id.editTextUsuario);
        txt_pass = (EditText) findViewById(R.id.editTextPass);

        btn_iniciar_sesion = (Button) findViewById(R.id.btnLogin);
        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String pass = txt_pass.getText().toString();

                if(!email.isEmpty() && !pass.isEmpty()){
                    iniciarSesion(email,pass);
                }
                else{
                    Toast.makeText(MainActivity.this, "Ingrese su usuario y contraseña",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void iniciarSesion(String email,String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //To Menu Activity
                            Intent intent= new Intent(MainActivity.this, Menu.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta.",
                                    Toast.LENGTH_SHORT).show();
                            txt_pass.setText("");
                        }
                    }
                });

    }

}
