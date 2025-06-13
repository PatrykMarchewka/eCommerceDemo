package com.example.patrykmarchewka.demo.API.Produkty;

import com.example.patrykmarchewka.demo.API.StawkiVAT;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produkty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nazwa;
    private BigDecimal cena;
    @Enumerated(EnumType.STRING)
    private StawkiVAT stawkaVat;
    private BigDecimal niestandarowyVat;
    private Boolean czyDostepny;
    private Integer dostepnaIlosc;

    //Lista Zamowienia_Produkty dla potencjalnych statystyk w pozniejszym czasie
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "zamowienia_produkty_id")
    private List<Zamowienia_Produkty> zamowienia_produktyList = new ArrayList<>();



    public Produkty(){};

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public StawkiVAT getStawkaVat() {
        return stawkaVat;
    }

    public void setStawkaVat(StawkiVAT stawkaVat) {
        this.stawkaVat = stawkaVat;
    }

    public BigDecimal getNiestandarowyVat() {
        return niestandarowyVat;
    }

    public void setNiestandarowyVat(BigDecimal niestandarowyVat) {
        this.niestandarowyVat = niestandarowyVat;
    }

    public boolean getCzyDostepny() {
        return czyDostepny;
    }

    public void setCzyDostepny(Boolean czyDostepny) {
        this.czyDostepny = czyDostepny;
    }

    public Integer getDostepnaIlosc() {
        return dostepnaIlosc;
    }

    public void setDostepnaIlosc(Integer dostepnaIlosc) {
        this.dostepnaIlosc = dostepnaIlosc;
    }


    public List<Zamowienia_Produkty> getZamowienia_produktyList() {
        return zamowienia_produktyList;
    }

    public void setZamowienia_produktyList(List<Zamowienia_Produkty> zamowienia_produktyList) {
        this.zamowienia_produktyList = zamowienia_produktyList;
    }
}
