package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;

public interface ZamowieniaPATCHUpdater extends ZamowieniaUpdater{
    void PATCHUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body);
}
