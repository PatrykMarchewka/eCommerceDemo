package com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;

public interface UzytkownicyPUTUpdater extends UzytkownicyUpdater{
    void PUTUpdate(Uzytkownicy uzytkownik, UzytkownicyRequestBody body);
}
