package com.example.patrykmarchewka.demo.API.Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyRequestBody;

public interface ProduktyPATCHUpdater extends ProduktyUpdater{
    void PATCHUpdate(Produkty produkt, ProduktyRequestBody body);

}
