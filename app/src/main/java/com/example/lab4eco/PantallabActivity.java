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
    int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallab);

        numero= getIntent().getExtras().getInt("numero");

        status= findViewById(R.id.status);
        bRegresarB=findViewById(R.id.bRegresarB);

        conectar();

        bRegresarB.setOnClickListener(this);
        Log.e("elnumero", String.valueOf(numero));
    }

    public void onClick (View view){


        //me devuelvo a la pantalla a
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);


    }

    public void conectar(){
        new Thread(
                () -> {

                    try {
                        Thread.sleep(500);


                        //confirmo la conexion con el numero que introdujo el usuario
                        InetAddress inet = InetAddress.getByName("192.168.0."+ numero);
                        String samsungJ7 = inet.getHostAddress();
                        boolean conectado = inet.isReachable(1000);


                        //letrero del status
                        String estado = status.getText().toString();


                        //valido que se si esta conectado y pinte recibido....si no, da perdido
                        if(conectado) {
                            estado = "Recibido";
                        }else{ estado = "Perdido";}

                         while(conectado){
                        //pinto el estado
                            String finalEstado = estado;
                            runOnUiThread(() -> status.append(finalEstado + "\n"));
                         }

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


        ).start();
    }


}