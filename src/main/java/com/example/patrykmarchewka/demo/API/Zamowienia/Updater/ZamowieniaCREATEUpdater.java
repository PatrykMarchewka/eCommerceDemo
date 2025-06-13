package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;

public interface ZamowieniaCREATEUpdater extends ZamowieniaUpdater{
    void CREATEUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body);
}
