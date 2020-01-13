package com.unipu.hr.GoPlay;

public class Item_sudionici {
    private String ime;
    private Boolean brisanje;

    public Item_sudionici() {}
    public Item_sudionici(String ime, Boolean brisanje) {
        this.ime = ime;
        this.brisanje = brisanje;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Boolean getBrisanje() {
        return brisanje;
    }

    public void setBrisanje(Boolean brisanje) {
        this.brisanje = brisanje;
    }
}
