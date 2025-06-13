package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import com.example.patrykmarchewka.demo.API.OnCreate;
import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
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
@RequestMapping("/api/uzytkownicy")
public class UzytkownicyController {

    private final UzytkownicyService uzytkownicyService;

    @Autowired
    public UzytkownicyController(UzytkownicyService uzytkownicyService){
        this.uzytkownicyService = uzytkownicyService;
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @GetMapping
    public ResponseEntity<OdpowiedzAPI<List<UzytkownicyDTO>>> getAllUzytkownicy(){
        List<UzytkownicyDTO> dtoList = uzytkownicyService.getAllUzytkownicy().stream().map(item -> new UzytkownicyDTO(item)).toList();
        return ResponseEntity.ok(new OdpowiedzAPI<>("Wszyscy uzytkownicy", dtoList));
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @GetMapping("/{uzytkownikID}")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> getUzytkownikByID(@PathVariable Long uzytkownikID){
        return ResponseEntity.ok(new OdpowiedzAPI<>("Informacje o uzytkowniku", new UzytkownicyDTO(uzytkownicyService.getUzytkownikByID(uzytkownikID))));
    }

    @GetMapping("/me")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> getUzytkownik(Authentication authentication){
        return ResponseEntity.ok(new OdpowiedzAPI<>("Informacje o uzytkowniku", new UzytkownicyDTO(uzytkownicyService.getUzytkownik(authentication))));
    }

    @PutMapping("/me")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> putUzytkownik(Authentication authentication, @RequestBody @Validated(OnCreate.class) UzytkownicyRequestBody body){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);

        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Edytowano calkowicie uzytkownika",new UzytkownicyDTO(uzytkownicyService.putUzytkownicy(uzytkownik,body,() -> uzytkownik.getRola()))));
    }

    @PatchMapping("/me")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> patchUzytkownik(Authentication authentication, @RequestBody @Valid UzytkownicyRequestBody body){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);

        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Edytowano czesciowo uzytkownika", new UzytkownicyDTO(uzytkownicyService.patchUzytkownicy(uzytkownik,body,() -> uzytkownik.getRola()))));
    }

    @DeleteMapping("/me")
    public ResponseEntity<OdpowiedzAPI<String>> deleteUzytkownik(Authentication authentication){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        uzytkownicyService.deleteUzytkownicy(uzytkownik);
        return ResponseEntity.ok().body(new OdpowiedzAPI<>("Usunieto uzytkownika", null));
    }

    /*
    Zarejestrować kontrahenta (klienta)
    Przykladowy JSON:
    {
  "vatID": "PL1234567890",
  "imie": "Jan",
  "nazwisko": "Kowalski",
  "email": "user@example.com",
  "haslo": "MojeSilneHaslo123!",
  "telefon": "+48123456789",
  "adres": [
    {
      "kraj": "POLSKA",
      "miasto": "Warszawa",
      "ulica": "Marszalkowska",
      "kodPocztowy": "00-001",
      "numerDomu": "12A",
      "numerMieszkania": "34"
    }
  ]
}




     */
    @PostMapping("/rejestracja")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> createUzytkownik(@RequestBody @Validated(OnCreate.class) UzytkownicyRequestBody body){
        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Stworzono nowego uzytkownika", new UzytkownicyDTO(uzytkownicyService.createUzytkownicy(body,() -> RoleUzytkownikow.KLIENT))));
    }

    @PreAuthorize("hasRole('PRACOWNIK')")
    @PostMapping("/rejestracja/pracownik")
    public ResponseEntity<OdpowiedzAPI<UzytkownicyDTO>> createUzytkownikPracownik(@RequestBody @Validated(OnCreate.class) UzytkownicyRequestBody body){
        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Stworzono nowego pracownika", new UzytkownicyDTO(uzytkownicyService.createUzytkownicy(body,() -> RoleUzytkownikow.PRACOWNIK))));
    }






}
