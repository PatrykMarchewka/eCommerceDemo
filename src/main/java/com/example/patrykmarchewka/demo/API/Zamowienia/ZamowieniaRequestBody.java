package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyRequestBody;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ZamowieniaRequestBody {
    @JsonIgnore
    private Uzytkownicy kupujacy;
    private List<Zamowienia_ProduktyRequestBody> zamowieniaProduktyRequestBodyList;
    private Long adresID;

    public ZamowieniaRequestBody(Uzytkownicy kupujacy, List<Zamowienia_ProduktyRequestBody> zamowieniaProduktyRequestBodyList, Long adresID){
        this.kupujacy = kupujacy;
        this.zamowieniaProduktyRequestBodyList = zamowieniaProduktyRequestBodyList;
        this.adresID = adresID;
    }

    public Uzytkownicy getKupujacy() {
        return kupujacy;
    }

    public void setKupujacy(Uzytkownicy kupujacy) {
        this.kupujacy = kupujacy;
    }

    public List<Zamowienia_ProduktyRequestBody> getZamowieniaProduktyRequestBodyList() {
        return zamowieniaProduktyRequestBodyList;
    }

    public void setZamowieniaProduktyRequestBodyList(List<Zamowienia_ProduktyRequestBody> zamowieniaProduktyRequestBodyList) {
        this.zamowieniaProduktyRequestBodyList = zamowieniaProduktyRequestBodyList;
    }


    public Long getAdresID() {
        return adresID;
    }

    public void setAdresID(Long adresID) {
        this.adresID = adresID;
    }
}
