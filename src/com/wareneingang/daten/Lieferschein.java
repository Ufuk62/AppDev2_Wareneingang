package com.wareneingang.daten;

import java.util.Hashtable;
import java.util.List;

public class Lieferschein {
    int Lieferungsnummer = 10;
    private int eingabeLieferscheinnummer;

    public Lieferschein (Hashtable<Ware, Integer> waren) {

    }

    public void setLieferscheinnummer(int lieferscheinnummer){
        eingabeLieferscheinnummer = lieferscheinnummer;
    }

    public int getLieferscheinnummer(){
        return eingabeLieferscheinnummer;
    }

    void printLieferschein(){

    }
}
