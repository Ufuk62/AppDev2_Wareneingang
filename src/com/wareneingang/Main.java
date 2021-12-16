package com.wareneingang;

import com.wareneingang.daten.Datenbank;
import com.wareneingang.daten.Kunde;
import com.wareneingang.daten.Lieferung;
import com.wareneingang.daten.Ware;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {
            Datenbank datenbank = new Datenbank();
            Kunde kunde = datenbank.getKunde(1);
            System.out.println(kunde.getName());

            Enumeration<Lieferung> enumeration = kunde.getLieferungen().elements();

            while (enumeration.hasMoreElements()) {
                Lieferung lieferung = enumeration.nextElement();

                Set<Ware> waren = lieferung.getWaren().keySet();
                Iterator<Ware> iterator = waren.iterator();

                while (iterator.hasNext()) {
                    Ware ware = iterator.next();
                    Hashtable<Ware, Integer> an = lieferung.getAngenommeneWaren();
                    Hashtable<Ware, Integer> ab = lieferung.getAbgelehnteWaren();

                    if (an.containsKey(ware)) {
                        System.out.println("Angenommen: " + ware.getWarennummer() + " (" + an.get(ware) + ")");
                    } else if (ab.containsKey(ware)) {
                        System.out.println("Abgelehnt: " + ware.getWarennummer() + " (" + ab.get(ware) + ")");
                    } else {
                        System.out.println("WHAT?");
                    }
                }
            }

            datenbank.save(kunde);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}