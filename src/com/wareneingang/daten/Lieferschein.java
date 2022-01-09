package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Bildet einen Lieferschein mit zugeordneten Waren ab.
 */
public interface Lieferschein extends Remote {
    /**
     * Wird eine zugeordnete Ware anhand der Warennummer finden und zurückgeben.
     * @param warennummer Die Warennummer der zurückzugebenden Ware.
     * @return Ein Objekt der Klasse Ware.
     * @throws RemoteException
     */
    public abstract Ware getWare(int warennummer) throws RemoteException;

    /**
     * Wird die Stückzahl der zugeordneten Ware anhand der Warennummer zurückgeben.
     * @param warennummer Die Warennummer, für deren Ware die stückzahl gebraucht wird.
     * @return Die Stückzahl als <c>int</c>
     * @throws RemoteException
     */
    public abstract int getStueckzahl(int warennummer) throws RemoteException;

    /**
     * Wird eine Liste aller zugeordnete Waren zurückgeben.
     * @return Eine ArrayList mit allen Waren.
     * @throws RemoteException
     */
    public abstract ArrayList<Ware> getWaren() throws RemoteException;
}