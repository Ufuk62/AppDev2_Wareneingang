package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface Lieferschein extends Remote {
    public abstract Ware getWare(int warennummer) throws RemoteException;

    public abstract int getStueckzahl(Ware ware) throws RemoteException;

    public abstract int getStueckzahl(int warennummer) throws RemoteException;

    public abstract ArrayList<Ware> getWaren() throws RemoteException;
}