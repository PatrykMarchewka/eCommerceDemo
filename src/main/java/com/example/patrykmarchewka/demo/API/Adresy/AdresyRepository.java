package com.example.patrykmarchewka.demo.API.Adresy;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdresyRepository extends JpaRepository<Adresy,Long> {

    List<Adresy> getAdresyByKlient(Uzytkownicy klient);

    Adresy getAdresyByKlientAndID(Uzytkownicy klient, Long id);

    Adresy getAdresyByID(Long id);

    boolean existsByKlientAndID(Uzytkownicy klient, Long id);
}
