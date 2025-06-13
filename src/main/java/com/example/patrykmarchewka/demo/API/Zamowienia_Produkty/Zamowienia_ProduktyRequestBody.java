package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;

import com.example.patrykmarchewka.demo.API.OnCreate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Zamowienia_ProduktyRequestBody {

    @NotNull(groups = OnCreate.class)
    @Positive
    private final Long produktyID;
    @NotNull(groups = OnCreate.class)
    @Positive
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
