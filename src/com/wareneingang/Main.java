package com.wareneingang;

import com.wareneingang.daten.Datenbank;
import com.wareneingang.daten.Kunde;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Datenbank datenbank = new Datenbank();
            Kunde kunde = datenbank.getKunde(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}