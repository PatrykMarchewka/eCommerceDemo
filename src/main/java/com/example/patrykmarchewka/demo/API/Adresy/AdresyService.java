package com.example.patrykmarchewka.demo.API.Adresy;

import com.example.patrykmarchewka.demo.API.Adresy.Updater.*;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
public class AdresyService {

    private final AdresyRepository adresyRepository;

    @Autowired
    public AdresyService(AdresyRepository adresyRepository){
        this.adresyRepository = adresyRepository;
    }

    final List<AdresyUpdater> updaters(Supplier<Uzytkownicy> uzytkownik){
        return List.of(
                new AdresyKlientUpdater(uzytkownik),
                new AdresyKrajUpdater(),
                new AdresyMiastoUpdater(),
                new AdresyKodPocztowyUpdater(),
                new AdresyUlicaUpdater(),
                new AdresyNumerDomuUpdater(),
                new AdresyNumerMieszkaniaUpdater()
        );
    }

    private void applyCREATEUpdates(Adresy adres, AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        for (AdresyUpdater updater : updaters(uzytkownik)){
            if (updater instanceof AdresyCREATEUpdater createUpdater){
                createUpdater.CREATEUpdate(adres, body);
            }
        }
    }

    private void applyPUTUpdates(Adresy adres, AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        for (AdresyUpdater updater : updaters(uzytkownik)){
            if (updater instanceof AdresyPUTUpdater putUpdater){
                putUpdater.PUTUpdate(adres, body);
            }
        }
    }

    private void applyPATCHUpdates(Adresy adres, AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        for (AdresyUpdater updater : updaters(uzytkownik)){
            if (updater instanceof AdresyPATCHUpdater patchUpdater){
                patchUpdater.PATCHUpdate(adres, body);
            }
        }
    }


    @Transactional
    public Adresy createAdres(AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        Adresy adres = new Adresy();
        applyCREATEUpdates(adres,body, uzytkownik);
        return saveAdres(adres);
    }

    @Transactional
    public Adresy putAdres(Adresy adres, AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        applyPUTUpdates(adres, body, uzytkownik);
        return saveAdres(adres);
    }

    @Transactional
    public Adresy patchAdres(Adresy adres,AdresyRequestBody body, Supplier<Uzytkownicy> uzytkownik){
        applyPATCHUpdates(adres, body, uzytkownik);
        return saveAdres(adres);
    }


    public Adresy saveAdres(Adresy adres){
        return adresyRepository.save(adres);
    }

    public void deleteAdres(Adresy adres){
        adresyRepository.delete(adres);
    }
    
    public List<Adresy> getAllAdresy(Uzytkownicy uzytkownik){
        return adresyRepository.getAdresyByKlient(uzytkownik);
    }

    public Adresy getByKlientAndID(Uzytkownicy uzytkownik, Long ID){
        return adresyRepository.getAdresyByKlientAndID(uzytkownik, ID);
    }

    public boolean existsByKlientAndID(Uzytkownicy uzytkownik, Long ID){
        return adresyRepository.existsByKlientAndID(uzytkownik, ID);
    }

    public Adresy getByID(Long ID){
        return adresyRepository.getAdresyByID(ID);
    }
}
