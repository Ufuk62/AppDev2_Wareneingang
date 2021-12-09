package com.wareneingang.praesentation;

import com.wareneingang.daten.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eingaben {
    // Als erstes Einagbe Kundennummer -> Ausgabe Liste der Lieferungen von dem Kunden.
    // Danach Auswahl der Lieferung -> EPK Ablauf fortsetzen
    // (1. Ist Ware in Lieferschein gleich mit Lieferung? -> Qualiaet/Stueckzahl prüfen. Wenn alles Ok. Bezahlen, sonst Teil oder alles Ablehnen)

    public static void wareneingang() throws RemoteException, SQLException {
        Scanner scanner = new Scanner(System.in);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // liefernummer eingeben
        //
        int lieferNummer = 0;
        Lieferung lieferung = null;

        do {
            try {
                System.out.print("Bitte geben Sie die Liefernummer ein: ");
                lieferNummer = scanner.nextInt();
                lieferung = lieferung.getLieferung(lieferNummer);

            } catch (LieferungNotFoundException e) {
                System.out.printf("Es konnte keine Lieferung mit der Nummer" + lieferNummer + "gefunden werden.\n");
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        } while (lieferung == null);

        System.out.println("\n" + String.format("Lieferung " + lieferNummer + " des Lieferscheins" + lieferscheinnummer +"(Lieferdatum:" + LocalDateTime. now()));

        if (lieferung.getAngenommen() != Lieferung.AngenommenEnum.NEIN) {
            System.err.println("Die Lieferung wurde bereits bearbeitet und Angenommen");
            return;
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Lieferung beauftragt?
        //
        boolean weiter = false;
        do {
            weiter = false;
            try {
                System.out.println("\nWurden die Waren durch Sie bestellt? (ja|nein) ");
                String input = scanner.next();

                if(input.equalsIgnoreCase("ja"))
                    weiter = true;
                else if(input.equalsIgnoreCase("nein")) {

                    // Annahme der Lieferung verweigern und Vorgang abbrechen
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.VERWEIGERT);
                    System.out.println("Der Status der Lieferung wurde auf \"Annahme verweigert\" gesetzt");
                    return;

                } else
                    System.out.println("Ungültige Eingabe! Geben Sie \"ja\" oder \"nein\" ein.");

            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        } while(weiter != true);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Lieferung ausgeben
        //
        System.out.print("\n\n\n");
        System.out.println(lieferung);
        System.out.print("\n\n");

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Mangel vorhanden?
        //
        System.out.println("Bitte prüfen Sie die Lieferung nach ihrer Qualität und Stückzahl!");
        do {
            weiter = false;
            try {
                System.out.println("\nIst ein Mangel in der Lieferung vorhanden? (ja|nein) ");
                String input = scanner.next();

                if(input.equalsIgnoreCase("ja"))
                    weiter = true;
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
        } while(weiter != true);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Teilannahme
        //
        do {
            weiter = false;
            try {
                System.out.println("\nWollen Sie einen Teil der Lieferung annehmen? (ja|nein) ");
                String input = scanner.next();

                if(input.equalsIgnoreCase("ja")) {

                    // Lieferung zum Teil annehmen
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.TEILWEISE);
                    System.out.println("Der Status der Lieferung wurde auf \"Teilweise angenommen\" gesetzt");
                    weiter = true;

                } else if(input.equalsIgnoreCase("nein")) {

                    // Lieferung ablehnen
                    lieferung.setLieferungAngenommen(lieferung.getLiefernummer(), Lieferung.AngenommenEnum.VERWEIGERT);
                    System.out.println("Der Status der Lieferung wurde auf \"Annahme verweigert\" gesetzt");
                    weiter = true;

                } else
                    System.out.println("Ungültige Eingabe! Geben Sie \"ja\" oder \"nein\" ein.");

            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe!");
                scanner.next();
            }
        } while(weiter != true);

    }


    //// ALT!
    void LieferscheinKorrekt() {

        Scanner lieferungscanner = new Scanner(System.in); //System.in, da der String von der Konsole eingelesen werden soll
        Lieferung lieferungObject = new Lieferung();
        System.out.println("Tragen Sie die Lieferungsnummer ein, die sie benötigen!");
        int lieferungsnummer = lieferungscanner.nextInt();

        //Datenbankabfrage fehlt
        if ((lieferungsnummer < 25) && (lieferungsnummer != 0)) {
            lieferungObject.setLieferung(lieferungsnummer);
            System.out.println("Die Lieferung mit der Nummer:" + lieferungObject.getLieferung() + "wurde gefunden!");
            Kunde.getLieferung(lieferungsnummer);
        }
    }

    void StueckzahlKorrekt () {
        Scanner stuekzahlscanner = new Scanner(System.in);
        Lieferung lieferungObject = new Lieferung();
        System.out.println("Trage die Stückzahl/-en der Waren ein!");

        int stueckzahl = stuekzahlscanner.nextInt();
        lieferungObject.setStueckzahl(stueckzahl);
        System.out.println("Die Stueckzahl/-en: " + lieferungObject.getStueckzahl() + " wurde/n eingetragen!");
    }

    void QualitaetPruefen () {

        Scanner qualitaetscanner = new Scanner(System.in);
        Lieferung lieferungObject = new Lieferung();
        System.out.println("Tragen Sie die Qualität der Waren ein!");

        String qualitaet = qualitaetscanner.nextLine();
        lieferungObject.setQualitaet(qualitaet);
        System.out.println("Die Qualitaet: " + lieferungObject.getQualitaet() + "wurde eingetragen!");
    }

    void Annehmen (){ //int warennummer

    }

    void Ablehnen (){ //int warennummer

    }

    void Bezahlen () {

    }
}
