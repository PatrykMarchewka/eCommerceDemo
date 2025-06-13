package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.AdresyService;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;

public class ZamowieniaAdresUpdater implements ZamowieniaCREATEUpdater,ZamowieniaPUTUpdater,ZamowieniaPATCHUpdater{

    private final AdresyService adresyService;

    public ZamowieniaAdresUpdater(AdresyService adresyService){
        this.adresyService = adresyService;
    }

    @Override
    public void CREATEUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    @Override
    public void PATCHUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        if (body.getAdresID() != null){
            sharedUpdate(zamowienie, body);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    void sharedUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body){

        zamowienie.setAdres(adresyService.getByID(body.getAdresID()));
    }
}
