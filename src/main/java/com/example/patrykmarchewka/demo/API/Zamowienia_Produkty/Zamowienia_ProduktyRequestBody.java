package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;

public class Zamowienia_ProduktyRequestBody {
    private final Long produktyID;
    private final Integer ilosc;

    public Zamowienia_ProduktyRequestBody(Long produktyID, Integer ilosc){
        this.produktyID = produktyID;
        this.ilosc = ilosc;
    }

    public Long getProduktyID() {
        return produktyID;
    }

    public Integer getIlosc() {
        return ilosc;
    }


}
