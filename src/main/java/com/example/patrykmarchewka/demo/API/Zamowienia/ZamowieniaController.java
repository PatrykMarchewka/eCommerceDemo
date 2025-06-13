package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Adresy.AdresyService;
import com.example.patrykmarchewka.demo.API.OnCreate;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyService;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.OdpowiedzAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ZamowieniaController {

    private final ZamowieniaService zamowieniaService;
    private final UzytkownicyService uzytkownicyService;
    private final AdresyService adresyService;
    private final ProduktyService produktyService;

    @Autowired
    public ZamowieniaController(ZamowieniaService zamowieniaService, UzytkownicyService uzytkownicyService, AdresyService adresyService, ProduktyService produktyService){
        this.zamowieniaService = zamowieniaService;
        this.uzytkownicyService = uzytkownicyService;
        this.adresyService = adresyService;
        this.produktyService = produktyService;
    }


    /*
    Pobrać szczegóły zamówienia po numerze zamówienia
    Powinno zwrócić dane klienta, listę produktów z ilościami i cenami oraz podsumowanie
    kwot.
     */
    @PreAuthorize("hasRole('PRACOWNIK')")
    @GetMapping("/zamowienia/{zamowienieID}")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> getZamowieniePracownik(@PathVariable Long zamowienieID){
        if (!zamowieniaService.existsByID(zamowienieID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono zamowienia o podanym ID", null));
        }

        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Informacje o zamowieniu", new ZamowieniaDTO(zamowieniaService.getZamowienieFromID(zamowienieID))));
    }

    //Brak walidacji, zwracamy nawet jak lista jest pusta
    @GetMapping("/me/zamowienia")
    public ResponseEntity<OdpowiedzAPI<List<ZamowieniaDTO>>> getAllZamowienia(Authentication authentication){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        List<ZamowieniaDTO> dtoList = zamowieniaService.getZamowieniaFromUzytkownicy(uzytkownik).stream().map(item -> new ZamowieniaDTO(item)).toList();
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Wszystkie twoje zamowienia", dtoList));
    }

    /*
    Złożyć zamówienie
    Klient może złożyć zamówienie składające się z jednego lub więcej produktów.

    Przykladowy JSON
    {
  "adresID": 1,
  "produkty": [
    {
      "produktyID": 1,
      "ilosc": 2
    },
    {
      "produktyID": 2,
      "ilosc": 1
    }
  ]
}

     */
    @PostMapping("/me/zamowienia")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> createZamowienia(Authentication authentication, @RequestBody @Validated(OnCreate.class) ZamowieniaRequestBody body){
        if (body.getZamowieniaProduktyRequestBodyList().stream().anyMatch(item -> !produktyService.existsByID(item.getProduktyID()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono produktu z podanym ID", null));
        }
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        if(!adresyService.existsByKlientAndID(uzytkownik, body.getAdresID())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }

        body.setKupujacy(uzytkownik);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Stworzono nowe zamowienie", new ZamowieniaDTO(zamowieniaService.createZamowienia(body,() -> LocalDateTime.now()))));
    }


    @GetMapping("/me/zamowienia/{zamowienieID}")
    public ResponseEntity<OdpowiedzAPI<ZamowieniaDTO>> getZamowienieByID(Authentication authentication, @PathVariable Long zamowienieID){
        if (!zamowieniaService.existsByID(zamowienieID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono zamowienia o podanym ID", null));
        }
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Informacje o twoim zamowieniu", new ZamowieniaDTO(zamowieniaService.getZamowienieFromUzytkownicyAndID(uzytkownik,zamowienieID))));
    }
}
