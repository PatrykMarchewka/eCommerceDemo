package com.example.patrykmarchewka.demo.API.Adresy;

import com.example.patrykmarchewka.demo.API.Kraje;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import jakarta.persistence.*;

@Entity
public class Adresy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Enumerated(EnumType.STRING)
    private Kraje kraj;
    private String miasto;
    private String ulica;
    private String kodPocztowy;
    private String numerDomu;
    private String numerMieszkania;
    @ManyToOne
    private Uzytkownicy klient;

    public Adresy(){};

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Kraje getKraj() {
        return kraj;
    }

    public void setKraj(Kraje kraj) {
        this.kraj = kraj;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }

    public Uzytkownicy getKlient() {
        return klient;
    }

    public void setKlient(Uzytkownicy klient) {
        this.klient = klient;
    }
}
