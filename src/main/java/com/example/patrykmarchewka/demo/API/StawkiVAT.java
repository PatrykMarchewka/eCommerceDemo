package com.example.patrykmarchewka.demo.API;

import java.math.BigDecimal;

//Jezeli kupujacy spoza EU -> Vat 0%
//Jezeli kupujacy w EU ale ma VAT ID -> Vat 0%
public enum StawkiVAT {
    ZERO(new BigDecimal("0"), "stawka VAT 0%"),
    PIEC(new BigDecimal("5"), "stawka obnizona VAT 5%"),
    OSIEM(new BigDecimal("8"), "stawka obnizona VAT 8%"),
    DWADZIESCIA_TRZY(new BigDecimal("23"), "stawka podstawowa VAT 23%");

    private final BigDecimal procent;
    private final String opis;

    StawkiVAT(BigDecimal procent, String opis){
        this.procent = procent;
        this.opis = opis;
    }

    public BigDecimal getProcent() {
        return procent;
    }

    public String getOpis() {
        return opis;
    }
}
