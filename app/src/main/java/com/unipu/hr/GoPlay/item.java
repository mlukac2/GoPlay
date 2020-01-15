package com.unipu.hr.GoPlay;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class item {

    private String sport;
    private int brisanje;
    private String lokacija;
    private int cijena;
    private int brOsoba;
    private Date datum;
    private int udio;
    private int uplaceno;
    private int brSudionika;
    private String userId;
    private List<String> sudionici = new ArrayList<>();




    @DocumentId
    private String documentId;

    public item() {
    }

    public item(String sport, int brisanje, String lokacija, int cijena, int brOsoba, Date datum, int udio, int uplaceno, int brSudionika, String userId, List<String> sudionici, String documentId) {
        this.sport = sport;
        this.brisanje = brisanje;
        this.lokacija = lokacija;
        this.cijena = cijena;
        this.brOsoba = brOsoba;
        this.datum = datum;
        this.udio = udio;
        this.uplaceno = uplaceno;
        this.brSudionika = brSudionika;
        this.userId = userId;
        this.sudionici = sudionici;
        this.documentId = documentId;
    }

    public int getBrSudionika() {
        return brSudionika;
    }

    public void setBrSudionika(int brSudionika) {
        this.brSudionika = brSudionika;
    }

    public int getBrisanje() {
        return brisanje;
    }

    public void setBrisanje(int brisanje) {
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSudionici() {
        return sudionici;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setSudionici(List<String> sudionici) {
        this.sudionici = sudionici;
    }

    public int getUplaceno() {
        return uplaceno;
    }

    public void setUplaceno(int uplaceno) {
        this.uplaceno = uplaceno;
    }
}