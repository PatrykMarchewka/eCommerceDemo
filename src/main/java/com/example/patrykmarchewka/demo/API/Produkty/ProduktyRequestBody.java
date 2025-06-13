package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.OnCreate;
import com.example.patrykmarchewka.demo.API.StawkiVAT;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProduktyRequestBody {
    @NotBlank(groups = OnCreate.class)
    private String nazwa;
    @Positive
    private BigDecimal cena;
    private StawkiVAT stawkaVat;
    private BigDecimal niestandarowyVat;
    @NotNull(groups = OnCreate.class)
    private Boolean czyDostepny;
    @PositiveOrZero
    private Integer dostepnaIlosc;

    public ProduktyRequestBody(){}

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public StawkiVAT getStawkaVat() {
        return stawkaVat;
    }

    public void setStawkaVat(StawkiVAT stawkaVat) {
        this.stawkaVat = stawkaVat;
    }

    public BigDecimal getNiestandarowyVat() {
        return niestandarowyVat;
    }

    public void setNiestandarowyVat(BigDecimal niestandarowyVat) {
        this.niestandarowyVat = niestandarowyVat;
    }

    public Boolean getCzyDostepny() {
        return czyDostepny;
    }

    public void setCzyDostepny(Boolean czyDostepny) {
        this.czyDostepny = czyDostepny;
    }

    public Integer getDostepnaIlosc() {
        return dostepnaIlosc;
    }

    public void setDostepnaIlosc(Integer dostepnaIlosc) {
        this.dostepnaIlosc = dostepnaIlosc;
    }
}
