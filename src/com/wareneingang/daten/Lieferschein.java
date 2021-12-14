package com.wareneingang.daten;

import java.util.Hashtable;
import java.util.List;

public class Lieferschein {
    int lieferungsnummer;
    private int eingabeLieferscheinnummer;

    public Lieferschein (int lieferungsnummer) {
        this.lieferungsnummer = lieferungsnummer;
    }

    public void setLieferscheinnummer(int lieferscheinnummer){
        eingabeLieferscheinnummer = lieferscheinnummer;
    }

    public int getLieferscheinnummer() {
        return eingabeLieferscheinnummer;
    }
}