package com.example.patrykmarchewka.demo.API.Adresy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;

import java.util.function.Supplier;

public class AdresyKlientUpdater implements AdresyCREATEUpdater,AdresyPUTUpdater,AdresyPATCHUpdater{

    private final Supplier<Uzytkownicy> uzytkownik;

    public AdresyKlientUpdater(Supplier<Uzytkownicy> uzytkownik){
        this.uzytkownik = uzytkownik;
    }

    @Override
    public void CREATEUpdate(Adresy adres, AdresyRequestBody body) {
        sharedUpdate(adres);
    }

    @Override
    public void PATCHUpdate(Adresy adres, AdresyRequestBody body) {
        if (uzytkownik.get() != null){
            sharedUpdate(adres);
        }
    }

    @Override
    public void PUTUpdate(Adresy adres, AdresyRequestBody body) {
        sharedUpdate(adres);
    }

    void sharedUpdate(Adresy adres){
        adres.setKlient(uzytkownik.get());
    }
}
