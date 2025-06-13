package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Adresy.AdresyDTO;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyDTO;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyZamowieniaDTO;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyDTO;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyZamowienieDTO;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class ZamowieniaDTO {
    private Long ID;
    @JsonProperty("Uzytkownik")
    private UzytkownicyZamowienieDTO uzytkownikDTO;
    @JsonProperty("Adres do dostarczenia")
    private AdresyDTO adresDTO;
    @JsonProperty("Lista produktow")
    private List<Zamowienia_ProduktyDTO> produktyDTOList;
    @JsonProperty("Data zamowienia")
    private LocalDateTime dataZamowienia;
    @JsonProperty("Calkowita cena do zaplaty netto")
    private BigDecimal cenaNetto;
    @JsonProperty("Calkowity podatek VAT")
    private BigDecimal podatek;
    @JsonProperty("Calkowita cena do zaplaty brutto (netto + podatek)")
    private BigDecimal cenaBrutto;

    //Raczej lista produktyDTO samo i z Zamowienia_ProduktyDTO biore to

    public ZamowieniaDTO(Zamowienia zamowienie){
        this.ID = zamowienie.getID();
        this.uzytkownikDTO = new UzytkownicyZamowienieDTO(zamowienie.getKlienci());
        this.adresDTO = new AdresyDTO(zamowienie.getAdres());
        this.produktyDTOList = zamowienie.getProdukty().stream().map(item -> new Zamowienia_ProduktyDTO(item)).toList();
        this.dataZamowienia = zamowienie.getDataZamowienia();
        this.cenaNetto = BigDecimal.ZERO;
        this.podatek = BigDecimal.ZERO;
        this.cenaBrutto = BigDecimal.ZERO;

        for (Zamowienia_ProduktyDTO dto : produktyDTOList){
            ProduktyZamowieniaDTO produkt = dto.getProduktDTO();
            Integer ilosc = dto.getIlosc();

            BigDecimal cenaNetto = produkt.getCenaNetto();
            BigDecimal podatek = produkt.getPodatek();
            BigDecimal cenaBrutto = produkt.getCenaBrutto();

            this.cenaNetto = this.cenaNetto.add(cenaNetto.multiply(new BigDecimal(ilosc)));
            this.podatek = this.podatek.add(podatek.multiply(new BigDecimal(ilosc)));
            this.cenaBrutto = this.cenaBrutto.add(cenaBrutto.multiply(new BigDecimal(ilosc)));
        }
        this.cenaNetto = this.cenaNetto.setScale(2, RoundingMode.HALF_UP);
        this.podatek = this.podatek.setScale(2,RoundingMode.HALF_UP);
        this.cenaBrutto = this.cenaBrutto.setScale(2,RoundingMode.HALF_UP);


    }

    public ZamowieniaDTO(){};

    public Long getID() {
        return ID;
    }

    public UzytkownicyZamowienieDTO getUzytkownikDTO() {
        return uzytkownikDTO;
    }

    public List<Zamowienia_ProduktyDTO> getProduktyDTOList() {
        return produktyDTOList;
    }

    public AdresyDTO getAdresDTO() {
        return adresDTO;
    }

    public LocalDateTime getDataZamowienia() {
        return dataZamowienia;
    }

    public BigDecimal getCenaNetto() {
        return cenaNetto;
    }

    public BigDecimal getPodatek() {
        return podatek;
    }

    public BigDecimal getCenaBrutto() {
        return cenaBrutto;
    }
}
