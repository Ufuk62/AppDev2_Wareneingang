package Präsentationsschicht;

import Anwendungsschicht.Lieferschein;
import Anwendungsschicht.Lieferung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Eingaben {
 // Als erstes Einagbe Kundennummer -> Ausgabe Liste der Lieferungen von dem Kunden.
 // Danach Auswahl der Lieferung -> EPK Ablauf fortsetzen
 // (1. Ist Ware in Lieferschein gleich mit Lieferung? -> Qualiaet/Stueckzahl prüfen. Wenn alles Ok. Bezahlen, sonst Teil oder alles Ablehnen)

    private void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Eine der folgenden Zahlen eingeben und Enter drücken: \n0 - Beenden\n1 - Lieferschein\n2 - Stueckzahl\n3 - Qualitaet\n4 - Annehmen\n5 - Ablehnen\n6 - Bezahlen\n");
        String uInput = br.readLine();

        switch (uInput) {
            case "0":
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
                break;
            case "1":
                LieferscheinKorrekt();
                break;
            case "2":
                StueckzahlKorrekt();
                break;
            case "3":
                QualitaetPruefen();
                break;
            case "4":
                Annehmen();
                break;
            case "5":
                Ablehnen();
                break;
            case "6":
                Bezahlen();
                break;
            default:
                System.out.println("Bitte einen Wert von oben eingeben");
                getInput();
                break;
        }
    }



    void LieferscheinKorrekt() {

        Scanner lieferscheinscanner = new Scanner(System.in); //System.in, da der String von der Konsole eingelesen werden soll
        Lieferschein lieferscheinObject = new Lieferschein();
        System.out.println("Tragen Sie die Lieferscheinnummer ein!");
        int lieferscheinnummer = lieferscheinscanner.nextInt();

        //Datenbankabfrage fehlt
        if ((lieferscheinnummer < 25) && (lieferscheinnummer != 0)) {
            lieferscheinObject.setLieferscheinnummer(lieferscheinnummer);
            System.out.println("Die Lieferung mit der Nummer:" + lieferscheinObject.getLieferscheinnummer() + "wurde gefunden!");
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
