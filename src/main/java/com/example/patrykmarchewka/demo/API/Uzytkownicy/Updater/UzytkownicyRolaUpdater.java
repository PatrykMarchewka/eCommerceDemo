package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

import java.util.function.Supplier;

public class UzytkownicyRolaUpdater implements UzytkownicyCREATEUpdater,UzytkownicyPUTUpdater,UzytkownicyPATCHUpdater{

    private final Supplier<RoleUzytkownikow> rola;

    public UzytkownicyRolaUpdater(Supplier<RoleUzytkownikow> rola){
        this.rola = rola;
    }


    @Override
    public void CREATEUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik);
    }

    @Override
    public void PATCHUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        if (rola.get() != null){
            sharedUpdate(uzytkownik);
        }

    }

    @Override
    public void PUTUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik);
    }

    void sharedUpdate(Uzytkownicy uzytkownik){
        uzytkownik.setRola(rola.get());
    }
}
