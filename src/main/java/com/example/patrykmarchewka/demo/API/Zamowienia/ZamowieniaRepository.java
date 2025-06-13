package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZamowieniaRepository extends JpaRepository<Zamowienia,Long> {
    Optional<Zamowienia> getZamowieniaByID(Long id);

    List<Zamowienia> getAllByKlienci(Uzytkownicy klienci);

    Zamowienia getZamowieniaByKlienciAndID(Uzytkownicy klienci, Long id);
}
