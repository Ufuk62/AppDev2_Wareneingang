package com.wareneingang.daten;

public class Ware {
    int Warennummer;
    String Warenbezeichnung;
    double Preis;

    //Konstruktor fehlt
    public Ware (int Warennummer, String Warenbezeichnung, double Preis){
        this.Warenbezeichnung = Warenbezeichnung;
        this.Warennummer = Warennummer;
        this.Preis = Preis;
    }

    public int getWarennummer() {
        return Warennummer;
    }

    public double getPreis() {
        return Preis;
    }

    public String getWarenbezeichnung() {
        return Warenbezeichnung;
    }
}
