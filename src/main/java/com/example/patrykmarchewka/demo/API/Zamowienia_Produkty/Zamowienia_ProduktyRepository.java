package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Zamowienia_ProduktyRepository extends JpaRepository<Zamowienia_Produkty,Long> {
    //Optional<Zamowienia_Produkty> findByZamowienieAndProduktAndIlosc(Zamowienia zamowienie, Produkty produkt, Integer ilosc);

    //Optional<Zamowienia_Produkty> getZamowienia_ProduktyByZamowienieAndProduktAndIlosc(Zamowienia zamowienie, Produkty produkt, Integer ilosc);
}
