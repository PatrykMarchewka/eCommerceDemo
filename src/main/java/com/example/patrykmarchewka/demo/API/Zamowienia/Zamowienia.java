package com.example.patrykmarchewka.demo.API.Zamowienia;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Zamowienia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @ManyToOne
    private Uzytkownicy klienci;
    @ManyToOne
    @JoinColumn(name = "ADRESY_ID")
    private Adresy adres;

    //Przy usunieciu zamowienia z bazy usuwamy takze powiazane dane z tym czego dotyczylo zamowienie
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "zamowienia_produkty_id")
    private List<Zamowienia_Produkty> produkty = new ArrayList<>();
    private LocalDateTime dataZamowienia;

    public Zamowienia(){};

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Uzytkownicy getKlienci() {
        return klienci;
    }

    public void setKlienci(Uzytkownicy klient) {
        this.klienci = klient;
    }

    public List<Zamowienia_Produkty> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<Zamowienia_Produkty> produkty) {
        this.produkty = produkty;
    }

    public LocalDateTime getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDateTime dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Adresy getAdres() {
        return adres;
    }

    public void setAdres(Adresy adres) {
        this.adres = adres;
    }
}
