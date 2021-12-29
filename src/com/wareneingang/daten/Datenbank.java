package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Stellt die Verbindung zur Datenbank her und kann ein Objekt der Klasse <c>Kunde</c> ausgeben,
 * das alle anderen Daten enthält.
 * Zusätzlich kann ein Objekt der Klasse Kunde mit den dazu gehörenden Daten gespeichert werden.
 *
 */
public interface Datenbank extends Remote {
    /**
     * Soll aus der Datenbank alle mit einem Kunden verbundenen Daten lesen.
     * Das schließt die Lieferungen, deren Lieferscheine und deren zugeordneten Waren ein.
     *
     * @param kundennummer Die Kundennummer eines Kunden als <c>Integer</c>
     * @return Ein Objekt der Klasse <c>Kunde</c>
     * @throws RemoteException Tritt auf, wenn ein Fehler beim Aufrufen über die Remoteverbindung auftritt.
     * @throws SQLException Tritt auf, wenn ein Fehler beim Ausführen von SQL-Befehlen auftritt.
     */
    public abstract Kunde getKunde(int kundennummer) throws RemoteException, SQLException;

    /**
     * Speichert ein Objekt der Klasse Kunde in die DB, mit allen zugehörigen Daten.
     *
     * @param kunde Das Objekt, das gespeichert werden soll.
     * @return Ein boolean, der angibt, ob das Speichern erfolgreich war.
     * @throws SQLException
     * @throws RemoteException
     */
    public abstract boolean save(Kunde kunde) throws SQLException, RemoteException;
}