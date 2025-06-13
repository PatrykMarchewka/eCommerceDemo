package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UzytkownicyRepository extends JpaRepository<Uzytkownicy,Long> {
    Optional<Uzytkownicy> getUzytkownicyByEmailAndHaslo(String email, String haslo);

    Optional<Uzytkownicy> getUzytkownicyByEmail(String email);

    Uzytkownicy getUzytkownicyByID(Long id);
}
