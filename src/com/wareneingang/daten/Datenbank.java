package com.wareneingang.daten;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Datenbank extends Remote {
    public abstract Kunde getKunde(int kundennummer) throws RemoteException, SQLException;
}