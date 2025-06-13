package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UzytkownicyZamowienieDTO {
    @JsonProperty("Unikalny identyfikator ID")
    private Long ID;
    @JsonProperty("Kod Vat ID")
    private String vatID;
    @JsonProperty("Imie")
    private String imie;
    @JsonProperty("Nazwisko")
    private String nazwisko;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Numer telefonu")
    private String telefon;

    public UzytkownicyZamowienieDTO(Uzytkownicy uzytkownik){
        this.ID = uzytkownik.getID();
        this.vatID = uzytkownik.getVatID();
        this.imie = uzytkownik.getImie();
        this.nazwisko = uzytkownik.getNazwisko();
        this.email = uzytkownik.getEmail();
        this.telefon = uzytkownik.getTelefon();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getVatID() {
        return vatID;
    }

    public void setVatID(String vatID) {
        this.vatID = vatID;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
