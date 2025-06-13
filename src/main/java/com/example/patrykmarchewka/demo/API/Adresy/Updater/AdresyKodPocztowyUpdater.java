package com.example.patrykmarchewka.demo.API.Adresy.Updater;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;

public class AdresyKodPocztowyUpdater implements AdresyCREATEUpdater,AdresyPUTUpdater,AdresyPATCHUpdater{
    @Override
    public void CREATEUpdate(Adresy adres, AdresyRequestBody body) {
        sharedUpdate(adres, body);
    }

    @Override
    public void PATCHUpdate(Adresy adres, AdresyRequestBody body) {
        if (body.getKodPocztowy() != null){
            sharedUpdate(adres, body);
        }
    }

    @Override
    public void PUTUpdate(Adresy adres, AdresyRequestBody body) {
        sharedUpdate(adres, body);
    }

    void sharedUpdate(Adresy adres, AdresyRequestBody body){
        adres.setKodPocztowy(body.getKodPocztowy());
    }
}
