package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyRequestBody;

import java.util.function.Supplier;

public class Zamowienia_ProduktyZamowienieUpdater implements Zamowienia_ProduktyCREATEUpdater,Zamowienia_ProduktyPUTUpdater,Zamowienia_ProduktyPATCHUpdater{

    private final Supplier<Zamowienia> zamowienie;

    public Zamowienia_ProduktyZamowienieUpdater(Supplier<Zamowienia> zamowienie){
        this.zamowienie = zamowienie;
    }


    @Override
    public void CREATEUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        sharedUpdate(zamowieniaProdukty);
    }

    @Override
    public void PATCHUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        if (zamowienie.get() != null){
            sharedUpdate(zamowieniaProdukty);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body) {
        sharedUpdate(zamowieniaProdukty);
    }

    void sharedUpdate(Zamowienia_Produkty zamowieniaProdukty){
        zamowieniaProdukty.setZamowienie(zamowienie.get());
    }
}
