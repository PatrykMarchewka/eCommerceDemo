package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.AdresyService;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public class UzytkownicyAdresyUpdater implements UzytkownicyCREATEUpdater,UzytkownicyPUTUpdater, UzytkownicyPATCHUpdater {

    private final AdresyService adresyService;

    public UzytkownicyAdresyUpdater(AdresyService adresyService){
        this.adresyService = adresyService;
    }

    @Override
    public void CREATEUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik, body);
    }

    @Override
    public void PATCHUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        if (body.getAdres() != null){
            sharedUpdate(uzytkownik, body);
        }
    }

    @Override
    public void PUTUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik, body);
    }

    void sharedUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body){
        //zamowienie.setProdukty(body.getZamowieniaProduktyRequestBodyList().stream().map(item -> zamowienia_produktyService.createZamowienia_Produkty(item, () -> zamowienie)).toList());
        uzytkownik.setAdres(body.getAdres().stream().map(item -> adresyService.createAdres(item, () -> uzytkownik)).toList());
    }
}
