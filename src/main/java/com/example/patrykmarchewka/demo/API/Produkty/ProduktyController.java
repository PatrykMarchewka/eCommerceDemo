package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.OnCreate;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.OdpowiedzAPI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produkty")
public class ProduktyController {

    private final ProduktyService produktyService;
    private final UzytkownicyService uzytkownicyService;

    @Autowired
    public ProduktyController(ProduktyService produktyService, UzytkownicyService uzytkownicyService){
        this.produktyService = produktyService;
        this.uzytkownicyService = uzytkownicyService;
    }

    @GetMapping
    public ResponseEntity<OdpowiedzAPI<List<ProduktyDTO>>> getAllProdukty(Authentication authentication){
        List<ProduktyDTO> dtoList = produktyService.getAllProdukty().stream().map(item -> new ProduktyDTO(item, uzytkownicyService.getUzytkownik(authentication))).toList();
        return ResponseEntity.ok(new OdpowiedzAPI<>("Lista wszystkich produktow", dtoList));
    }

    /*
    Dodać produkt do oferty sklepu
    Każdy produkt powinien zawierać nazwę, cenę oraz informację o podatku VAT.

    Przykladowy JSON
    {
  "nazwa": "Produkt testowy",
  "cena": 100,
  "stawkaVat": "DWADZIESCIA_TRZY",
  "czyDostepny": true,
  "dostepnaIlosc": 10
}



     */
    @PreAuthorize("hasRole('PRACOWNIK')")
    @PostMapping
    public ResponseEntity<OdpowiedzAPI<ProduktyDTO>> postProdukt(Authentication authentication, @RequestBody @Validated(OnCreate.class) ProduktyRequestBody body){
        if (body.getStawkaVat() == null && body.getNiestandarowyVat() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie podano prawidlowej stawki VAT", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Dodano nowy produkt", new ProduktyDTO(produktyService.createProdukty(body), uzytkownicyService.getUzytkownik(authentication))));
    }

    @GetMapping("/{produktID}")
    public ResponseEntity<OdpowiedzAPI<ProduktyDTO>> getProduktByID(Authentication authentication, @PathVariable Long produktID){
        return ResponseEntity.ok(new OdpowiedzAPI<>("Informacje o produkcie", new ProduktyDTO(produktyService.getProduktByID(produktID), uzytkownicyService.getUzytkownik(authentication))));
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @PutMapping("/{produktID}")
    public ResponseEntity<OdpowiedzAPI<ProduktyDTO>> putProduktByID(Authentication authentication, @PathVariable Long produktID, @RequestBody @Validated(OnCreate.class) ProduktyRequestBody body){
        if (!produktyService.existsByID(produktID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono produktu o podanym ID", null));
        }
        if (body.getStawkaVat() == null && body.getNiestandarowyVat() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie podano prawidlowej stawki VAT", null));
        }


        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        Produkty produkt = produktyService.getProduktByID(produktID);
        return ResponseEntity.ok(new OdpowiedzAPI<>("Edytowano w pelni produkt", new ProduktyDTO(produktyService.putProdukty(produkt,body), uzytkownik)));
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @PatchMapping("/{produktID}")
    public ResponseEntity<OdpowiedzAPI<ProduktyDTO>> patchProduktByID(Authentication authentication, @PathVariable Long produktID, @RequestBody @Valid ProduktyRequestBody body){
        if (!produktyService.existsByID(produktID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono produktu o podanym ID", null));
        }

        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        Produkty produkty = produktyService.getProduktByID(produktID);
        return ResponseEntity.ok(new OdpowiedzAPI<>("Edytowano czesciowo produkt", new ProduktyDTO(produktyService.patchProdukty(produkty,body),uzytkownik)));
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @DeleteMapping("/{produktID}")
    public ResponseEntity<OdpowiedzAPI<String>> deleteProduktByID(Authentication authentication, @PathVariable Long produktID){
        if (!produktyService.existsByID(produktID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono produktu o podanym ID", null));
        }
        Produkty produkt = produktyService.getProduktByID(produktID);
        produktyService.deleteProdukty(produkt);
        return ResponseEntity.ok(new OdpowiedzAPI<>("Usunieto produkt",""));
    }


}
