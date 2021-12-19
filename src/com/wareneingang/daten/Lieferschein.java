package com.wareneingang.daten;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

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

    public Ware getWare(int warennummer) {
        Set<Ware> waren = this.waren.keySet();
        Iterator<Ware> iterator = waren.iterator();

        while (iterator.hasNext()) {
            Ware ware = iterator.next();

            if (ware.getWarennummer() == warennummer) {
                return ware;
            }
        }

        return null;
    }

    public int getStueckzahl(Ware ware) {
        return waren.get(ware);
    }

    public Set<Ware> getWaren() {
        return waren.keySet();
    }
}