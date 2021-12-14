package com.wareneingang.daten;

import java.util.Hashtable;

public class Kunde {
    int Kundennummer;
    // key = lieferungsnummer
    // value = lieferung
    Hashtable<Integer, Lieferung> Lieferungen;

    public Kunde(int kundennummer, Hashtable<Integer, Lieferung> lieferungen) {
        this.Kundennummer = kundennummer;
        this.Lieferungen = lieferungen;
    }

    public Lieferung getLieferung(int lieferungsnummer) {
        return this.Lieferungen.get(lieferungsnummer);
    }

    public int getKundennummer() {
        return Kundennummer;
    }

    public Hashtable<Integer, Lieferung> getLieferungen() {
        return Lieferungen;
    }
}
