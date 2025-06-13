package com.example.patrykmarchewka.demo.API.Zamowienia;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyService;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.API.Zamowienia.Updater.*;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ZamowieniaService {
    private final ZamowieniaRepository zamowieniaRepository;
    private final UzytkownicyService uzytkownicyService;
    private final Zamowienia_ProduktyService zamowienia_produktyService;
    private final AdresyService adresyService;

    @Autowired
    public ZamowieniaService(ZamowieniaRepository zamowieniaRepository, UzytkownicyService uzytkownicyService, Zamowienia_ProduktyService zamowienia_produktyService, AdresyService adresyService){
        this.zamowieniaRepository = zamowieniaRepository;
        this.uzytkownicyService = uzytkownicyService;
        this.zamowienia_produktyService = zamowienia_produktyService;
        this.adresyService = adresyService;
    }

    final List<ZamowieniaUpdater> updaters(Supplier<LocalDateTime> data){
        return List.of(
                new ZamowieniaAdresUpdater(adresyService),
                new ZamowieniaKupujacyUpdater(uzytkownicyService),
                new ZamowieniaZamowieniaProduktyListUpdater(zamowienia_produktyService),
                new ZamowieniaDataUpdater(data)
        );
    }

    private void applyCreateUpdates(Zamowienia zamowienie,ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        for (ZamowieniaUpdater updater : updaters(data)){
            if (updater instanceof ZamowieniaCREATEUpdater createUpdater){
                createUpdater.CREATEUpdate(zamowienie,body);
            }
        }
    }

    private void applyPutUpdates(Zamowienia zamowienie, ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        for (ZamowieniaUpdater updater : updaters(data)){
            if (updater instanceof ZamowieniaPUTUpdater putUpdater){
                putUpdater.PUTUpdate(zamowienie, body);
            }
        }
    }

    private void applyPatchUpdates(Zamowienia zamowienie, ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        for (ZamowieniaUpdater updater : updaters(data)){
            if (updater instanceof ZamowieniaPATCHUpdater patchUpdater){
                patchUpdater.PATCHUpdate(zamowienie, body);
            }
        }
    }

    @Transactional
    public Zamowienia createZamowienia(ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        Zamowienia zamowienie = new Zamowienia();
        applyCreateUpdates(zamowienie,body, data);
        return saveZamowienia(zamowienie);
    }

    @Transactional
    public Zamowienia putZamowienia(Zamowienia zamowienie,ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        applyPutUpdates(zamowienie, body, data);
        return saveZamowienia(zamowienie);
    }

    @Transactional
    public Zamowienia patchZamowienia(Zamowienia zamowienie, ZamowieniaRequestBody body, Supplier<LocalDateTime> data){
        applyPatchUpdates(zamowienie, body, data);
        return saveZamowienia(zamowienie);
    }

    public Zamowienia saveZamowienia(Zamowienia zamowienia){
        return zamowieniaRepository.save(zamowienia);
    }

    public void deleteZamowienia(Zamowienia zamowienie){
        zamowieniaRepository.delete(zamowienie);
    }

    public Zamowienia getZamowienieFromID(Long ID){
        return zamowieniaRepository.getZamowieniaByID(ID).orElseThrow(RuntimeException::new);
    }

    public boolean existsByID(Long ID){
        return zamowieniaRepository.existsById(ID);
    }

    public Zamowienia getZamowienieFromUzytkownicyAndID(Uzytkownicy uzytkownik, Long ID){
        return zamowieniaRepository.getZamowieniaByKlienciAndID(uzytkownik, ID);
    }

    public List<Zamowienia> getZamowieniaFromUzytkownicy(Uzytkownicy uzytkownik){
        return zamowieniaRepository.getAllByKlienci(uzytkownik);
    }

}
