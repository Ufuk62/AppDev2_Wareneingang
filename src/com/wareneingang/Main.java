package com.wareneingang;

import com.wareneingang.daten.Datenbank;
import com.wareneingang.daten.Kunde;
import com.wareneingang.daten.Lieferung;
import com.wareneingang.daten.Ware;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {
            Datenbank datenbank = new Datenbank();
            Kunde kunde = datenbank.getKunde(3);

            Enumeration<Lieferung> enumeration = kunde.getLieferungen().elements();

            while (enumeration.hasMoreElements()) {
                Lieferung lieferung = enumeration.nextElement();
                int i = 0;

                Set<Ware> waren = lieferung.getWaren().keySet();
                Iterator<Ware> iterator = waren.iterator();

                while (iterator.hasNext()) {
                    Ware ware = iterator.next();
                    if (i%3 != 0) {
                        lieferung.angenommeneWaren.put(ware, lieferung.getWaren().get(ware));
                    } else {
                        lieferung.abgelehnteWaren.put(ware, lieferung.getWaren().get(ware));
                    }

                    i++;
                }
            }

            datenbank.save(kunde);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}