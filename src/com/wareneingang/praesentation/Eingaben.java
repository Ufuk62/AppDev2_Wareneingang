package com.wareneingang.praesentation;

import com.wareneingang.daten.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eingaben {
    Scanner scanner = new Scanner(System.in);
    // Als erstes Eingabe Kundennummer -> Ausgabe Liste der Lieferungen von dem Kunden.
    // Danach Auswahl der Lieferung -> EPK Ablauf fortsetzen
    // (1. Ist Ware in Lieferschein gleich mit Lieferung? -> Qualiaet/Stueckzahl prüfen. Wenn alles Ok. Bezahlen, sonst Teil oder alles Ablehnen)

    public void wareneingang(Kunde kunde) throws RemoteException, SQLException {

        KundennummerEingebenAlleLieferungenAusgeben();
        LieferungsnummerEingebenEineLieferungAusgeben();
        MangelVorhanden();
        Teilannahme();
    }

    private void KundennummerEingebenAlleLieferungenAusgeben(){
        int kundennummer = 0;
        Lieferung lieferung = null;

        while (lieferung == null) {
            try {
                System.out.print("Bitte geben Sie die kundennummer ein: ");
                kundennummer = scanner.nextInt();
                lieferung = kunde.getLieferungen(kundennummer);

            } catch (LieferungNotFoundException e) {
                System.out.printf("Es konnte keine Lieferung mit der Kundennummer" + kundennummer + "gefunden werden.\n");
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        }
    }

    private void LieferungsnummerEingebenEineLieferungAusgeben () {
        int lieferungsnummer = 0;
        Lieferung lieferung = null;

        while (lieferung == null) {
            try {
                System.out.print("Bitte geben Sie die Lieferungsnummer ein: ");
                lieferungsnummer = scanner.nextInt();
                lieferung = kunde.getLieferung(lieferungsnummer);

            } catch (LieferungNotFoundException e) {
                System.out.printf("Es konnte keine Lieferung mit der Nummer" + lieferungsnummer + "gefunden werden.\n");
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        }

        System.out.println("\n" + String.format("Lieferung " + lieferungsnummer + " des Lieferscheins" + lieferscheinnummer +"(Lieferdatum:" + LocalDateTime. now())); // TODO: Datum lassen?

        if (lieferung.getAngenommen() != Lieferung.AngenommenEnum.NEIN) {
            System.err.println("Die Lieferung wurde bereits bearbeitet und Angenommen");
            return;
        }
        // Lieferung ausgeben
        //
        System.out.println(lieferung);
    }

    private void MangelVorhanden(){
        System.out.println("Bitte prüfen Sie die Lieferung nach ihrer Qualität und Stückzahl!");
        while(true) {
            try {
                System.out.println("\nIst ein Mangel in der Lieferung vorhanden? (ja|nein) ");
                String input = scanner.next();

                if(input.equalsIgnoreCase("ja"))
                    break;
                else if(input.equalsIgnoreCase("nein")) {

                    // Lieferung annehmen und Programm beenden
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.JA);
                    System.out.println("Der Status der Lieferung wurde auf \"Angenommen\" gesetzt");
                    return;

                } else
                    System.out.println("Ungültige Eingabe! Geben Sie \"ja\" oder \"nein\" ein.");

            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        }
    }

    private void Teilannahme(){
        while(true); {
            try {
                System.out.println("\nWollen Sie einen Teil der Lieferung annehmen? (ja|nein) ");
                String input = scanner.next();

                if(input.equalsIgnoreCase("ja")) {
                    // Lieferung zum Teil annehmen
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.TEILWEISE);
                    System.out.println("Der Status der Lieferung wurde auf \"Teilweise angenommen\" gesetzt");
                    return;

                } else if(input.equalsIgnoreCase("nein")) {
                    // Lieferung ablehnen
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.VERWEIGERT);
                    System.out.println("Der Status der Lieferung wurde auf \"Annahme verweigert\" gesetzt");
                    return;

                } else
                    System.out.println("Ungültige Eingabe! Geben Sie \"ja\" oder \"nein\" ein.");

            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        }
    }
}
