package Präsentationsschicht;

import Anwendungsschicht.Lieferschein;
import Anwendungsschicht.Lieferung;

import java.util.Scanner;

public class Eingaben {

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

        void Annehmen ( int warennummer){

        }

        void Ablehnen ( int warennummer){

        }

        void Bezahlen () {

        }

}
