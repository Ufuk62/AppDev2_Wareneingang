package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ware extends Remote {
    public abstract int getWarennummer() throws RemoteException;

    public abstract double getPreis() throws RemoteException;

    public abstract String getWarenbezeichnung() throws RemoteException;
}