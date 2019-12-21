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
    int uplaceno;
    String userId;
    List<String> sudionici = new ArrayList<>();

    public item() {
    }

    public item(String sport, String lokacija, int cijena, int brOsoba, Date datum, int udio, int uplaceno, String userId, List<String> sudionici) {
        this.sport = sport;
        this.lokacija = lokacija;
        this.cijena = cijena;
        this.brOsoba = brOsoba;
        this.datum = datum;
        this.udio = udio;
        this.uplaceno = uplaceno;
        this.userId = userId;
        this.sudionici = sudionici;
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

    public int getUplaceno() {
        return uplaceno;
    }

    public void setUplaceno(int uplaceno) {
        this.uplaceno = uplaceno;
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