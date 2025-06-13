package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.OdpowiedzAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ZamowieniaController {

    private final ZamowieniaService zamowieniaService;
    private final UzytkownicyService uzytkownicyService;

    @Autowired
    public ZamowieniaController(ZamowieniaService zamowieniaService, UzytkownicyService uzytkownicyService){
        this.zamowieniaService = zamowieniaService;
        this.uzytkownicyService = uzytkownicyService;
    }


    /*
    Pobrać szczegóły zamówienia po numerze zamówienia
    Powinno zwrócić dane klienta, listę produktów z ilościami i cenami oraz podsumowanie
    kwot.
     */
    @PreAuthorize("hasRole('PRACOWNIK')")
    @GetMapping("/zamowienia/{zamowienieID}")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> getZamowieniePracownik(@PathVariable Long zamowienieID){
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Informacje o zamowieniu", new ZamowieniaDTO(zamowieniaService.getZamowienieFromID(zamowienieID))));
    }


    @GetMapping("/me/zamowienia")
    public ResponseEntity<OdpowiedzAPI<List<ZamowieniaDTO>>> getAllZamowienia(Authentication authentication){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        List<ZamowieniaDTO> dtoList = zamowieniaService.getZamowieniaFromUzytkownicy(uzytkownik).stream().map(item -> new ZamowieniaDTO(item)).toList();
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Wszystkie twoje zamowienia", dtoList));
    }

    /*
    Złożyć zamówienie
    Klient może złożyć zamówienie składające się z jednego lub więcej produktów.
     */
    @PostMapping("/me/zamowienia")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> createZamowienia(Authentication authentication, @RequestBody ZamowieniaRequestBody body){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        body.setKupujacy(uzytkownik);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Stworzono nowe zamowienie", new ZamowieniaDTO(zamowieniaService.createZamowienia(body,() -> LocalDateTime.now()))));
    }


    @GetMapping("/me/zamowienia/{zamowienieID}")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> getZamowienieByID(Authentication authentication, @PathVariable Long zamowienieID){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Informacje o twoim zamowieniu", new ZamowieniaDTO(zamowieniaService.getZamowienieFromUzytkownicyAndID(uzytkownik,zamowienieID))));
    }
}
