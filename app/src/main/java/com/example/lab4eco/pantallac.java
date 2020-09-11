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
        host= new ArrayList<String>();

        bRegresarC.setOnClickListener(this);
        buscar();

        Log.e("elnumero", String.valueOf(numero));

    }



    public void onClick (View view){

        //me devuelvo a la pantalla b
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void buscar(){

        new Thread(

            ()->{
                try {

                    Thread.sleep(500);

                    for(int i=0; i<255; i++) {

                        InetAddress inet = InetAddress.getByName("192.168.0."+i);
                        //String h1 = inet.getHostAddress();
                        String h1 = "192.168.0."+ numero;
                        boolean conectado = inet.isReachable(1000);

                        String estadoC = statusc.getText().toString();


                        if (conectado) {
                            host.add(h1+ conectado);
                            runOnUiThread(() -> statusc.append(h1 + "\n"));
                        }
                    }

                } catch (UnknownHostException | InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        ).start();



    }

}