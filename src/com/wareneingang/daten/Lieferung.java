package com.wareneingang.daten;

import java.util.*;

public class Lieferung {
    private int lieferungsnummer;
    private Date eingangsdatum;
    private Lieferschein lieferschein;
    private Hashtable<Ware, Integer> waren; //  enthält Stückzahl und Waren
    private Hashtable<Ware, Integer> abgelehnteWaren = new Hashtable<>();
    private Hashtable<Ware, Integer> angenommeneWaren = new Hashtable<>();
    private boolean abgeschlossen;

    public Lieferung (int lieferungsnummer, Date eingangsdatum, Lieferschein lieferschein, Hashtable<Ware, Integer> waren, boolean abgeschlossen)
    {
        this.lieferungsnummer = lieferungsnummer;
        this.eingangsdatum = eingangsdatum;
        this.lieferschein = lieferschein;
        this.waren = waren;
        this.abgeschlossen = abgeschlossen;
    }

    // Zweiter Konstruktor um Fehler in Eingabe.java zu entfernen
    // An und fuer sich vermutlich aber nicht wirklich Sinnvoll
    public Lieferung() {}

    public Lieferschein getLieferschein()
    {
        return this.lieferschein;
    }

    public Hashtable<Ware, Integer> getWaren(){
        return this.waren;
    }

    public void wareAnnehmen(Ware ware, int stueckzahl){
        angenommeneWaren.put(ware,stueckzahl);
    }
    public void wareAblehnen(Ware ware, int stueckzahl){
        abgelehnteWaren.put(ware,stueckzahl);
    }

    public void abschliessen() {
        this.abgeschlossen = true;
    }

    public int getLieferungsnummer() {
        return lieferungsnummer;
    }

    public Hashtable<Ware, Integer> getAbgelehnteWaren() {
        return abgelehnteWaren;
    }

    public Hashtable<Ware, Integer> getAngenommeneWaren() {
        return angenommeneWaren;
    }

    public boolean isAbgeschlossen() {
        return abgeschlossen;
    }

    public void setQualitaet(Ware ware, boolean qualitaet) {
        if (qualitaet) {
            this.addAngenommeneWare(ware, this.waren.get(ware));
        }
    }

    public Date getEingangsdatum() {
        return eingangsdatum;
    }

    public void addAngenommeneWare(Ware ware, int stueckzahl) {
        angenommeneWaren.put(ware, stueckzahl);
    }

    public void addAbgelehnteWare(Ware ware, int stueckzahl) {
        abgelehnteWaren.put(ware, stueckzahl);
    }

    public double getGesamtpreis() {
        double preis = 0;

        Set<Ware> waren = angenommeneWaren.keySet();
        Iterator<Ware> iterator = waren.iterator();

        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            preis += ware.getPreis() * angenommeneWaren.get(ware);
        }

        return Math.round(preis * 100.0) / 100.0;
    }
}
