package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyService;

public class ZamowieniaZamowieniaProduktyListUpdater implements ZamowieniaCREATEUpdater,ZamowieniaPUTUpdater,ZamowieniaPATCHUpdater{

    private final Zamowienia_ProduktyService zamowienia_produktyService;

    public ZamowieniaZamowieniaProduktyListUpdater(Zamowienia_ProduktyService zamowienia_produktyService){
        this.zamowienia_produktyService = zamowienia_produktyService;
    }

    @Override
    public void CREATEUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    @Override
    public void PATCHUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        if (body.getZamowieniaProduktyRequestBodyList() != null){
            sharedUpdate(zamowienie, body);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    void sharedUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body){
        zamowienie.setProdukty(body.getZamowieniaProduktyRequestBodyList().stream().map(item -> zamowienia_produktyService.createZamowienia_Produkty(item, () -> zamowienie)).toList());
    }
}
