package com.example.patrykmarchewka.demo.API.Uzytkownicy;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Uzytkownicy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String vatID;
    private String imie;
    private String nazwisko;
    @OneToMany
    private List<Adresy> adres = new ArrayList<>();
    @Column(unique = true)
    private String email;
    private String haslo; //Dla tego zadania haslo jest trzymane w bazie jako tekst, w normalnym przypadku staralbym sie dodac jakies haszowanie, korzystajac z np BCrypt
    private String telefon;
    @Enumerated(EnumType.STRING)
    private RoleUzytkownikow rola;
    @OneToMany
    private List<Zamowienia> zamowienia = new ArrayList<>();




    public Uzytkownicy(){};

    public Long getID(){return ID;}
    public void setID(Long ID){this.ID = ID;}
    public String getVatID(){return vatID;}
    public void setVatID(String vatID){this.vatID = vatID;}
    public String getImie() { return imie;}
    public void setImie(String Imie){ this.imie = Imie;}
    public String getNazwisko() { return nazwisko;}
    public void setNazwisko(String Nazwisko){ this.nazwisko = Nazwisko;}
    public List<Adresy> getAdres() { return adres; }
    public void setAdres(List<Adresy> Adres) { this.adres = Adres; }
    public String getEmail() { return email;}
    public void setEmail(String email){ this.email = email;}
    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo){ this.haslo = haslo; }
    public String getTelefon() { return telefon;}
    public void setTelefon(String telefon){ this.telefon = telefon; }
    public RoleUzytkownikow getRola() { return rola; }
    public void setRola(RoleUzytkownikow rola) { this.rola = rola; }
    public List<Zamowienia> getZamowienia() {return zamowienia;}
    public void setZamowienia(List<Zamowienia> zamowienia) {this.zamowienia = zamowienia;}
}
