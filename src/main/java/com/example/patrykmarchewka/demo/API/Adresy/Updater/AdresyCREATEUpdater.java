package com.example.patrykmarchewka.demo.API.Adresy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;

public interface AdresyCREATEUpdater extends AdresyUpdater{
    void CREATEUpdate(Adresy adres, AdresyRequestBody body);
}
