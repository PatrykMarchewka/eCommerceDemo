package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public class UzytkownicyEmailUpdater implements UzytkownicyCREATEUpdater,UzytkownicyPUTUpdater,UzytkownicyPATCHUpdater{
    @Override
    public void CREATEUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdates(uzytkownik, body);
    }

    @Override
    public void PATCHUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        if (body.getEmail() != null){
            sharedUpdates(uzytkownik, body);
        }
    }

    @Override
    public void PUTUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body) {
        sharedUpdates(uzytkownik, body);
    }

    void sharedUpdates(Uzytkownicy uzytkownik, UzytkownicyRequestBody body){
        uzytkownik.setEmail(body.getEmail());
    }
}
