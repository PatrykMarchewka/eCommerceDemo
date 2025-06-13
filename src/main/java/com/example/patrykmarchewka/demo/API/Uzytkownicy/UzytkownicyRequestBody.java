package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import com.example.patrykmarchewka.demo.API.Adresy.AdresyDTO;
import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UzytkownicyRequestBody {
    private String vatID;
    @NotBlank(message = "Imie nie moze byc puste")
    private String imie;
    @NotBlank(message = "Nazwisko nie moze byc puste")
    private String nazwisko;
    //Podstawowy regex do walidacji email w formacie: [tekst]@[tekst].[tekst]
    //Przyjete: user@strona.com | 123@456.789 | abc123@xyz456.test
    //Odrzucone: @strona.pl (brak tekstu przed @) | user@.com (brak tekstu po @ i przed .) | user@strona. (brak tekstu po .) | user@stronapl (brak .)
    //Odrzucone ktore powinny przejsc: john.may@gmail.com (kropka przed @) | john@gmail.com.pl (dwie kropki @)
    @NotBlank(message = "Email nie moze byc pusty") @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$", message = "Nieprawidlowy adres Email")
    private String email;
    @NotBlank(message = "Haslo nie moze byc puste")
    private String haslo;
    private List<AdresyDTO> adres = new ArrayList<>();
    private String telefon;

    public UzytkownicyRequestBody(@Nullable String vatID, String imie, String nazwisko, String email, String haslo, @Nullable List<AdresyDTO> adres, @Nullable String telefon){
        this.vatID = vatID;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.adres = adres;
        this.telefon = telefon;
    }

    public UzytkownicyRequestBody(){}


    public String getVatID() {
        return vatID;
    }

    public void setVatID(String vatID) {
        this.vatID = vatID;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public List<AdresyDTO> getAdres() {
        return adres;
    }

    public void setAdres(List<AdresyDTO> adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
