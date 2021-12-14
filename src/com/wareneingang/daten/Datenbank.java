package com.wareneingang.daten;

import java.sql.*;
import java.util.*;

public class Datenbank {
    private static final String _Url = "jdbc:mariadb://localhost/appdev";
    private static final String _Driver = "org.mariadb.jdbc.Driver";
    private static final String _User = "root";
    private static final String _Password = "";
    private Connection con = null;

    public Datenbank() throws SQLException {
        try {
            Class.forName(_Driver);
            this.con = DriverManager.getConnection(_Url, _User, _Password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Kunde getKunde(int kundennummer) throws SQLException {
        String sql = "SELECT kundennummer FROM kunde WHERE kundennummer='" + kundennummer + "'";

        Statement stat = this.con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        Kunde kunde = null;

        while (set.next()) {
            kunde = new Kunde(kundennummer, this.getLieferungen(kundennummer));
        }

        return kunde;
    }

    private Hashtable<Ware, Integer> getLieferungsWaren(int lieferungsnummer) throws SQLException {
        Hashtable<Ware, Integer> waren = new Hashtable<>();

        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = new Ware( Integer.parseInt(set.getString("warennummer")) );

            waren.put(ware, set.getInt("stueckzahl"));
        }

        return waren;
    }

    private Hashtable<Integer, Lieferung> getLieferungen(int kundennummer) throws SQLException {
        Hashtable<Integer, Lieferung> lieferungen = new Hashtable<>();

        String sql = "SELECT lieferungsnummer, lieferscheinnummer FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            int lieferungsnummer = set.getInt("lieferungsnummer");

            Lieferung lieferung = new Lieferung(
                    lieferungsnummer,
                    this.getLieferschein(set.getString("lieferscheinnummer")),
                    this.getLieferungsWaren(lieferungsnummer)
            );

            lieferungen.put(lieferungsnummer, lieferung);
        }

        return lieferungen;
    }

    private Hashtable<Ware, Integer> getLieferscheinWaren(String lieferscheinnummer) throws SQLException {
        Hashtable<Ware, Integer> waren = new Hashtable<>();

        String sql = "SELECT warennummer, stueckzahl FROM fuehrt_auf WHERE lieferscheinnummer='" + lieferscheinnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = new Ware( Integer.parseInt(set.getString("warennummer")) );

            waren.put(ware, set.getInt("stueckzahl"));
        }

        return waren;
    }

    private Lieferschein getLieferschein(String lieferungsnummer) throws SQLException {
        Lieferschein lieferschein = null;

        String sql = "SELECT lieferscheinnummer FROM lieferschein WHERE lieferscheinnummer='" + lieferungsnummer + "';";

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            lieferschein = new Lieferschein(this.getLieferscheinWaren(
                    resultSet.getString("lieferscheinnummer")
            ));
        }

        return lieferschein;
    }

    public boolean save(Kunde kunde) {
        Collection<Lieferung> lieferungen = kunde.getLieferungen().values();
        Iterator<Lieferung> lieferungenIterator = lieferungen.iterator();

        while (lieferungenIterator.hasNext()) {
            Lieferung lieferung = lieferungenIterator.next();
            System.out.println("Lieferung:\t" + lieferung.getLieferungsnummer());

            Set<Ware> angenommene = lieferung.getAngenommeneWaren().keySet();
            Iterator<Ware> angenommeneIterator = angenommene.iterator();

            while (angenommeneIterator.hasNext()) {
                Ware ware = angenommeneIterator.next();
                System.out.println("\tAngenommen: " + lieferung.getAngenommeneWaren().get(ware) + "x " + ware.getWarennummer());
            }

            Set<Ware> abgelehnte = lieferung.getAbgelehnteWaren().keySet();
            Iterator<Ware> abgelehnteIterator = abgelehnte.iterator();

            while (abgelehnteIterator.hasNext()) {
                Ware ware = abgelehnteIterator.next();
                System.out.println("\tAbgelehnt: " + lieferung.getAbgelehnteWaren().get(ware) + "x " + ware.getWarennummer());
            }

        }

        return false;
    }
}
