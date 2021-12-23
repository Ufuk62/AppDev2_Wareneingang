package com.wareneingang.praesentation;

import com.wareneingang.daten.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class Ausgabe {

    public static void AusgabeVergleichLieferungLieferschein() throws SQLException {
        Datenbank datenbank = null;
        try {
            datenbank = new Datenbank();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Kunde kunde = datenbank.getKunde(6);

        Enumeration<Lieferung> enumeration = kunde.getLieferungen().elements();

        while (enumeration.hasMoreElements()) {
            Lieferung lieferung = enumeration.nextElement();
            Lieferschein lieferschein = lieferung.getLieferschein();
            System.out.println(lieferschein.getLieferscheinnummer());

            Set<Ware> waren = lieferung.getWaren().keySet();
            Iterator<Ware> iterator = waren.iterator();
            System.out.println("\t\t\t\t\t\tLieferung\t\t\t\t\t\t|\t\t\t\t\t\tLieferschein\t\t\t\t\t\t");
            System.out.println("--------------------------------------------------------|" +
                    "--------------------------------------------------------"
            );
            System.out.println(" Waren-Nr.\t| Bezeichnung\t\t\t\t| Stueckzahl\t|" +
                    " Waren-Nr.\t| Bezeichnung\t\t\t\t| Stueckzahl\t"
            );
            System.out.println("------------+---------------------------+---------------|" +
                    "-----------+---------------------------+----------------"
            );

            Set<Ware> lieferscheinWaren = lieferschein.getWaren();

            Vector<Ware> alleWaren = new Vector<>();

            while (iterator.hasNext()) {
                Ware ware = iterator.next();
                alleWaren.add(ware);
                Ware lieferscheinWare = lieferschein.getWare(ware.getWarennummer());

                System.out.print(" " + ware.getWarennummer() + "\t\t\t| " + ware.getWarenbezeichnung() +
                        " ".repeat(25 - ware.getWarenbezeichnung().length()) + "\t| " +
                        lieferung.getWaren().get(ware) + " \t\t\t|"
                );
                if (lieferscheinWare != null) {
                    System.out.println(" " + lieferscheinWare.getWarennummer() +
                            " \t\t| " + lieferscheinWare.getWarenbezeichnung() +
                            " ".repeat(25 - lieferscheinWare.getWarenbezeichnung().length()) + "\t| " +
                            lieferschein.getStueckzahl(lieferscheinWare)
                    );
                    lieferscheinWaren.remove(lieferscheinWare);
                } else {
                    System.out.println("\t".repeat(3) + "\u001B[31m Das Element wurde nicht gefunden \u001B[0m");
                }
            }
        }
    }
}
