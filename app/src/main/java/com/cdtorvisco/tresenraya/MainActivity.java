package com.cdtorvisco.tresenraya;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;
    private boolean contador=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CASILLAS = new int[9];
        CASILLAS[0] = R.id.a1;
        CASILLAS[1] = R.id.a2;
        CASILLAS[2] = R.id.a3;
        CASILLAS[3] = R.id.b1;
        CASILLAS[4] = R.id.b2;
        CASILLAS[5] = R.id.b3;
        CASILLAS[6] = R.id.c1;
        CASILLAS[7] = R.id.c2;
        CASILLAS[8] = R.id.c3;
    }
    public void play (View vista) {

        ImageView imagen;
        for (int cadaCasilla : CASILLAS) {
            imagen = (ImageView) findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla);
        }
        jugadores =1;
        if(vista.getId()==R.id.dosjug){
            jugadores=2;
            contador=true;
        }
        RadioGroup configDificultad=(RadioGroup)findViewById(R.id.configD);
        int  id=configDificultad.getCheckedRadioButtonId();
        int dificultad=0;
        if(id==R.id.normal){
            dificultad=1;}
        else if(id==R.id.imposible){
            dificultad=2;}
        partida = new Partida(dificultad,jugadores);
        ((Button)findViewById(R.id.unjug)).setEnabled(false);
        ((Button)findViewById(R.id.dosjug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);

    }

    public void toque (View mivista){
        int casilla=0;
        if(partida==null){
            return;
        }

        for(int i=0;i<=9;i++){
            if(CASILLAS[i]==mivista.getId()){
                casilla=i;
                break;
            }
        }
        if(partida.comprueba_casilla(casilla)==false){
            return;
        }
        marca(casilla);
        int resultado =partida.turno();
        if (resultado>0){

            termina(resultado);
            return;

        }
        if(jugadores!=2){
            casilla=partida.ia();
            while (partida.comprueba_casilla(casilla)!=true){
                casilla=partida.ia();
            }
            marca(casilla);
        }

        marca(casilla);
        resultado=partida.turno();
        if(resultado>0){
            termina(resultado);

        }

    }
    private void termina(int resultado){
            String mensaje;
        if(resultado==1)mensaje="Ganan los circulos";
        else if(resultado==2)mensaje="Ganan las aspas";
        else mensaje="Empate";
        Toast toast=Toast.makeText(this,mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        partida=null;
        ((Button)findViewById(R.id.unjug)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);
        ((Button)findViewById(R.id.dosjug)).setEnabled(true);

    }
    private void marca(int casilla){

        ImageView imagen;
        imagen =(ImageView)findViewById(CASILLAS[casilla]);
        if(partida.jugador==1)
        {
            imagen.setImageResource(R.drawable.circulo);
        }else{
            imagen.setImageResource(R.drawable.aspa);
        }


    }



}