package com.example.lab4eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class pantallac extends AppCompatActivity implements View.OnClickListener {

    private Button bRegresarC;
    private TextView statusc;
    private String hostt;
    int numero;
    ArrayList<String> host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallac);

        numero= getIntent().getExtras().getInt("numero");

        bRegresarC= findViewById(R.id.bRegresarC);
        statusc= findViewById(R.id.statusc);
        bRegresarC.setOnClickListener(this);
        host= new ArrayList<String>();


        Log.e("elnumero", String.valueOf(numero));
        buscar();


    }



    public void onClick (View view){

        //me devuelvo a la pantalla b
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void buscar(){

        new Thread(

            ()->{

                for(int i=0; i<255; i++) {

                    try {
                        
                        String h1 = "192.168.0." + i;
                        InetAddress inet = InetAddress.getByName(h1);
                        //String h1 = inet.getHostAddress();

                        boolean conectado = inet.isReachable(1000);

                        runOnUiThread(
                                () -> {

                                    if (conectado == true) {
                                        statusc.append(h1 + "\n");
                                    }
                                }
                        );

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        ).start();



    }

}