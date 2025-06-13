package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.StawkiVAT;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProduktyZamowieniaDTO {
    @JsonProperty("Unikalny identyfikator ID")
    private Long ID;
    @JsonProperty("Nazwa")
    private String nazwa;
    @JsonProperty("Cena netto")
    private BigDecimal cenaNetto;
    @JsonProperty("Cena brutto")
    private BigDecimal cenaBrutto;
    @JsonProperty("Podatek VAT")
    private BigDecimal podatek;
    @JsonProperty("Standardowa stawka VAT")
    private StawkiVAT stawkaVat;
    @JsonProperty("Niestandardowa stawka VAT")
    private BigDecimal niestandarowyVat;

    public ProduktyZamowieniaDTO(Produkty produkt){
        this.ID = produkt.getID();
        this.nazwa = produkt.getNazwa();
        this.cenaNetto = produkt.getCena();
        this.stawkaVat = produkt.getStawkaVat();
        this.niestandarowyVat = produkt.getNiestandarowyVat();
        if (niestandarowyVat == null){
            this.podatek = cenaNetto.multiply(stawkaVat.getProcent().divide(new BigDecimal(100),6, RoundingMode.HALF_UP));
            this.cenaBrutto = (cenaNetto.multiply((BigDecimal.ONE.add(stawkaVat.getProcent().divide(new BigDecimal(100),6, RoundingMode.HALF_UP))))).setScale(2,RoundingMode.HALF_UP);
        }
        else{
            this.podatek = cenaNetto.multiply(niestandarowyVat.divide(new BigDecimal(100),6,RoundingMode.HALF_UP));
            this.cenaBrutto = (cenaNetto.multiply((new BigDecimal(1).add(niestandarowyVat.divide(new BigDecimal(100),6, RoundingMode.HALF_UP))))).setScale(2,RoundingMode.HALF_UP);
        }
        if (!checkPrices(cenaNetto,podatek,cenaBrutto)){
            throw new RuntimeException(String.format("Problem z obliczaniem ceny: %s + %s != %s",cenaNetto,podatek,cenaBrutto));
        }
        this.cenaNetto = this.cenaNetto.setScale(2,RoundingMode.HALF_UP);
        this.podatek = this.podatek.setScale(2,RoundingMode.HALF_UP);
        this.cenaBrutto = this.cenaBrutto.setScale(2,RoundingMode.HALF_UP);

    }

    private static boolean checkPrices(BigDecimal cena, BigDecimal podatek, BigDecimal cenaBrutto){
        return cena.add(podatek).setScale(2,RoundingMode.HALF_UP).compareTo(cenaBrutto) == 0;
    }

    public ProduktyZamowieniaDTO(){};


    public Long getID() {
        return ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public BigDecimal getNiestandarowyVat() {
        return niestandarowyVat;
    }

    public StawkiVAT getStawkaVat() {
        return stawkaVat;
    }

    public BigDecimal getCenaBrutto() {
        return cenaBrutto;
    }

    public BigDecimal getCenaNetto() {
        return cenaNetto;
    }

    public BigDecimal getPodatek() {
        return podatek;
    }
}
