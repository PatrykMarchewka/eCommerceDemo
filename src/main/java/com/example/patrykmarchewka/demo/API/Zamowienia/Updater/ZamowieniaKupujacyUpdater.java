package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;

public class ZamowieniaKupujacyUpdater implements ZamowieniaCREATEUpdater, ZamowieniaPUTUpdater,ZamowieniaPATCHUpdater {

    private final UzytkownicyService uzytkownicyService;

    public ZamowieniaKupujacyUpdater(UzytkownicyService uzytkownicyService){
        this.uzytkownicyService = uzytkownicyService;
    }


    @Override
    public void CREATEUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    @Override
    public void PATCHUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        if (body.getKupujacy() != null){
            sharedUpdate(zamowienie, body);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie, body);
    }

    void sharedUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body){
        zamowienie.setKlienci(body.getKupujacy());
    }
}
