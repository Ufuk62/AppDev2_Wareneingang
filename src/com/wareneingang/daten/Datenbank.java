package com.wareneingang.daten;

import java.sql.*;
import java.util.*;

public class Datenbank {
    private static String _Url = "jdbc:mariadb://localhost/appdev";
    private static String _Driver = "org.mariadb.jdbc.Driver";
    private static String _User = "root";
    private static String _Password = "";
    private Connection con = null;

    public Datenbank() throws SQLException {
        try {
            Class.forName(_Driver);
            this.con = DriverManager.getConnection(_Url, _User, _Password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kunde getKunde(String kundennummer) throws SQLException {
        String sql = "SELECT kundennummer FROM kunde WHERE kundennummer='" + kundennummer + "'";

        Statement stat = this.con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        Kunde kunde = null;

        while (set.next()) {
            kunde = new Kunde(set.getString("kundennummer"));
        }

        return kunde;
    }

    private List<Ware> getLieferungsWaren(String lieferungsnummer) throws SQLException {
        List<Ware> list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Hashtable data = new Hashtable();
            Ware ware = new Ware(
                    set.getString("warennummer"),
                    set.getInt("stueckzahl")
            );

            list.add(ware);
        }

        return list;
    }

    public List<Lieferung> getLieferungen(String kundennummer) throws SQLException {
        List<Lieferung> list = new Vector();

        String sql = "SELECT lieferungsnummer, lieferscheinnummer FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            String lieferungsnummer = set.getString("lieferungsnummer");

            Lieferung lieferung = new Lieferung(
                    lieferungsnummer,
                    set.getString("lieferscheinnummer"),
                    kundennummer
            );

            list.add(lieferung);
        }

        return list;
    }

    private List<Ware> getLieferscheinWaren(String lieferscheinnummer) throws SQLException {
        List<Ware> list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM fuehrt_auf WHERE lieferscheinnummer='" + lieferscheinnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = new Ware(
                    set.getString("warennummer"),
                    set.getInt("stueckzahl")
            );

            list.add(ware);
        }

        return list;
    }

    public Lieferschein getLieferschein(String lieferscheinnummer) throws SQLException {
        return new Lieferschein();
    }
}
