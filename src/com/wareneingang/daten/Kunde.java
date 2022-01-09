package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * Bildet einen Kunden ab, dem Lieferungen zugeordnet sind.
 * Der Kunde besitzt einen Namen, der technisch nicht notwendig ist, aber logisch sinnvoll ist.
 */
public interface Kunde extends Remote {
    /**
     * Wird eine Lieferung anhand der Lieferungsnummer finden und ausgeben.
     * @param lieferungsnummer Die Lieferungsnummer der Lieferung die ausgegeben werden soll als <c>int<c/>.
     * @return Ein Objekt der Klasse Lieferung.
     * @throws RemoteException
     */
    public abstract Lieferung getLieferung(int lieferungsnummer) throws RemoteException;

    /**
     * Wird alle zum Kunden gehörenden Lieferungen ausgeben.
     * @return Eine Hashtable mit allen Lieferungen. Nutzt als key die Lieferungsnummer.
     * @throws RemoteException
     */
    public abstract Hashtable<Integer, Lieferung> getLieferungen() throws RemoteException;

    /**
     * Wird den zusammengefügten Namen des Kunden zurückgeben.
     * @return Ein String, der den Namen des Kunden erhält.
     * @throws RemoteException
     */
    public abstract String getName() throws RemoteException;
}