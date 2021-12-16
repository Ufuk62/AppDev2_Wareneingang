package com.wareneingang.daten;

import java.util.Date;
import java.util.Hashtable;

public class Lieferschein {
    private int lieferscheinnummer;
    private Hashtable<Ware, Integer> waren;
    private Date versanddatum;

    public Lieferschein (int lieferscheinnummer, Date versanddatum, Hashtable<Ware, Integer> waren) {
        this.lieferscheinnummer = lieferscheinnummer;
        this.waren = waren;
        this.versanddatum = versanddatum;
    }

    public int getLieferscheinnummer() {
        return this.lieferscheinnummer;
    }
}