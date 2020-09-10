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


        String numeroUno= firstNumbers.getText().toString();
        String numeroDos= secondNumbers.getText().toString();
        String numeroTres= thirdNumber.getText().toString();
        String numeroCuatro= fourNumber.getText().toString();

        //numerodef=Integer.parseInt(numeroCuatro);


        bPing.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
        conection();

    }

    public void onClick(View view){

            switch (view.getId()){
                case R.id.bPing:
                    Intent i = new Intent(this, PantallabActivity.class);
                    startActivity(i);
                    break;

                case R.id.bBuscar:
                    Intent a= new Intent(this, pantallac.class);
                    startActivity(a);
                    break;

            }
    }

    public void conection() {


        new Thread(
                () -> {

                    try {


                        for( int i=0; i<50; i++) {

                            //Tomo el ultimo numero que ingrese el usuario
                            String numeroCuatro= fourNumber.getText().toString();


                            if(numeroCuatro.trim().isEmpty()){
                                runOnUiThread( ()-> Toast.makeText(this, " Uno de los campos no fue llenado", Toast.LENGTH_LONG).show());
                                return;
                            }


                            //lo paso a int
                            numerodef=Integer.parseInt(numeroCuatro);


                            //lo igualo con i
                            i=numerodef;

                            //conexion
                            InetAddress inet = InetAddress.getByName("192.168.0"+i);
                            String samsungJ7 = inet.getHostAddress();
                            boolean conectado = inet.isReachable(5000);
                            Log.e("mensaje", String.valueOf(conectado));
                        }

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


        ).start();
    }



}