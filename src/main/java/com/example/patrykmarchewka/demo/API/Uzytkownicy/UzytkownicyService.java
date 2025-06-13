package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Updater.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
public class UzytkownicyService implements UserDetailsService {

    private final UzytkownicyRepository uzytkownicyRepository;

    @Autowired
    public UzytkownicyService(UzytkownicyRepository uzytkownicyRepository){
        this.uzytkownicyRepository = uzytkownicyRepository;
    }

    final List<UzytkownicyUpdater> updaters(Supplier<RoleUzytkownikow> rola){
        return List.of(
                new UzytkownicyVatIDUpdater(),
                new UzytkownicyImieUpdater(),
                new UzytkownicyNazwiskoUpdater(),
                new UzytkownicyEmailUpdater(),
                new UzytkownicyHasloUpdater(),
                new UzytkownicyTelefonUpdater(),
                new UzytkownicyRolaUpdater(rola)
        );
    }

    private void applyCreateUpdates(Uzytkownicy uzytkownik, UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        for (UzytkownicyUpdater updater : updaters(rola)){
            if (updater instanceof UzytkownicyCREATEUpdater createUpdater){
                createUpdater.CREATEUpdate(uzytkownik, body);
            }
        }
    }

    private void applyPutUpdates(Uzytkownicy uzytkownik, UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        for (UzytkownicyUpdater updater : updaters(rola)){
            if (updater instanceof UzytkownicyPUTUpdater putUpdater){
                putUpdater.PUTUpdate(uzytkownik, body);
            }
        }
    }

    private void applyPatchUpdates(Uzytkownicy uzytkownik, UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        for (UzytkownicyUpdater updater : updaters(rola)){
            if (updater instanceof UzytkownicyPATCHUpdater patchUpdater){
                patchUpdater.PATCHUpdate(uzytkownik, body);
            }
        }
    }

    @Transactional
    public Uzytkownicy createUzytkownicy(UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        Uzytkownicy uzytkownicy = new Uzytkownicy();
        applyCreateUpdates(uzytkownicy,body,rola);
        return saveUzytkownicy(uzytkownicy);
    }

    @Transactional
    public Uzytkownicy putUzytkownicy(Uzytkownicy uzytkownik, UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        applyPutUpdates(uzytkownik, body,rola);
        return saveUzytkownicy(uzytkownik);
    }

    @Transactional
    public Uzytkownicy patchUzytkownicy(Uzytkownicy uzytkownik, UzytkownicyRequestBody body, Supplier<RoleUzytkownikow> rola){
        applyPatchUpdates(uzytkownik, body,rola);
        return saveUzytkownicy(uzytkownik);
    }

    public Uzytkownicy saveUzytkownicy(Uzytkownicy uzytkownik){
        return uzytkownicyRepository.save(uzytkownik);
    }

    public void deleteUzytkownicy(Uzytkownicy uzytkownik){
        uzytkownicyRepository.delete(uzytkownik);
    }

    public Uzytkownicy getUzytkownik(Authentication authentication){
        return uzytkownicyRepository.getUzytkownicyByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("Nie znaleziono uzytkownika"));
    }

    public Uzytkownicy getUzytkownikFromDTO(UzytkownicyDTO dto){
        return uzytkownicyRepository.getUzytkownicyByEmailAndHaslo(dto.getEmail(), dto.getHaslo()).orElseThrow(() -> new RuntimeException("Nie znaleziono uzytkownika"));
    }

    public Uzytkownicy getUzytkownikByID(Long ID){
        return uzytkownicyRepository.getUzytkownicyByID(ID);
    }

    public List<Uzytkownicy> getAllUzytkownicy(){
        return uzytkownicyRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Uzytkownicy uzytkownik = uzytkownicyRepository.getUzytkownicyByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika"));

        return User.withUsername(uzytkownik.getEmail()).password(uzytkownik.getHaslo()).roles(uzytkownik.getRola().name()).build();
    }
}
