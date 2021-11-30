package com.wareneingang.daten;

import java.util.List;

public class Lieferung {
    private String Lieferungsnummer;
    private String Lieferscheinnummer;
    private String Kundennummer;
    private List<Ware> Waren;

    public Lieferung (String lieferungsnummer, String lieferscheinnummer, String kundennummer, List<Ware> waren) {
        this.Lieferungsnummer = lieferungsnummer;
        this.Lieferscheinnummer = lieferscheinnummer;
        this.Kundennummer = kundennummer;
        this.Waren = waren;
    }
}
