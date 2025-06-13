package com.example.patrykmarchewka.demo.API.Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyRequestBody;

public interface ProduktyCREATEUpdater extends ProduktyUpdater{
    void CREATEUpdate(Produkty produkt, ProduktyRequestBody body);
}
