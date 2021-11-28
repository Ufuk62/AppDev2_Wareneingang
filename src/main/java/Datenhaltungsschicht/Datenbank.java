package Datenhaltungsschicht;
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

    public Hashtable getKunde(String kundennummer) throws SQLException {
        Hashtable data = new Hashtable();

        String sql = "SELECT kundennummer FROM kunde WHERE kundennummer='" + kundennummer + "'";

        Statement stat = this.con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            data.put("kundennummer", set.getString("kundennummer"));
        }
        return data;
    }

    public List getLieferungen(String kundennummer) throws SQLException {
        List list = new Vector();

        String sql = "SELECT lieferungsnummer, lieferscheinnummer FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Hashtable data = new Hashtable();

            data.put("lieferungsnummer", set.getString("lieferungsnummer"));
            data.put("lieferscheinnummer", set.getString("lieferscheinnummer"));

            list.add(data);
        }

        return list;
    }

    public List getLieferung(String lieferungsnummer) throws SQLException {
        List list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Hashtable data = new Hashtable();

            data.put("warennummer", set.getString("warennummer"));
            data.put("steuckzahl", set.getString("stueckzahl"));

            list.add(data);
        }

        return list;
    }

    public List getLieferschein(String lieferscheinnummer) throws SQLException {
        List list = new Vector();

        String sql = "SELECT warennummer, stueckzahl FROM fuehrt_auf WHERE lieferscheinnummer='" + lieferscheinnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            Hashtable data = new Hashtable();

            data.put("warennummer", set.getString("warennummer"));
            data.put("stueckzahl", set.getString("stueckzahl"));

            list.add(data);
        }

        return list;
    }

    public Hashtable getWareninfo(String warennummer) throws SQLException {
        Hashtable data = new Hashtable();

        String sql = "SELECT warennummer FROM ware WHERE warennummer='" + warennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            data.put("warennummer", set.getString("warennummer"));
        }

        return data;
    }
}