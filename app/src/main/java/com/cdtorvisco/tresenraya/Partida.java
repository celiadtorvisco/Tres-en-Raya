package com.cdtorvisco.tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Partida {
    public int casillas[];
    public int jugador;
    public final int dificultad;
    private int [] conbinaciones;
    private final int[][] CONBINACIONES={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public Partida (int dificultad, int jugador) {

        this.dificultad = dificultad;
        this.jugador=jugador;
        casillas=new int[9];
        for(int i=0;i<9;i++){
            casillas[i]=0;
        }
    }
    public boolean comprueba_casilla(int casilla){
        if(casillas[casilla]!=0){
            return false;
        }else{
            casillas[casilla]=jugador;
        }
        return true;
    }
    public int turno(){
        boolean empate=true;
        boolean ult_movimiento=true;
       jugador++;

        if(jugador>2){
            jugador=1;

        }
        for(int i=0;i<CONBINACIONES.length;i++){
            for (int pos:CONBINACIONES[i]){
                System.out.println("Valor en posicion" + i +" "+casillas[pos]);
                if(casillas[pos]!=jugador) ult_movimiento=false;
                if(casillas[pos]==0)empate=false;
            }//Cierre for anidado
            System.out.println("---------------------------------------------------------");
            if(ult_movimiento){
                return jugador;
            }
            ult_movimiento=true;
        }//Cierre for principal
        if(empate){
            return 3;
        }

        return 0;

    }

    public int dosEnRaya(int jugador_en_turno){
        int casilla=-1;
        int cuantas_lleva=0;
        for(int i=0;i<CONBINACIONES.length;i++){
            for (int pos:CONBINACIONES[i]) {
                if(casillas[pos]==jugador_en_turno)cuantas_lleva++;
                if(casillas[pos]==0)casilla=pos;


            }
            if(cuantas_lleva==2 &&casilla!=-1)return casilla;

            casilla=-1;
            cuantas_lleva=0;

        }
        return -1;
    }


    public int ia(){
        int casilla;
        casilla=dosEnRaya(2);
        if(casilla!=-1)return casilla;
        if(dificultad>0){
            casilla=dosEnRaya(1);
            if(casilla!=-1)return casilla;
        }
        if(dificultad==2){
            if(casillas[0]==0)return 0;
            if(casillas[2]==0)return 2;
            if(casillas[6]==0)return 6;
            if(casillas[8]==0)return 8;

        }
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);

        return casilla;

    }

}
