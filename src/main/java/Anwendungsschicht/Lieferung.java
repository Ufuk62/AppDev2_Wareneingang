package Anwendungsschicht;

public class Lieferung {

    private int eingabeStueckzahl;
    private String eingabeQualitaet;


    public void setStueckzahl(int stueckzahl){
       eingabeStueckzahl = stueckzahl;
    }
    public int getStueckzahl(){
        return eingabeStueckzahl;
    }

    public void setQualitaet(String qualitaet){
        eingabeQualitaet = qualitaet;
    }

    public String getQualitaet(){
        return eingabeQualitaet;
    }

    public printLieferung();

    public getLieferschein(int lieferscheinnummer);

    public getWare(int warennummer);

    public wareAnnehmen(int warennummer){

    }
    public wareAblehnen(int warennummer){

    }

    public printAngenommen(){

    }

    public printAbgelehnt(){

    }

    public abschliessen(bool bestaetigt) {

    }

}
