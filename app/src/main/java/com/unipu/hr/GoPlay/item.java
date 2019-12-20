package com.unipu.hr.GoPlay;

import android.graphics.Bitmap;

public class item {

    String datum;
    String sport;
    String lokacija;
    String cijena;
    String ime;
    String slika;


    public item() {
    }

    public item(String datum, String sport, String lokacija, String cijena, String ime, String slika) {
        this.datum = datum;
        this.sport = sport;
        this.lokacija = lokacija;
        this.cijena = cijena;
        this.ime = ime;
        this.slika = slika;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getDatum() {
        return datum;
    }

    public String getSport() {
        return sport;
    }

    public String getLokacija() {
        return lokacija;
    }

    public String getCijena() {
        return cijena;
    }

    public String getIme() {
        return ime;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
