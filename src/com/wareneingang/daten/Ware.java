package com.wareneingang.daten;

public class Ware {
    int warennummer;
    String Warenbezeichnung;
    double Warenwert;

    public Ware (int warennummer) {
        this.warennummer = warennummer;
    }

    public int getWarennummer() {
        return warennummer;
    }
}
