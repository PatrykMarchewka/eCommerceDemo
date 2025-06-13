package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyRequestBody;

public class Zamowienia_ProduktyIloscUpdater implements Zamowienia_ProduktyCREATEUpdater,Zamowienia_ProduktyPUTUpdater,Zamowienia_ProduktyPATCHUpdater{
    @Override
    public void CREATEUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        sharedUpdate(zamowieniaProdukty, body);
    }

    @Override
    public void PATCHUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        if (body.getIlosc() != null){
            sharedUpdate(zamowieniaProdukty, body);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        sharedUpdate(zamowieniaProdukty, body);
    }

    void sharedUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body){
        zamowieniaProdukty.setIlosc(body.getIlosc());
    }
}
