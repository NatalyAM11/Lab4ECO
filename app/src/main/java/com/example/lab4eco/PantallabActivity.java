package com.example.lab4eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PantallabActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView status;
    private Button bRegresarB;
    int numero, yanomas;
    String samsungJ7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallab);

        numero= getIntent().getExtras().getInt("numero");

        status= findViewById(R.id.status);
        bRegresarB=findViewById(R.id.bRegresarB);
        bRegresarB.setOnClickListener(this);
        Log.e("elnumero", String.valueOf(numero));

        //con esta variable defino las veces que va a pintar el status
        yanomas=0;

        //metodo de conexion
        conectar();


    }

    public void onClick (View view){

        //me devuelvo a la pantalla a
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);


    }

    public void conectar(){

        new Thread(

                () -> {

                    while(yanomas<5) {


                        try {
                            yanomas++;

                            //confirmo la conexion con el numero que introdujo el usuario
                            InetAddress inet = InetAddress.getByName("192.168.0." + numero);
                            samsungJ7 = inet.getHostAddress();
                            boolean conectado = inet.isReachable(1000);


                            runOnUiThread(
                                    () -> {
                                        //valido que se si esta conectado y pinte recibido....si no, da perdido
                                        if (conectado == true) {
                                            status.append("Recibido"+ "\n");

                                        } else {
                                            status.append("Perdidon" + "\n");
                                        }
                                    }
                            );
                            Thread.sleep(2000);

                            //Log.e("mensaje", String.valueOf(conectado));
                            //}

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }


        ).start();
    }


}