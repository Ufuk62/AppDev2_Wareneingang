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

    private List<Ware> getLieferungsWaren(String lieferungsnummer) throws SQLException {
        List<Ware> list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Hashtable data = new Hashtable();
            Ware ware = new Ware();

            list.add(ware);
        }

        return list;
    }

    public Hashtable<String, Lieferung> getLieferungen(int kundennummer) throws SQLException {
        Hashtable<String, Lieferung> lieferungen = new Hashtable<String, Lieferung>();

        String sql = "SELECT lieferungsnummer, lieferscheinnummer FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            String lieferungsnummer = set.getString("lieferungsnummer");

            Lieferung lieferung = new Lieferung(
                    lieferungsnummer,
                    this.getLieferschein(set.getString("lieferscheinnummer"))
            );

            lieferungen.put(lieferungsnummer, lieferung);
        }

        return lieferungen;
    }

    private List<Ware> getLieferscheinWaren(String lieferscheinnummer) throws SQLException {
        List<Ware> list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM fuehrt_auf WHERE lieferscheinnummer='" + lieferscheinnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Ware ware = new Ware();

            list.add(ware);
        }

        return list;
    }

    public Lieferschein getLieferschein(String lieferungsnummer) throws SQLException {
        Lieferschein lieferschein = null;

        String sql = "SELECT lieferscheinnummer FROM lieferschein WHERE lieferscheinnummer='" + lieferungsnummer + "';";

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            lieferschein = new Lieferschein();
        }

        return lieferschein;
    }
}
