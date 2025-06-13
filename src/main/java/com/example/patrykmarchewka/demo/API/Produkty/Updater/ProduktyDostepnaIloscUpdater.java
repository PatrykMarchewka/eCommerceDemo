package com.example.patrykmarchewka.demo.API.Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyRequestBody;

public class ProduktyDostepnaIloscUpdater implements ProduktyCREATEUpdater,ProduktyPUTUpdater,ProduktyPATCHUpdater{
    @Override
    public void CREATEUpdate(Produkty produkt, ProduktyRequestBody body) {
        sharedUpdate(produkt, body);
    }

    @Override
    public void PATCHUpdate(Produkty produkt, ProduktyRequestBody body) {
        if (body.getDostepnaIlosc() != null){
            sharedUpdate(produkt, body);
        }
    }

    @Override
    public void PUTUpdate(Produkty produkt, ProduktyRequestBody body) {
        sharedUpdate(produkt, body);
    }

    void sharedUpdate(Produkty produkt, ProduktyRequestBody body){
        produkt.setDostepnaIlosc(body.getDostepnaIlosc());
    }
}
