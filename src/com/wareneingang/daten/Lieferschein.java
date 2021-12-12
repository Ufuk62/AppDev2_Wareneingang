package com.wareneingang.daten;

public class Lieferschein {
    int Lieferungsnummer = 10;
    private int eingabeLieferscheinnummer;

    public void lieferschein (int Lieferungsnummer) {
        this.Lieferungsnummer = Lieferungsnummer;

    }

    public void setLieferscheinnummer(int lieferscheinnummer){
        eingabeLieferscheinnummer = lieferscheinnummer;
    }

    public int getLieferscheinnummer(){
        return eingabeLieferscheinnummer;
    }
}
