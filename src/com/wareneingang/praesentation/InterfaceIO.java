package com.wareneingang.praesentation;

import com.wareneingang.daten.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class InterfaceIO {
    Datenbank db;

    public InterfaceIO() throws SQLException, IOException {
        db = new Datenbank();
        ReadKundennummer();
    }

    private void ReadKundennummer() {
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
                    LieferungAuswahl(kunde);
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

    private void LieferungAuswahl(Kunde kunde) throws IOException, SQLException {
        Enumeration<Lieferung> enumeration = kunde.getLieferungen().elements();

        System.out.println(" Nr.\t| Eingang am");
        System.out.println("--------+-----------");
        while (enumeration.hasMoreElements()) {
            Lieferung lieferung = enumeration.nextElement();
            System.out.println(" " + lieferung.getLieferungsnummer() + "\t\t| " + lieferung.getEingangsdatum().toString());
        }

        while (true) {
            System.out.print("Geben Sie eine Liefernummer ein: ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            try {
                int lNummer = Integer.parseInt(input);
                Lieferung lieferung = kunde.getLieferung(lNummer);

                if (lieferung != null) {
                    AusgabeLieferscheinUndLieferungUndAbweichungEingeben(lieferung);
                } else {
                    System.out.println("Bitte wählen Sie eine Nummer aus der Liste.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Die Eingabe muss eine Nummer sein.");
            }
        }
    }

    private void AusgabeLieferscheinUndLieferungUndAbweichungEingeben(Lieferung lieferung) throws IOException, SQLException {
        Lieferschein lieferschein = lieferung.getLieferschein();

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

        Set<Ware> lieferscheinWaren = lieferschein.getWaren();

        Vector<Ware> alleWaren = new Vector<>();

        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            alleWaren.add(ware);
            Ware lieferscheinWare = lieferschein.getWare(ware.getWarennummer());

            System.out.print(" " + ware.getWarennummer() + "\t\t\t| " + ware.getWarenbezeichnung() +
                    " ".repeat(25-ware.getWarenbezeichnung().length()) + "\t| " +
                    lieferung.getWaren().get(ware) + " \t\t\t|"
            );
            if (lieferscheinWare != null) {
                System.out.println(" " + lieferscheinWare.getWarennummer() +
                        " \t\t| " + lieferscheinWare.getWarenbezeichnung() +
                        " ".repeat(25-lieferscheinWare.getWarenbezeichnung().length()) + "\t| " +
                        lieferschein.getStueckzahl(lieferscheinWare)
                );
                lieferscheinWaren.remove(lieferscheinWare);
            } else {
                System.out.println("\t".repeat(3) + "\u001B[31m Das Element wurde nicht gefunden \u001B[0m");
            }
        }

        iterator = lieferscheinWaren.iterator();
        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            alleWaren.add(ware);
            System.out.print("\t".repeat(3) + "\u001B[31m Das Element wurde nicht gefunden \u001B[0m" + "\t".repeat(3) + "|");


            System.out.println(" " + ware.getWarennummer() +
                    " \t\t| " + ware.getWarenbezeichnung() +
                    " ".repeat(25-ware.getWarenbezeichnung().length()) + "\t| " +
                    lieferschein.getStueckzahl(ware)
            );
            lieferscheinWaren.remove(ware);
        }
// ---------------------------------------------------------------------------------------------------------------------
        while (true) {
            System.out.print("Ist eine Abweichung in der Liefermenge vorhanden? (j/N) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            if (input.equalsIgnoreCase("j")) {
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
                            if (StueckzahlPruefung(lieferung, ware, alleWaren)) {
                                alleWaren.remove(ware);
                            }
                            while (true) {
                                System.out.print("Sind noch weitere Abweichungen vorhanden? (j/N) ");

                                reader = new BufferedReader(
                                        new InputStreamReader(System.in)
                                );

                                input = reader.readLine();

                                if (input.equalsIgnoreCase("j")) {
                                    break;
                                } else if (input.equalsIgnoreCase("n") || input.length() == 0) {
                                    QualitaetsCheck(lieferung, alleWaren);
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
                QualitaetsCheck(lieferung, alleWaren);
                return;
            } else {
                System.out.println("Eingabe konnte nicht interpretiert werden.");
            }
        }
    }

    static private void QualitaetsCheck(Lieferung lieferung, Vector<Ware> waren) {
        System.out.println("Bitte ueberpruefen Sie die folgenden Waren auf Ihre Qualitaet.");
        Iterator<Ware> iterator = waren.iterator();
        while (iterator.hasNext()) {
            Ware ware = iterator.next();
            while (true) {
                System.out.print(ware.getWarenbezeichnung() + " Qualitaet OK? (J/n) ");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in)
                );

                try {
                    String input = reader.readLine();

                    if ((input.equalsIgnoreCase("n"))) {
                        lieferung.setQualitaet(ware, false);
                        StueckzahlPruefung(lieferung, ware, waren);
                        break;
                    } else if (input.equalsIgnoreCase("j") || input.length() == 0) {
                        lieferung.setQualitaet(ware, true);
                        break;
                    } else {
                        System.out.println("Die Eingabe konnte nicht interpretiert werden.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static boolean StueckzahlPruefung(Lieferung lieferung, Ware ware, Vector<Ware> alleWaren) throws IOException {
        while (true) {
            System.out.print("Moechten Sie einen Teil der Ware annehmen? (j/N) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input = reader.readLine();

            if (input.equalsIgnoreCase("j")) {
                while (true) {
                    System.out.print("Bitte geben Sie die Menge ein, die Sie annehmen: ");

                    reader = new BufferedReader(
                            new InputStreamReader(System.in)
                    );

                    input = reader.readLine();

                    try {
                        int anzahl = Integer.parseInt(input);
                        if (lieferung.getWaren().get(ware) != null) {
                            int abgelehnt = lieferung.getWaren().get(ware) - anzahl;
                            if (abgelehnt >= 0) {
                                lieferung.addAngenommeneWare(ware, anzahl);
                                if (abgelehnt != 0) {
                                    lieferung.addAbgelehnteWare(ware, abgelehnt);
                                }
                                return false;
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
                    lieferung.addAbgelehnteWare(ware, lieferung.getWaren().get(ware));
                }
                return true;
            } else {
                System.out.println("Eingabe konnte nicht interpretiert werden.");
            }
        }
    }
}

