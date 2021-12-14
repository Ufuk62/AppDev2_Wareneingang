package com.wareneingang.daten;

import java.util.Hashtable;

public class Kunde {
    private int kundennummer;
    private String vorname;
    private String nachname;
    // key = lieferungsnummer
    // value = lieferung
    private Hashtable<Integer, Lieferung> lieferungen;

    public Kunde(int kundennummer, String vorname, String nachname, Hashtable<Integer, Lieferung> lieferungen) {
        this.kundennummer = kundennummer;
        this.lieferungen = lieferungen;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Lieferung getLieferung(int lieferungsnummer) {
        return this.lieferungen.get(lieferungsnummer);
    }

    public int getKundennummer() {
        return this.kundennummer;
    }

    public Hashtable<Integer, Lieferung> getLieferungen() {
        return this.lieferungen;
    }

    public String getName() {
        return this.nachname + ", " + this.vorname;
    }
}
