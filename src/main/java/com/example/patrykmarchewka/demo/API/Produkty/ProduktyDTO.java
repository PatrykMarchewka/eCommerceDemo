package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.StawkiVAT;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProduktyDTO {
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
    @JsonProperty("Czy produkt jest w sprzedazy")
    private Boolean czyDostepny;
    @JsonProperty("Dostepna ilosc w magazynie")
    private Integer dostepnaIlosc;

    public ProduktyDTO(Produkty produkt, Uzytkownicy uzytkownik){
        this.ID = produkt.getID();
        this.nazwa = produkt.getNazwa();
        this.cenaNetto = produkt.getCena();
        this.stawkaVat = produkt.getStawkaVat();
        this.niestandarowyVat = produkt.getNiestandarowyVat();
        if (niestandarowyVat == null){
            this.podatek = cenaNetto.multiply(stawkaVat.getProcent().divide(new BigDecimal(100),6,RoundingMode.HALF_UP));
            this.cenaBrutto = (cenaNetto.multiply((BigDecimal.ONE.add(stawkaVat.getProcent().divide(new BigDecimal(100),6, RoundingMode.HALF_UP))))).setScale(2,RoundingMode.HALF_UP);
        }
        else{
            this.podatek = cenaNetto.multiply(niestandarowyVat.divide(new BigDecimal(100),6,RoundingMode.HALF_UP));
            this.cenaBrutto = (cenaNetto.multiply((new BigDecimal(1).add(niestandarowyVat.divide(new BigDecimal(100),6, RoundingMode.HALF_UP))))).setScale(2,RoundingMode.HALF_UP);
        }
        //cenaBrutto = cena * (1 + (vat/100), zaokrąglane w góre po 6 miejsach po przecinku przy dzielenium, koncowy wynik zaokraglany w gore po 2 miejsach po przecinku
        //Zaokraglanie w gore po 6 miejscach aby dzialalo z nawet najdziwniejszym VATem takim jak 17.935% lub 8.875%


        //Niepowinno nigdy sie wydarzyc ale dodane dla pewnosci
        if (!checkPrices(cenaNetto,podatek,cenaBrutto)){
            throw new RuntimeException(String.format("Problem z obliczaniem ceny: %s + %s != %s",cenaNetto,podatek,cenaBrutto));
        }


        this.czyDostepny = produkt.getCzyDostepny();
        this.dostepnaIlosc = calculateItemVisibility(uzytkownik, produkt);

    }

    //Jezeli produkt jest niedostepny nie pokazujemy ilosci na stanie dla klientow, moze byc uzyte w przypadku gdy mamy kilka sztuk w magazynie ale nie chcemy ich sprzedac
    private Integer calculateItemVisibility(Uzytkownicy uzytkownik, Produkty produkt){
        if (Boolean.TRUE.equals(this.czyDostepny) || uzytkownik.getRola().equals(RoleUzytkownikow.PRACOWNIK)){
            return produkt.getDostepnaIlosc();
        }
        return null;
    }

    private static boolean checkPrices(BigDecimal cena, BigDecimal podatek, BigDecimal cenaBrutto){
        return cena.add(podatek).setScale(2,RoundingMode.HALF_UP).compareTo(cenaBrutto) == 0;
    }

    public ProduktyDTO(){};


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

    public Boolean getCzyDostepny() {
        return czyDostepny;
    }

    public Integer getDostepnaIlosc() {
        return dostepnaIlosc;
    }
}
