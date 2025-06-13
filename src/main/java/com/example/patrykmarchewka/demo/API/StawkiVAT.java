package com.example.patrykmarchewka.demo.API;

import java.math.BigDecimal;

//Jezeli kupujacy spoza EU -> Vat 0%
//Jezeli kupujacy w EU ale ma VAT ID -> Vat 0%
public enum StawkiVAT {
    ZERO(new BigDecimal("0")),
    PIEC(new BigDecimal("5")),
    OSIEM(new BigDecimal("8")),
    DWADZIESCIA_TRZY(new BigDecimal("23"));

    private final BigDecimal procent;

    StawkiVAT(BigDecimal procent) {
        this.procent = procent;
    }

    public BigDecimal getProcent() {
        return procent;
    }
}
