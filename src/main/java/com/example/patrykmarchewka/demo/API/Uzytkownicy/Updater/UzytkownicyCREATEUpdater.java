package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public interface UzytkownicyCREATEUpdater extends UzytkownicyUpdater{
    void CREATEUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body);
}
