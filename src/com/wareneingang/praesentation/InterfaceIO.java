package com.wareneingang.praesentation;

import com.wareneingang.daten.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

/**
 * Liefert den Einstieg und alle internen vorgänge, um die Anwendung in der Konsole nutzen zu können.
 */
public class InterfaceIO {
    // Stellt eine Verbindung zur Serverseite der Anwendung her und bietet die Datenbankverbindung als Einstieg bereit.
    private Datenbank db = (Datenbank) Naming.lookup("rmi://localhost:1099/wareneingang_server");

    /**
     * Der Einstiegspunkt für die Verwendung der Anwendung.
     * @throws SQLException
     * @throws IOException
     * @throws NotBoundException
     */
    public InterfaceIO() throws SQLException, IOException, NotBoundException {
        ReadKundennummer();
    }

    /**
     * Fragt in der Konsole die Kundennummer ab und setzt den Ablauf mit dem damit verbundenen Kunde-Objekt fort.
     */
    private void ReadKundennummer() {
        // Wird so lange wiederholt, bis ein Kunde ausgewählt wurde.
        while (true) {
            System.out.print("Geben Sie Ihre Kundennummer ein: ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            try {
                String input = reader.readLine();
                int kNummer = Integer.parseInt(input);
                Kunde kunde = db.getKunde(kNummer);
                if (kunde != null) {
                    System.out.println("Hallo " + kunde.getName());
                    LieferungAuswahl(kunde);
                    // Speichert den Kunden mit den Lieferungen.
                    db.save(kunde);
                    // Die Anwendung wird hiernach beendet.
                    return;
                } else {
                    System.out.println("Diese Kundennummer ist nicht vergeben.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Die Eingabe muss eine Nummer sein.");
            }
        }
    }

    /**
     * Gibt eine Liste der zugeordneten Lieferungen aus und nutzt die Konsole, damit eine dieser ausgewählt werde kann.
     * @param kunde Das Kunde-Objekt, dessen Lieferungen bearbeitet wird.
     * @throws IOException
     * @throws SQLException
     */
    private void LieferungAuswahl(Kunde kunde) throws IOException, SQLException {
        Lieferung l = kunde.getLieferungen().get(1);
        Enumeration<Lieferung> enumeration = kunde.getLieferungen().elements();
        Vector<Integer> abgeschlossene = new Vector<>();

        // Die Ausgabe der Liste von Lieferungen, die noch nicht abgeschlossen sind.
        System.out.println(" Nr.\t| Eingang am");
        System.out.println("--------+-----------");
        while (enumeration.hasMoreElements()) {
            Lieferung lieferung = enumeration.nextElement();
            if (!lieferung.isAbgeschlossen()) {
                System.out.println(" " + lieferung.getLieferungsnummer() +
                        "\t\t| " + lieferung.getEingangsdatum().toString()
                );
            } else {
                abgeschlossene.add(lieferung.getLieferungsnummer());
            }
        }

        // Wird so lange wiederholt, bis für den Kunden eine Lieferung ausgewählt wurde.
        while (true) {
            System.out.print("Geben Sie eine Liefernummer ein: ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            try {
                int lNummer = Integer.parseInt(input);
                Lieferung lieferung = kunde.getLieferung(lNummer);

                // Überprüft, ob eine Lieferung mit der Liefernummer für diesen Kunden existiert.
                // Überprüft gleichzeitig, ob die Lieferung bereits bearbeitet wurde.
                if (lieferung != null && !abgeschlossene.contains(lieferung.getLieferungsnummer())) {
                    AusgabeLieferscheinUndLieferungUndAbweichungEingeben(lieferung);
                    // Geht zurück zum Schritt "ReadKundennummer".
                    return;
                } else {
                    System.out.println("Bitte wählen Sie eine Nummer aus der Liste.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Die Eingabe muss eine Nummer sein.");
            }
        }
    }

    /**
     * Gibt eine Gegenüberstellung der Waren in der Lieferung und auf dem Lieferschein aus.
     * Führt den Anwender durch die Auswahl, ob die Stückzahl korrekt ist.
     * @param lieferung
     * @throws IOException
     */
    private void AusgabeLieferscheinUndLieferungUndAbweichungEingeben(Lieferung lieferung) throws IOException {
        Lieferschein lieferschein = lieferung.getLieferschein();

        // Gibt die eine Überschrift für die Gegenüberstellung der Waren aus.
        Set<Ware> waren = lieferung.getWaren().keySet();
        Iterator<Ware> iterator = waren.iterator();
        System.out.println("\t\t\t\t\t\tLieferung\t\t\t\t\t\t|\t\t\t\t\t\tLieferschein\t\t\t\t\t\t");
        System.out.println("--------------------------------------------------------|" +
                "--------------------------------------------------------"
        );
        System.out.println(" Waren-Nr.\t| Bezeichnung\t\t\t\t| Stueckzahl\t|" +
                " Waren-Nr.\t| Bezeichnung\t\t\t\t| Stueckzahl\t"
        );
        System.out.println("------------+---------------------------+---------------|" +
                "-----------+---------------------------+----------------"
        );

        ArrayList<Ware> lieferscheinWaren = lieferschein.getWaren();

        // Eine Liste aller Waren, auch diese die nur in der Lieferung oder nur im Lieferschein enthalten sind.
        Vector<Ware> alleWaren = new Vector<>();

        // Geht durch alle Lieferungswaren und gibt sie in der Gegenüberstellung mit den zugehörigen Lieferscheinwaren
        //  inklusive Ihrer Stückzahl aus.
        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            // Fügt die Ware zu der Sammlung aller Waren hinzu.
            alleWaren.add(ware);
            Ware lieferscheinWare = lieferschein.getWare(ware.getWarennummer());

            System.out.print(" " + ware.getWarennummer() + "\t\t\t| " + ware.getWarenbezeichnung() +
                    " ".repeat(25-ware.getWarenbezeichnung().length()) + "\t| " +
                    lieferung.getWaren().get(ware) + " \t\t\t|"
            );
            if (lieferscheinWare != null) {
                int stueckzahl = 0;
                try {
                    stueckzahl = lieferschein.getStueckzahl(lieferscheinWare.getWarennummer());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(" " + lieferscheinWare.getWarennummer() +
                        " \t\t| " + lieferscheinWare.getWarenbezeichnung() +
                        " ".repeat(25-lieferscheinWare.getWarenbezeichnung().length()) + "\t| " +
                        stueckzahl
                );
                // Entfernt die lieferscheinwaren aus dem Lieferscheinobjekt,
                //  damit diese im nächsten Schritt nicht erneut ausgegeben werden.
                lieferscheinWaren.remove(lieferscheinWare);
            } else {
                System.out.println("\t".repeat(3) + "\u001B[31m Das Element wurde nicht gefunden \u001B[0m");
            }
        }

        // Geht durch alle noch vorhandenen Lieferscheinwaren durch, und gibt sie in der Gegenüberstellung aus.
        iterator = lieferscheinWaren.iterator();
        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            alleWaren.add(ware);
            // Da diese Ware nicht zu einer Lieferungsware gehört, wird an deren Stelle ein hinweis dazu ausgegeben.
            System.out.print("\t".repeat(3) + "\u001B[31m Das Element wurde nicht gefunden \u001B[0m" + "\t".repeat(3) + "|");

            System.out.println(" " + ware.getWarennummer() +
                    " \t\t| " + ware.getWarenbezeichnung() +
                    " ".repeat(25-ware.getWarenbezeichnung().length()) + "\t| " +
                    lieferschein.getStueckzahl(ware.getWarennummer())
            );
            lieferscheinWaren.remove(ware);
        }

        // Wiederholt die Frage nach der Abweichung bis eine Eingabe akzeptiert wird.
        while (true) {
            System.out.print("Ist eine Abweichung in der Liefermenge vorhanden? (j/N) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            if (input.equalsIgnoreCase("j")) {
                // Wiederholt die Frage nach einer Warennummer, bis eine gültige eingegeben wurde.
                while (true) {
                    System.out.print("Geben Sie die Warennummer ein: ");

                    reader = new BufferedReader(
                            new InputStreamReader(System.in)
                    );

                    input = reader.readLine();

                    try {
                        int warennummer = Integer.parseInt(input);

                        Ware ware = null;

                        for (Ware checkWare : alleWaren) {
                            if (warennummer == checkWare.getWarennummer()) {
                                ware = checkWare;
                            }
                        }

                        if (ware != null) {
                            System.out.println("Ausgewaehlte Ware: \"" + ware.getWarenbezeichnung() + "\"");
                            // Überprüft, ob der Anwender einen Teil der Ware annimmt.
                            // Wenn nicht, wird sie aus der Sammlung aller Waren entfernt.
                            if (!StueckzahlPruefung(lieferung, lieferschein, ware)) {
                                alleWaren.remove(ware);
                            }
                            // Wiederholt die Frage nach weiteren abweichungen bis eine Eingabe angenommen wird.
                            while (true) {
                                System.out.print("Sind noch weitere Abweichungen vorhanden? (j/N) ");

                                reader = new BufferedReader(
                                        new InputStreamReader(System.in)
                                );

                                input = reader.readLine();

                                if (input.equalsIgnoreCase("j")) {
                                    // Bricht aus der Schleife für weitere Abweichungen
                                    // und beginnt am Anfang der Schleife, die nach einer Warennummer fragt erneut.
                                    break;
                                } else if (input.equalsIgnoreCase("n") || input.length() == 0) {
                                    // Wenn keine weiteren mehr angegeben werden,
                                    // dann wird die Überprüfung der Qualität gestartet.
                                    QualitaetsCheck(lieferung, lieferschein, alleWaren);
                                    // Geht zurück zum Schritt "LieferungAuswahl"
                                    return;
                                } else {
                                    System.out.println("Eingabe konnte nicht interpretiert werden.");
                                }
                            }
                        } else {
                            System.out.println("Bitte geben Sie eine vorhandene Warennummer ein.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Das ist keine Warennummer.");
                    }
                }
            } else if (input.equalsIgnoreCase("n") || input.length() == 0) {
                // Wenn keine Abweichungen angegeben werden, wird die Überprüfung der Qualität gestartet.
                QualitaetsCheck(lieferung, lieferschein, alleWaren);
                // Geht zurück zum Schritt "LieferungAuswahl"
                return;
            } else {
                System.out.println("Eingabe konnte nicht interpretiert werden.");
            }
        }
    }

    /**
     * Führt den Anwender durch die Überprüfung der Qualität der Ware.
     * @param lieferung Die Lieferung, die aktuell bearbeitet wird.
     * @param lieferschein Der Lieferschein der aktuell bearbeiteten Lieferung.
     * @param waren Die Sammlung der bisher akzeptierten Waren.
     * @throws RemoteException
     */
    private void QualitaetsCheck(Lieferung lieferung, Lieferschein lieferschein, Vector<Ware> waren) throws RemoteException {
        System.out.println("Bitte ueberpruefen Sie die folgenden Waren auf Ihre Qualitaet.");
        Iterator<Ware> iterator = waren.iterator();
        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            // Wiederholt die Frage nach der Qualität der Ware, bis die Eingabe interpretiert werden konnte.
            while (true) {
                System.out.print(ware.getWarenbezeichnung() + " Qualitaet OK? (J/n) ");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in)
                );

                try {
                    String input = reader.readLine();

                    if ((input.equalsIgnoreCase("n"))) {
                        // Wenn die Qualität nicht angenommen wurde, wird überprüft,
                        // ob ein Teil der Ware angenommen wird.
                        StueckzahlPruefung(lieferung, lieferschein, ware);
                        break;
                    } else if (input.equalsIgnoreCase("j") || input.length() == 0) {
                        // Ruft die Methode auf, um die gesamte Ware den angenommenen Waren hinzuzufügen.
                        lieferung.setQualitaet(ware.getWarennummer(), true);
                        break;
                    } else {
                        System.out.println("Die Eingabe konnte nicht interpretiert werden.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Da die Qualiätsüberprüfung der letzte Schritt vor der Annahme ist, wird die Annahme hier gestartet.
        PreisUndAnnahme(lieferung);
        // Geht zurück zum Schritt "AusgabeLieferscheinUndLieferungUndAbweichungEingeben"
        return;
    }

    /**
     * Führt den Anwender durch die Auswahl, ob er einen Teil der Ware annimmt.
     * @param lieferung Die Lieferung, die aktuell bearbeitet wird.
     * @param lieferschein Der Lieferschein der aktuell bearbeiteten Lieferung.
     * @param ware Die aktuell bearbeitete Ware.
     * @return Ob ein Teil angenommen wurde oder nicht.
     * @throws IOException
     */
    public static boolean StueckzahlPruefung(Lieferung lieferung, Lieferschein lieferschein, Ware ware) throws IOException {
        // Wiederholt die Frage nach der Teilannahme so lange, bis die Eingabe interpretiert werden konnte.
        while (true) {
            System.out.print("Moechten Sie einen Teil der Ware annehmen? (j/N) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            if (input.equalsIgnoreCase("j")) {
                // Wiederholt die Frage nach der angenommenen Stückzahl so lange,
                // bis eine Zahl unter der vorhandenen Stückzahl eingegeben wurde.
                while (true) {
                    System.out.print("Bitte geben Sie die Menge ein, die Sie annehmen: ");

                    reader = new BufferedReader(
                            new InputStreamReader(System.in)
                    );

                    input = reader.readLine();

                    try {
                        // Die anzahl der angenommenen Ware.
                        int anzahl = Integer.parseInt(input);
                        if (lieferung.getWaren().get(ware) != null) {
                            // Berechnet die Anzahl der abgelehnten Ware.
                            int abgelehnt = lieferschein.getStueckzahl(ware.getWarennummer()) - anzahl;
                            // Überprüft, ob eine negative Zahl durch die berechnung entsteht.
                            if (abgelehnt >= 0 && anzahl <= lieferung.getWaren().get(ware)) {
                                // Fügt die angenommenen Waren hinzu.
                                lieferung.addAngenommeneWare(ware.getWarennummer(), anzahl);
                                // Überprüft, ob eine oder mehr Waren abgeleht wurden.
                                if (abgelehnt != 0) {
                                    // Fügt die abgelehnten Waren hinzu.
                                    lieferung.addAbgelehnteWare(ware.getWarennummer(), abgelehnt);
                                }
                                return true;
                            } else {
                                System.out.println("Sie koennen nicht mehr Stuecke annehmen als vorhanden sind");
                            }
                        } else {
                            System.out.println("Diese Ware ist nicht in der Lieferung vorhanden");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Die Eingabe konnte nicht interpretiert werden");
                    }
                }
            } else if (input.equalsIgnoreCase("n") || input.length() == 0) {
                if (lieferung.getWaren().get(ware) != null) {
                    // Fügt die abgelehnten Waren hinzu, wenn keine Ware angenommen wird.
                    lieferung.addAbgelehnteWare(ware.getWarennummer(), lieferung.getWaren().get(ware));
                }
                return false;
            } else {
                System.out.println("Eingabe konnte nicht interpretiert werden.");
            }
        }
    }

    /**
     * Gibt den Gesamtpreis der angenommenen Ware aus und fragt ab, ob eine Bezahlung veranlasst werden soll.
     * @param lieferung Die aktuell bearbeitete Lieferung.
     * @throws RemoteException
     */
    public void PreisUndAnnahme(Lieferung lieferung) throws RemoteException {
        System.out.println("Ihr Gesamtpreis ist: " + lieferung.getGesamtpreis() + " €");

        // Wiederholt die Frage nach der Zahlung, bis die Eingabe interpretiert werden konnte.
        while (true) {
            System.out.print("Möchten Sie die Zahlung veranlassen? (j/N) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            try {
                String input = reader.readLine();

                if ((input.equalsIgnoreCase("j"))) {
                    lieferung.abschliessen();
                    return;
                } else if (input.equalsIgnoreCase("n") || input.length() == 0) {
                    return;
                } else {
                    System.out.println("Die Eingabe konnte nicht interpretiert werden.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

