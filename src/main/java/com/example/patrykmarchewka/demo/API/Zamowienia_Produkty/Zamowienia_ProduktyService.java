package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyService;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaService;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Updater.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
public class Zamowienia_ProduktyService {

    private final Zamowienia_ProduktyRepository zamowienia_produktyRepository;
    private final ProduktyService produktyService;

    @Autowired
    public Zamowienia_ProduktyService(Zamowienia_ProduktyRepository zamowienia_produktyRepository, ProduktyService produktyService){
        this.zamowienia_produktyRepository = zamowienia_produktyRepository;
        this.produktyService = produktyService;
    }

    final List<Zamowienia_ProduktyUpdater> updaters(Supplier<Zamowienia> zamowienie){
        return List.of(
                new Zamowienia_ProduktyProduktUpdater(produktyService),
                new Zamowienia_ProduktyIloscUpdater(),
                new Zamowienia_ProduktyZamowienieUpdater(zamowienie)
        );
    }

    private void applyCreateUpdates(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        for (Zamowienia_ProduktyUpdater updater : updaters(zamowienie)){
            if (updater instanceof Zamowienia_ProduktyCREATEUpdater createUpdater){
                createUpdater.CREATEUpdate(zamowieniaProdukty, body);
            }
        }
    }

    private void applyPutUpdates(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        for (Zamowienia_ProduktyUpdater updater : updaters(zamowienie)){
            if (updater instanceof Zamowienia_ProduktyPUTUpdater putUpdater){
                putUpdater.PUTUpdate(zamowieniaProdukty, body);
            }
        }
    }

    private void applyPatchUpdates(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        for (Zamowienia_ProduktyUpdater updater : updaters(zamowienie)){
            if (updater instanceof Zamowienia_ProduktyPATCHUpdater patchUpdater){
                patchUpdater.PATCHUpdate(zamowieniaProdukty, body);
            }
        }
    }

    @Transactional
    public Zamowienia_Produkty createZamowienia_Produkty(Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        Zamowienia_Produkty zamowieniaProdukty = new Zamowienia_Produkty();
        applyCreateUpdates(zamowieniaProdukty,body, zamowienie);
        //Zapisanie przez cascade
        return zamowieniaProdukty;
    }

    @Transactional
    public Zamowienia_Produkty putZamowienia_Produkty(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        applyPutUpdates(zamowieniaProdukty,body, zamowienie);
        return saveZamowienia_Produkty(zamowieniaProdukty);
    }

    @Transactional
    public Zamowienia_Produkty patchZamowienia_Produkty(Zamowienia_Produkty zamowieniaProdukty, Zamowienia_ProduktyRequestBody body, Supplier<Zamowienia> zamowienie){
        applyPatchUpdates(zamowieniaProdukty,body, zamowienie);
        return saveZamowienia_Produkty(zamowieniaProdukty);
    }

    public Zamowienia_Produkty saveZamowienia_Produkty(Zamowienia_Produkty zamowieniaProdukty){
        return zamowienia_produktyRepository.save(zamowieniaProdukty);
    }

    public void deleteZamowienia_Produkty(Zamowienia_Produkty zamowieniaProdukty){
        zamowienia_produktyRepository.delete(zamowieniaProdukty);
    }
}
