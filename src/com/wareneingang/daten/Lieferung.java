package com.wareneingang.daten;

import java.util.Hashtable;

public class Lieferung {
    private String lieferungsnummer;
    private Kunde kunde ;
    private Lieferschein lieferschein;
    private Hashtable<Ware, Integer> waren; //  enthält Stückzahl und Waren
    private Hashtable<Ware, Integer>  abgelehnteWaren;
    private Hashtable<Ware, Integer> angenommeneWaren;
    private boolean abgeschlossen ;


    public Lieferung ( String lieferungsnummer, Kunde kunde ,Lieferschein lieferschein)
    {
        this.lieferungsnummer = lieferungsnummer;
        this.kunde = kunde;
        this.lieferschein = lieferschein;
    }

    public Lieferschein getLieferschein()
    {
        return this.lieferschein;
    }

    public Hashtable<Ware, Integer> getWaren(int warennummer){
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
}
