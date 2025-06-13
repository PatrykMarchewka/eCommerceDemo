package com.example.patrykmarchewka.demo.API.Adresy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;

public interface AdresyPATCHUpdater extends AdresyUpdater{
    void PATCHUpdate(Adresy adres, AdresyRequestBody body);
}
