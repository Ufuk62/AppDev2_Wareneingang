package com.wareneingang.daten;

import java.util.List;

public class Lieferschein {
    private String Lieferscheinnummer;
    private List<Ware> Waren;

    public Lieferschein(String lieferscheinnummer, List<Ware> waren) {
        this.Lieferscheinnummer = lieferscheinnummer;
        this.Waren = waren;
    }
}
