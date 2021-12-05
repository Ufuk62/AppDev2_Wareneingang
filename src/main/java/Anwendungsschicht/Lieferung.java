package Anwendungsschicht;

public class Lieferung {
private String lieferungsnummer; 
private Kunde kunde ; 
private Lieferschein lieferschein;
private Hashtable<Ware,int> waren; //  enthält Stückzahl und Waren 
private Hashtable<Ware,int>  abgelehnteWaren;
private Hashtable<Ware,int> angenommneWaren;
private boolean abgeschlossen ;


public Lieferung ( String lieferungsnummer, Kunde kunde ,Lieferschein lieferschein)
{
this.lieferungsnummer = lieferungsnummer;
this.kunde = kunde;
this.lieferschein = lieferschein;
}

    public Lieferschein getLieferschein()
{
    return this.lieferschein;
}

    public Hashtable<Ware,int> getWaren(int warennummer){
        return this.waren;
    }

    public void wareAnnehmen(Ware ware, int stueckzahl){
    angenommnenWaren.put(ware,stueckzahl)
    }
    public void wareAblehnen(Ware ware, int stueckzahl){
        abgelehnteWaren.put(ware,stueckzahl)
    }



    public abschliessen() {
        this.abgeschlossen = true;

    }

}
