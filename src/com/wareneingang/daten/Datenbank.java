package com.wareneingang.daten;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

/**
 * Die Klasse stellt alle Verbindungen zur Datenbank bereit.
 * Es kann allerdings nur ein Objekt vom Typ <c>Kunde</c> ausgegeben werden,
 * welches die damit verbundenen Objekte beinhaltet
 *
 * Für die Verwendung müssen die vier Attribute die mit einem '_' beginnen angepasst werden,
 * je nachdem welche Datenbank verwendet wird, wie diese angesprochen wird und welche Zugangsdaten verwendet werden.
 */
public class Datenbank {
    private static final String _Url = "jdbc:mariadb://localhost/appdev";
    private static final String _Driver = "org.mariadb.jdbc.Driver";
    private static final String _User = "root";
    private static final String _Password = "";

    private Connection con = null;

    /**
     * Der Konstruktor startet bei Instanziierung eines Datenbank-Objektes die Verbindung zur eigentlichen Datenbank.
     * Zuvor wird noch getestet, ob die Klasse des Treibers existiert.
     *
     * @throws SQLException Diese tritt auf, wenn keine Verbindung zur Datenbank hergestellt werden kann.
     */
    public Datenbank() throws SQLException {
        try {
            Class.forName(_Driver);
            this.con = DriverManager.getConnection(_Url, _User, _Password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Die Methode liest aus der Datenbank die Daten des Kunden selbst und die Daten der Lieferungen,
     * die mit dem Kunden verbunden sind, aus.
     * Es gibt, sobald alle Daten gelesen und in Objekte umgewandelt wurden, ein Objekt der Klasse <c>Kunde</c> aus.
     *
     * @param kundennummer Die Kundennummer des Kunden, der aus der DB gelesen werden soll.
     * @return Ein Objekt der Klasse <c>Kunde</c> oder <c>null</c>, wenn keiner mit der Kundennummer gefunden wurde.
     * @throws SQLException Tritt auf, wenn etwas beim Ausführen des SQL-Statements fehlschlägt.
     */
    public Kunde getKunde(int kundennummer) throws SQLException {
        // Liest aus der Datenbank alle zusätzlichen Daten des Kunden aus.
        String sql = "SELECT vorname, nachname FROM kunde WHERE kundennummer='" + kundennummer + "'";

        Statement stat = this.con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        // Das Kunden-Objekt wird hier erst null gesetzt, damit zwischen erfolgreichem und nicht-erfolgreichem lesen
        // aus der Datenbank unterschieden werden kann.
        Kunde kunde = null;

        // Die Schleife wird nur betreten, wenn mindestens eine Zeile aus der Datenbank gelesen wurde.
        // Somit wird kein Objekt von Kunde zurückgegeben, wenn eine Kundennummer nicht existiert.
        // Da die Kundennummer in der DB Primärschlüssel ist, wird auch maximal eine gelesen.
        while (set.next()) {
            kunde = new Kunde(
                    kundennummer,
                    set.getString("vorname"),
                    set.getString("nachname"),
                    // Started den Prozess die Lieferungen des Kunden zu erhalten.
                    this.getLieferungen(kundennummer)
            );
        }

        return kunde;
    }

    /**
     * Gibt eine Hashtable zurück, die die Lieferungen für einen Kunden beinhaltet.
     * Die Hashtable benutzt als Schlüssel die Lieferungsnummer, um die Eingabe in der Konsole zu ermöglichen.
     *
     * @param kundennummer Die Kundennummer des Kunden, dem diese Lieferungen zugeordnet sind.
     * @return Eine <c>Hashtable</c> mit der Lieferungsnummer als Schlüssel und einem <c>Lieferung</c>-Objekt als Wert.
     * @throws SQLException Tritt auf, wenn etwas beim Ausführen des SQL-Statements fehlschlägt.
     */
    private Hashtable<Integer, Lieferung> getLieferungen(int kundennummer) throws SQLException {
        // Die Hashtable für die Lieferung wird hier Instanziiert.
        // Werden keine Lieferungen in der Datenbank gefunden wird sie so zurückgegeben.
        Hashtable<Integer, Lieferung> lieferungen = new Hashtable<>();

        // Liest aus der Datenbank eine Liste der Lieferungen mit allen zusätzlichen Daten aus.
        String sql = "SELECT lieferungsnummer, eingangsdatum, lieferscheinnummer, abgeschlossen FROM lieferung WHERE kundennummer='" + kundennummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            int lieferungsnummer = set.getInt("lieferungsnummer");

            Lieferung lieferung = new Lieferung(
                    lieferungsnummer,
                    set.getDate("eingangsdatum"),
                    // Startet den Prozess den Lieferschein aus der Datenbank zu lesen.
                    this.getLieferschein(set.getInt("lieferscheinnummer")),
                    // Startet den Prozess, die der Lieferung zugeordneten Waren aus der DB zu lesen.
                    this.getLieferungsWaren(lieferungsnummer),
                    set.getBoolean("abgeschlossen")
            );

            lieferungen.put(lieferungsnummer, lieferung);

            this.fillAbgelehntAngenommen(lieferung);
        }

        return lieferungen;
    }

    /**
     * Gibt die einer Lieferung angehörigen Waren in einer Hashtable aus.
     * Die <c>Hashtable</c> benutzt das Objekt der Klasse <c>Ware</c> als Schlüssel, um es möglich zu machen, die Stückzahl
     * der Ware mit in der <c>Hashtable</c> zu speichern.
     *
     * @param lieferungsnummer Die Lieferungsnummer der Lieferung, zu der die Waren gehören.
     * @return Eine <c>Hashtable</c> mit dem <c>Ware</c>-Objekt als Schlüssel und der Stückzahl als Wert.
     * @throws SQLException Tritt auf, wenn etwas beim Ausführen des SQL-Statements fehlschlägt.
     */
    private Hashtable<Ware, Integer> getLieferungsWaren(int lieferungsnummer) throws SQLException {
        // Die Hashtable für die Waren wird hier Instanziiert.
        // Werden keine Waren in der Datenbank gefunden wird sie so zurückgegeben.
        Hashtable<Ware, Integer> waren = new Hashtable<>();

        // Liest aus der Datenbank eine Liste der Wareb mit allen zusätzlichen Daten aus.
        String sql = "SELECT warennummer, stueckzahl FROM enthaelt WHERE lieferungsnummer='" + lieferungsnummer + "'";

        Statement stat = con.createStatement();
        ResultSet set = stat.executeQuery(sql);

        while (set.next()) {
            // Startet den Prozess aus der DB die zusätzlichen Daten der Ware zu lesen
            Ware ware = this.getWare( set.getInt("warennummer") );

            waren.put(ware, set.getInt("stueckzahl"));
        }

        return waren;
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
