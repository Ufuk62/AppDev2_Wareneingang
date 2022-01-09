package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

/**
 * Bildet eine Lieferung mit allen dazugehörigen Daten ab.
 */
public interface Lieferung extends Remote {
    /**
     * Wird den Lieferschein zur Lieferung ausgeben.
     * @return Ein Objekt der Klasse Lieferung.
     * @throws RemoteException
     */
    public abstract Lieferschein getLieferschein() throws RemoteException;

    /**
     * Wird die Waren der Lieferung als Hashtable ausgeben.
     * @return Eine Hashtable mit einem Ware-Objekt als key und der Stückzahl als value.
     * @throws RemoteException
     */
    public abstract Hashtable<Ware, Integer> getWaren() throws RemoteException;

    /**
     * Wird ein Attribut setzten, mit dem angezeigt wird, ob die Lieferung schon bearbeitet wurde.
     * @throws RemoteException
     */
    public abstract void abschliessen() throws RemoteException;

    /**
     * Wird die Lieferungsnummer ausgeben.
     * @return Die Lieferungsnummer als int.
     * @throws RemoteException
     */
    public abstract int getLieferungsnummer() throws RemoteException;

    /**
     * Wird eine Hashtable mit den aktuell abgelehnten Waren ausgeben.
     * @return Eine Hashtable mit einem Ware-Objekt als key und der Stückzahl als value.
     * @throws RemoteException
     */
    public abstract Hashtable<Ware, Integer> getAbgelehnteWaren() throws RemoteException;


    /**
     * Wird eine Hashtable mit den aktuell angenommen Waren ausgeben.
     * @return Eine Hashtable mit einem Ware-Objekt als key und der Stückzahl als value.
     * @throws RemoteException
     */
    public abstract Hashtable<Ware, Integer> getAngenommeneWaren() throws RemoteException;

    /**
     * Wird das Attribut ausgeben, das angibt, ob die Lieferung bearbeitet wurde.
     * @return Ob die Lieferung schon bearbeitet wurden als boolean.
     * @throws RemoteException
     */
    public abstract boolean isAbgeschlossen() throws RemoteException;

    /**
     * Wird eine Ware zu den angenommenen Waren hinzufügen, wenn Ihre Qualität akzeptiert wurde.
     * @param warennummer die Warennummer, der Ware die bearbeitet werden soll.
     * @param qualitaet Ob die Qualität angenommen wurde.
     * @throws RemoteException
     */
    public abstract void setQualitaet(int warennummer, boolean qualitaet) throws RemoteException;

    /**
     * Wird das Eingangsdatum der Lieferung ausgeben.
     * @return Das Eingangsdatum als <c>Date</c>
     * @throws RemoteException
     */
    public abstract Date getEingangsdatum() throws RemoteException;

    /**
     * Wird eine Ware anhand ihrer Warennummer mit einer Stückzahl zu den angenommenen Waren hinzufügen.
     * @param warennummer Die Warennummer der hinzuzufügenden Ware.
     * @param stueckzahl Die angenommene Stückzahl.
     * @throws RemoteException
     */
    public abstract void addAngenommeneWare(int warennummer, int stueckzahl) throws RemoteException;

    /**
     * Wird eine Ware anhand ihrer Warennummer mit einer Stückzahl zu den abgelehnten Waren hinzufügen.
     * @param warennummer Die Warennummer der hinzuzufügenden Ware.
     * @param stueckzahl Die abgelehnte Stückzahl.
     * @throws RemoteException
     */
    public abstract void addAbgelehnteWare(int warennummer, int stueckzahl) throws RemoteException;

    /**
     * Wird den Gesamtpreis der angenommenen Waren der Lieferung errechnen und ausgeben.
     * @return Der Gesamtpreis als <c>double</c>
     * @throws RemoteException
     */
    public abstract double getGesamtpreis() throws RemoteException;
}