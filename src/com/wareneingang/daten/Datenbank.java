package com.wareneingang.daten;

import javax.swing.plaf.nimbus.State;
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
        String sql = "SELECT vorname, nachname FROM kunde WHERE kundennummer='" + kundennummer + "'";

        Statement stat = this.con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        Kunde kunde = null;

        while (set.next()) {
            kunde = new Kunde(
                    kundennummer,
                    set.getString("vorname"),
                    set.getString("nachname"),
                    this.getLieferungen(kundennummer)
            );
        }

        return kunde;
    }

    private Hashtable<Ware, Integer> getLieferungsWaren(int lieferungsnummer) throws SQLException {
        Hashtable<Ware, Integer> waren = new Hashtable<>();

        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = this.getWare( set.getInt("warennummer") );

            waren.put(ware, set.getInt("stueckzahl"));


        }

        return waren;
    }

    private Hashtable<Integer, Lieferung> getLieferungen(int kundennummer) throws SQLException {
        Hashtable<Integer, Lieferung> lieferungen = new Hashtable<>();

        String sql = "SELECT lieferungsnummer, eingangsdatum, lieferscheinnummer, abgeschlossen FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            int lieferungsnummer = set.getInt("lieferungsnummer");

            Lieferung lieferung = new Lieferung(
                    lieferungsnummer,
                    set.getDate("eingangsdatum"),
                    this.getLieferschein(set.getInt("lieferscheinnummer")),
                    this.getLieferungsWaren(lieferungsnummer),
                    set.getBoolean("abgeschlossen")
            );

            lieferungen.put(lieferungsnummer, lieferung);

            this.fillAbgelehntAngenommen(lieferung);
        }

        return lieferungen;
    }

    private void fillAbgelehntAngenommen(Lieferung lieferung) throws SQLException {
        Set<Ware> waren = lieferung.getWaren().keySet();
        Iterator<Ware> iterator = waren.iterator();

        while (iterator.hasNext()) {
            Ware ware = iterator.next();

            String sql = "SELECT stueckzahl FROM angenommen " +
                    "WHERE lieferungsnummer=" + lieferung.getLieferungsnummer() +
                    " AND warennummer=" + ware.getWarennummer() + ";";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lieferung.addAngenommeneWare(ware, resultSet.getInt("stueckzahl"));
            }

            sql = "SELECT stueckzahl FROM abgelehnt " +
                    "WHERE lieferungsnummer=" + lieferung.getLieferungsnummer() +
                    " AND warennummer=" + ware.getWarennummer() + ";";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lieferung.addAbgelehnteWare(ware, resultSet.getInt("stueckzahl"));
            }
        }
    }

    private Hashtable<Ware, Integer> getLieferscheinWaren(int lieferscheinnummer) throws SQLException {
        Hashtable<Ware, Integer> waren = new Hashtable<>();

        String sql = "SELECT warennummer, stueckzahl FROM fuehrt_auf WHERE lieferscheinnummer='" + lieferscheinnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = this.getWare(set.getInt("warennummer"));

            waren.put(ware, set.getInt("stueckzahl"));
        }

        return waren;
    }

    private Lieferschein getLieferschein(int lieferscheinnummer) throws SQLException {
        Lieferschein lieferschein = null;

        String sql = "SELECT versanddatum FROM lieferschein WHERE lieferscheinnummer='" + lieferscheinnummer + "';";

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            lieferschein = new Lieferschein(
                    lieferscheinnummer,
                    resultSet.getDate("versanddatum"),
                    this.getLieferscheinWaren(lieferscheinnummer)
            );
        }

        return lieferschein;
    }

    public Ware getWare(int warennummer) throws SQLException {
        Ware ware = null;

        String sql = "SELECT bezeichnung, preis FROM ware WHERE warennummer=" + warennummer + ";";

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            ware = new Ware(
                    warennummer,
                    resultSet.getString("bezeichnung"),
                    resultSet.getDouble("preis")
            );
        }

        return ware;
    }

    public boolean save(Kunde kunde) throws SQLException {
        Collection<Lieferung> lieferungen = kunde.getLieferungen().values();
        Iterator<Lieferung> lieferungenIterator = lieferungen.iterator();

        while (lieferungenIterator.hasNext()) {
            Lieferung lieferung = lieferungenIterator.next();

            Set<Ware> angenommene = lieferung.getAngenommeneWaren().keySet();
            Iterator<Ware> angenommeneIterator = angenommene.iterator();

            while (angenommeneIterator.hasNext()) {
                Ware ware = angenommeneIterator.next();
                String insert = "INSERT INTO angenommen (lieferungsnummer, warennummer, stueckzahl) VALUES (" +
                        lieferung.getLieferungsnummer() + ", " +
                        ware.getWarennummer() + ", " +
                        lieferung.getAngenommeneWaren().get(ware) + ");";
                String update = "UPDATE angenommen SET stueckzahl=" +
                        lieferung.getAngenommeneWaren().get(ware) +
                        " WHERE lieferungsnummer=" + lieferung.getLieferungsnummer() +
                        " AND warennummer=" + ware.getWarennummer() + ";";
                Statement statement = con.createStatement();
                try {
                    statement.executeUpdate(insert);
                } catch (SQLIntegrityConstraintViolationException e) {
                    statement.executeUpdate(update);
                }
            }

            Set<Ware> abgelehnte = lieferung.getAbgelehnteWaren().keySet();
            Iterator<Ware> abgelehnteIterator = abgelehnte.iterator();

            while (abgelehnteIterator.hasNext()) {
                Ware ware = abgelehnteIterator.next();
                String insert = "INSERT INTO abgelehnt (lieferungsnummer, warennummer, stueckzahl) VALUES (" +
                        lieferung.getLieferungsnummer() + ", " +
                        ware.getWarennummer() + ", " +
                        lieferung.getAbgelehnteWaren().get(ware) + ");";
                String update = "UPDATE abgelehnt SET stueckzahl=" +
                        lieferung.getAbgelehnteWaren().get(ware) +
                        " WHERE lieferungsnummer=" + lieferung.getLieferungsnummer() +
                        " AND warennummer=" + ware.getWarennummer() + ";";
                Statement statement = con.createStatement();
                try {
                    statement.executeUpdate(insert);
                } catch (SQLIntegrityConstraintViolationException e) {
                    statement.executeUpdate(update);
                }
            }

        }

        return true;
    }
}
