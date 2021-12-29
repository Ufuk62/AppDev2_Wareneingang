package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

public interface Lieferung extends Remote {
    public abstract Lieferschein getLieferschein() throws RemoteException;

    public abstract Hashtable<Ware, Integer> getWaren() throws RemoteException;

    public abstract void wareAnnehmen(Ware ware, int stueckzahl) throws RemoteException;
    public abstract void wareAblehnen(Ware ware, int stueckzahl) throws RemoteException;

    public abstract void abschliessen() throws RemoteException;

    public abstract int getLieferungsnummer() throws RemoteException;

    public abstract Hashtable<Ware, Integer> getAbgelehnteWaren() throws RemoteException;

    public abstract Hashtable<Ware, Integer> getAngenommeneWaren() throws RemoteException;

    public abstract boolean isAbgeschlossen() throws RemoteException;

    public abstract void setQualitaet(int warennummer, boolean qualitaet) throws RemoteException;

    public abstract Date getEingangsdatum() throws RemoteException;

    public abstract void addAngenommeneWare(Ware ware, int stueckzahl) throws RemoteException;

    public abstract void addAbgelehnteWare(Ware ware, int stueckzahl) throws RemoteException;

    public abstract double getGesamtpreis() throws RemoteException;
}