package com.example.patrykmarchewka.demo.API.Adresy;

import com.example.patrykmarchewka.demo.API.Kraje;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdresyDTO {

    @JsonProperty("Unikalny identyfikator ID")
    private Long ID;
    @JsonProperty("Kraj")
    private Kraje kraj;
    @JsonProperty("Miasto")
    private String miasto;
    @JsonProperty("Ulica")
    private String ulica;
    @JsonProperty("Kod pocztowy")
    private String kodPocztowy;
    @JsonProperty("Numer domu")
    private String numerDomu;
    @JsonProperty("Numer mieszkania")
    private String numerMieszkania;

    public AdresyDTO(Adresy adres){
        this.ID = adres.getID();
        this.kraj = adres.getKraj();
        this.miasto = adres.getMiasto();
        this.ulica = adres.getUlica();
        this.kodPocztowy = adres.getKodPocztowy();
        this.numerDomu = adres.getNumerDomu();
        this.numerMieszkania = adres.getNumerMieszkania();
    }

    public AdresyDTO(){};

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Kraje getKraj() {
        return kraj;
    }

    public void setKraj(Kraje kraj) {
        this.kraj = kraj;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }
}
