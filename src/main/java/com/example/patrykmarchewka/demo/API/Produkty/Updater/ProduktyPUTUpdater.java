package com.example.patrykmarchewka.demo.API.Produkty.Updater;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyRequestBody;

public interface ProduktyPUTUpdater extends ProduktyUpdater{
    void PUTUpdate(Produkty produkt, ProduktyRequestBody body);
}
