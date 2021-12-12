package com.wareneingang.daten;

import java.util.Hashtable;

public class Kunde {
    int Kundennummer;
    String Kundenname;
    // key = lieferungsnummer
    // value = lieferung
    Hashtable Lieferungen;

    public Kunde(int kundennummer, String kundenname, Hashtable lieferungen) {
        this.Kundennummer = kundennummer;
        this.Lieferungen = lieferungen;
        this.Kundenname = kundenname;
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
