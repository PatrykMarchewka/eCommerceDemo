package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;

public interface ZamowieniaPUTUpdater extends ZamowieniaUpdater{
    void PUTUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body);
}
