package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;

import com.example.patrykmarchewka.demo.API.Kraje;
import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyDTO;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyZamowieniaDTO;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Zamowienia_ProduktyDTO {

    @JsonProperty("Produkt")
    private ProduktyZamowieniaDTO produktDTO;
    @JsonProperty("Ilosc")
    private Integer ilosc;


    public Zamowienia_ProduktyDTO(Zamowienia_Produkty zamowieniaProdukty){
        Produkty produkt = zamowieniaProdukty.getProdukt();



        //Sprawdzanie czy mozemy dostac 0% vat
        if (zamowieniaProdukty.getZamowienie().getKlienci().getVatID() != null && zamowieniaProdukty.getZamowienie().getAdres().getKraj().equals(Kraje.INNY_KRAJ_W_UNII_EUROPEJSKIEJ)){
            //Posiada VAT ID i jest w Unii europejskiej, kwalifikuje sie do Vat 0%
            produkt.setStawkaVat(null);
            produkt.setNiestandarowyVat(BigDecimal.ZERO);
        }
        else if(zamowieniaProdukty.getZamowienie().getAdres().getKraj().equals(Kraje.INNY_KRAJ_SPOZA_UNII_EUROPEJSKIEJ)){
            //Kraj spoza Unii europejskiej, kwalifikuje sie do Vat 0%
            produkt.setStawkaVat(null);
            produkt.setNiestandarowyVat(BigDecimal.ZERO);
        }
        this.produktDTO = new ProduktyZamowieniaDTO(produkt);
        this.ilosc = zamowieniaProdukty.getIlosc();

    }

    public Integer getIlosc() {
        return ilosc;
    }


    public ProduktyZamowieniaDTO getProduktDTO() {
        return produktDTO;
    }
}
