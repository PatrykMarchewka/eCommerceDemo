package com.example.patrykmarchewka.demo.API.Produkty;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduktyRepository extends JpaRepository<Produkty,Long> {
    Optional<Produkty> getProduktyByID(Long id);
}
