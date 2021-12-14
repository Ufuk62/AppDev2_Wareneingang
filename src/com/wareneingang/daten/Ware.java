package com.wareneingang.daten;

public class Ware {
    int warennummer;
    String warenbezeichnung;
    double preis;

    public Ware (int warennummer, String warenbezeichnung, double preis){
        this.warenbezeichnung = warenbezeichnung;
        this.warennummer = warennummer;
        this.preis = preis;
    }

    public int getWarennummer() {
        return this.warennummer;
    }

    public double getPreis() {
        return this.preis;
    }

    public String getWarenbezeichnung() {
        return this.warenbezeichnung;
    }
}
