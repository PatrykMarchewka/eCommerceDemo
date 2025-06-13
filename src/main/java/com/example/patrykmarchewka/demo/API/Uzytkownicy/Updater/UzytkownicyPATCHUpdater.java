package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public interface UzytkownicyPATCHUpdater extends UzytkownicyUpdater{
    void PATCHUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body);
}
