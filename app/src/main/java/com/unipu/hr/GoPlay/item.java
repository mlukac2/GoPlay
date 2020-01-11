package com.unipu.hr.GoPlay;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class item {

    String sport;
    String lokacija;
    int cijena;
    int brOsoba;
    Date datum;
    int udio;
    int uplacneno;
    String userId;
    List<String> sudionici = new ArrayList<>();
    List<String> brisanje = new ArrayList<>();

    public item() {
    }

    public item(String sport, String lokacija, int cijena, int brOsoba, Date datum, int udio, int uplacneno, String userId, List<String> sudionici, List<String> brisanje) {
        this.sport = sport;
        this.lokacija = lokacija;
        this.cijena = cijena;
        this.brOsoba = brOsoba;
        this.datum = datum;
        this.udio = udio;
        this.uplacneno = uplacneno;
        this.userId = userId;
        this.sudionici = sudionici;
        this.brisanje = brisanje;
    }

    public List<String> getBrisanje() {
        return brisanje;
    }

    public void setBrisanje(List<String> brisanje) {
        this.brisanje = brisanje;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getBrOsoba() {
        return brOsoba;
    }

    public void setBrOsoba(int brOsoba) {
        this.brOsoba = brOsoba;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getUdio() {
        return udio;
    }

    public void setUdio(int udio) {
        this.udio = udio;
    }

    public int getUplacneno() {
        return uplacneno;
    }

    public void setUplaceno(int uplaceno) {
        this.uplacneno = uplaceno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSudionici() {
        return sudionici;
    }

    public void setSudionici(List<String> sudionici) {
        this.sudionici = sudionici;
    }
}