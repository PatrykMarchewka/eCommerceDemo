package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.Produkty.Updater.*;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProduktyService {

    private final ProduktyRepository produktyRepository;

    @Autowired
    public ProduktyService(ProduktyRepository produktyRepository){
        this.produktyRepository = produktyRepository;
    }

    final List<ProduktyUpdater> updaters(){
        return List.of(
                new ProduktyNazwaUpdater(),
                new ProduktyCenaUpdater(),
                new ProduktyVatUpdater(),
                new ProduktyCzyDostepnyUpdater(),
                new ProduktyDostepnaIloscUpdater()
        );
    }

    private void applyCreateUpdates(Produkty produkt, ProduktyRequestBody body){
        for (ProduktyUpdater updater : updaters()){
            if (updater instanceof ProduktyCREATEUpdater createUpdater){
                createUpdater.CREATEUpdate(produkt, body);
            }
        }
    }

    private void applyPutUpdates(Produkty produkt, ProduktyRequestBody body){
        for (ProduktyUpdater updater : updaters()){
            if (updater instanceof ProduktyPUTUpdater putUpdater){
                putUpdater.PUTUpdate(produkt, body);
            }
        }
    }

    private void applyPatchUpdates(Produkty produkt, ProduktyRequestBody body){
        for (ProduktyUpdater updater : updaters()){
            if (updater instanceof ProduktyPATCHUpdater patchUpdater){
                patchUpdater.PATCHUpdate(produkt, body);
            }
        }
    }

    @Transactional
    public Produkty createProdukty(ProduktyRequestBody body){
        Produkty produkt = new Produkty();
        applyCreateUpdates(produkt, body);
        return saveProdukty(produkt);
    }

    @Transactional
    public Produkty putProdukty(Produkty produkt, ProduktyRequestBody body){
        applyPutUpdates(produkt, body);
        return saveProdukty(produkt);
    }

    @Transactional
    public Produkty patchProdukty(Produkty produkt, ProduktyRequestBody body){
        applyPatchUpdates(produkt, body);
        return saveProdukty(produkt);
    }

    public Produkty saveProdukty(Produkty produkt){
        return produktyRepository.save(produkt);
    }

    public void deleteProdukty(Produkty produkt){
        produktyRepository.delete(produkt);
    }

    public Produkty getProduktByID(Long ID){
        return produktyRepository.getProduktyByID(ID).orElseThrow(RuntimeException::new);
    }

    public Produkty getProduktByDTO(ProduktyDTO dto){
        return getProduktByID(dto.getID());
    }

    public ProduktyDTO getDTOByProdukt(Produkty produkt, Uzytkownicy uzytkownik){
        return new ProduktyDTO(produkt,uzytkownik);
    }

    public List<Produkty> getAllProdukty(){
        return produktyRepository.findAll();
    }
}
