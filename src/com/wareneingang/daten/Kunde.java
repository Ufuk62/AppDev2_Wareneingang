package com.wareneingang.daten;

import java.util.Hashtable;

public class Kunde {
    int Kundennummer;
    // key = lieferungsnummer
    // value = lieferung
    Hashtable Lieferungen;

    public Kunde(int kundennummer, Hashtable lieferungen) {
        this.Kundennummer = kundennummer;
        this.Lieferungen = lieferungen;
    }

    public Lieferung getLieferung(int lieferungsnummer) {
        return (Lieferung) this.Lieferungen.get(lieferungsnummer);
    }

    public void setKundennummer(int Kundennummer){
        int eingabeKundennummer = Kundennummer;
    }

    public int getKundennummer(){
        return Kundennummer;
    }
}
