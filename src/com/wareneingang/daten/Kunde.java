package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface Kunde extends Remote {

    public abstract Lieferung getLieferung(int lieferungsnummer) throws RemoteException;

    public abstract Hashtable<Integer, Lieferung> getLieferungen() throws RemoteException;

    public abstract String getName() throws RemoteException;
}
