package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyRequestBody;

public interface Zamowienia_ProduktyPATCHUpdater extends Zamowienia_ProduktyUpdater{
    void PATCHUpdate(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body);
}
