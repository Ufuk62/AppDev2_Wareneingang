package com.wareneingang;

import com.wareneingang.daten.Datenbank;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Datenbank datenbank = new Datenbank();
            System.out.println(datenbank.getKunde("3"));
            System.out.println(datenbank.getLieferungen("3"));
            System.out.println(datenbank.getLieferschein("3"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}