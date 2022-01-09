package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Bildet eine Ware mit ID, Preis und Bezeichnung ab.
 */
public interface Ware extends Remote {
    /**
     * Wird die Warennummer der Ware ausgeben.
     * @return Die Warennummer als <c>int</c>
     * @throws RemoteException
     */
    public abstract int getWarennummer() throws RemoteException;

    /**
     * Wird den Preis der Ware ausgeben.
     * @return Der Peris als <c>double</c>
     * @throws RemoteException
     */
    public abstract double getPreis() throws RemoteException;

    /**
     * Wird die Warenbezeichnung ausgeben.
     * @return Die Warenbezeichnung als <c>String</c>
     * @throws RemoteException
     */
    public abstract String getWarenbezeichnung() throws RemoteException;
}