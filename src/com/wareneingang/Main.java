package com.wareneingang;

import com.wareneingang.praesentation.InterfaceIO;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            InterfaceIO io = new InterfaceIO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
}