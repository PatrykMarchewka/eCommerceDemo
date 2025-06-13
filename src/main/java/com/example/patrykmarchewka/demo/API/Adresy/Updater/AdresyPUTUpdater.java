package com.example.patrykmarchewka.demo.API.Adresy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;

public interface AdresyPUTUpdater extends AdresyUpdater{
    void PUTUpdate(Adresy adres, AdresyRequestBody body);
}
