package com.example.patrykmarchewka.demo.API.Adresy;

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
@RequestMapping("/api/uzytkownicy")
public class AdresyController {

    private final AdresyService adresyService;
    private final UzytkownicyService uzytkownicyService;

    @Autowired
    public AdresyController(AdresyService adresyService, UzytkownicyService uzytkownicyService){
        this.adresyService = adresyService;
        this.uzytkownicyService = uzytkownicyService;
    }

    @GetMapping("/{uzytkownikID}/adresy")
    @PreAuthorize("hasRole('PRACOWNIK')")
    public ResponseEntity<OdpowiedzAPI<List<AdresyDTO>>> getAllAdresy(@PathVariable Long uzytkownikID){
        if (!uzytkownicyService.existsByID(uzytkownikID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono uzytkownika o podanym ID", null));
        }
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownikByID(uzytkownikID);
        List<AdresyDTO> dtoList = adresyService.getAllAdresy(uzytkownik).stream().map(item -> new AdresyDTO(item)).toList();
        return ResponseEntity.ok(new OdpowiedzAPI<>("Wszystkie zapisane adresy uzytkownika",dtoList));
    }

    @GetMapping("/{uzytkownikID}/adresy/{adresyID}")
    @PreAuthorize("hasRole('PRACOWNIK')")
    public ResponseEntity<OdpowiedzAPI<AdresyDTO>> getAdresyByID(@PathVariable Long uzytkownikID, @PathVariable Long adresyID){
        if (!uzytkownicyService.existsByID(uzytkownikID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono uzytkownika o podanym ID", null));
        }
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownikByID(uzytkownikID);
        if(!adresyService.existsByKlientAndID(uzytkownik,adresyID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }
        return ResponseEntity.ok(new OdpowiedzAPI<>("Informacje o zapisanym adresie",new AdresyDTO(adresyService.getByKlientAndID(uzytkownik,adresyID ))));
    }

    //Zwracamy liste nawet jak jest pusta
    @GetMapping("/me/adresy")
    public ResponseEntity<OdpowiedzAPI<List<AdresyDTO>>> getAllAdresy(Authentication authentication){
        List<AdresyDTO> dtoList = adresyService.getAllAdresy(uzytkownicyService.getUzytkownik(authentication)).stream().map(item -> new AdresyDTO(item)).toList();
        return ResponseEntity.ok(new OdpowiedzAPI<>("Wszystkie twoje zapisane adresy",dtoList));
    }

    @PostMapping("/me/adresy")
    public ResponseEntity<OdpowiedzAPI<AdresyDTO>> createAdresy(Authentication authentication,@RequestBody @Validated(OnCreate.class) AdresyRequestBody body){
        return ResponseEntity.status(HttpStatus.CREATED).body(new OdpowiedzAPI<>("Dodano nowy adres",new AdresyDTO(adresyService.createAdres(body,() -> uzytkownicyService.getUzytkownik(authentication)))));
    }

    @GetMapping("/me/adresy/{adresyID}")
    public ResponseEntity<OdpowiedzAPI<AdresyDTO>> getAdresyByID(Authentication authentication, @PathVariable Long adresyID){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        if(!adresyService.existsByKlientAndID(uzytkownik,adresyID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }
        return ResponseEntity.ok(new OdpowiedzAPI<>("Informacje o zapisanym adresie", new AdresyDTO(adresyService.getByKlientAndID(uzytkownik,adresyID))));
    }

    @PutMapping("/me/adresy/{adresyID}")
    public ResponseEntity<OdpowiedzAPI<AdresyDTO>> putAdresyByID(Authentication authentication, @PathVariable Long adresyID, @RequestBody @Validated(OnCreate.class) AdresyRequestBody body){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        if(!adresyService.existsByKlientAndID(uzytkownik,adresyID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }
        Adresy adres = adresyService.getByKlientAndID(uzytkownik,adresyID);

        return ResponseEntity.ok(new OdpowiedzAPI<>("Edytowano w pelni adres", new AdresyDTO(adresyService.putAdres(adres,body,() -> uzytkownik))));
    }

    @PatchMapping("/me/adresy/{adresyID}")
    public ResponseEntity<OdpowiedzAPI<AdresyDTO>> patchAdresyByID(Authentication authentication, @PathVariable Long adresyID, @RequestBody @Valid AdresyRequestBody body){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        if(!adresyService.existsByKlientAndID(uzytkownik,adresyID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }
        Adresy adres = adresyService.getByKlientAndID(uzytkownik,adresyID);

        return ResponseEntity.ok(new OdpowiedzAPI<>("Edytowano czesciowo adres", new AdresyDTO(adresyService.patchAdres(adres, body, () -> uzytkownik))));
    }

    @DeleteMapping("/me/adresy/{adresyID}")
    public ResponseEntity<OdpowiedzAPI<String>> deleteAdresyByID(Authentication authentication, @PathVariable Long adresyID){
        Uzytkownicy uzytkownik = uzytkownicyService.getUzytkownik(authentication);
        if(!adresyService.existsByKlientAndID(uzytkownik,adresyID)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OdpowiedzAPI<>("Nie znaleziono adresu o podanym ID", null));
        }

        Adresy adres = adresyService.getByKlientAndID(uzytkownik,adresyID);
        adresyService.deleteAdres(adres);
        return ResponseEntity.ok(new OdpowiedzAPI<>("Usunieto adres", null));
    }
}
