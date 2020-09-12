package com.example.lab4eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText firstNumbers;
    private EditText secondNumbers;
    private EditText thirdNumber;
    private EditText fourNumber;
    private Button bPing;
    private Button bBuscar;
    ArrayList<String> numero;
    private int numerodef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumbers= findViewById(R.id.firstNumbers);
        secondNumbers= findViewById(R.id.secondNumbers);
        thirdNumber= findViewById(R.id.thirdNumber);
        fourNumber= findViewById(R.id.fourNumber);
        bPing= findViewById(R.id.bPing);
        bBuscar= findViewById(R.id.bBuscar);
        numero= new ArrayList<String>();


        bPing.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
    }

    public void onClick(View view){
        //los demas numeros
        String numeroUno= firstNumbers.getText().toString();
        String numeroDos= secondNumbers.getText().toString();
        String numeroTres= thirdNumber.getText().toString();
        String numeroCuatro= fourNumber.getText().toString();


        //no deja pasar al usuario hasta que llene todo
        if(numeroUno.trim().isEmpty() || numeroDos.trim().isEmpty() || numeroTres.trim().isEmpty()||  numeroCuatro.trim().isEmpty()){
            runOnUiThread( ()-> Toast.makeText(this, " Uno de los campos no fue llenado", Toast.LENGTH_LONG).show());
            return;
        }

            switch (view.getId()){

                case R.id.bPing:

                    //paso a la pantalla b y mando el dato de numero
                    numerodef=Integer.parseInt(numeroCuatro);

                    Intent i = new Intent(this, PantallabActivity.class);
                    i.putExtra("numero", numerodef);
                    startActivity(i);
                    break;

                case R.id.bBuscar:

                    //paso a la pantalla c
                    Intent a= new Intent(this, pantallac.class);
                    a.putExtra("numero", numerodef);
                    startActivity(a);
                    break;

            }
    }


}