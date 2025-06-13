package com.example.patrykmarchewka.demo.API.Zamowienia.Updater;

import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public class ZamowieniaDataUpdater implements ZamowieniaCREATEUpdater,ZamowieniaPUTUpdater,ZamowieniaPATCHUpdater{

    private Supplier<LocalDateTime> data;

    public ZamowieniaDataUpdater(Supplier<LocalDateTime> data){
        this.data = data;
    }

    @Override
    public void CREATEUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie);
    }

    @Override
    public void PATCHUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        if (data.get() != null){
            sharedUpdate(zamowienie);
        }
    }

    @Override
    public void PUTUpdate(Zamowienia zamowienie, ZamowieniaRequestBody body) {
        sharedUpdate(zamowienie);
    }

    void sharedUpdate(Zamowienia zamowienie){
        zamowienie.setDataZamowienia(data.get());
    }
}
