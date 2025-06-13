package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public class UzytkownicyVatIDUpdater implements UzytkownicyCREATEUpdater,UzytkownicyPUTUpdater,UzytkownicyPATCHUpdater{
    @Override
    public void CREATEUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik, body);
    }

    @Override
    public void PATCHUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        if (body.getVatID() != null){
            sharedUpdate(uzytkownik, body);
        }
    }

    @Override
    public void PUTUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdate(uzytkownik, body);
    }

    void sharedUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body){
        uzytkownik.setVatID(body.getVatID());
    }
}
