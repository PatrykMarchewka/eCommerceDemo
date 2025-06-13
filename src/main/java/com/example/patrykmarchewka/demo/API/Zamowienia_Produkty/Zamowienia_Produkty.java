package com.example.patrykmarchewka.demo.API.Zamowienia_Produkty;

import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia.Zamowienia;
import jakarta.persistence.*;

//Przechowuje ID Zamowienia, ID produktu i ilosc tego produkty
//Przy zamowieniach Kilku roznych produktow tworzymy kilka Zamowienia_Produkty
@Entity
public class Zamowienia_Produkty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @ManyToOne
    private Zamowienia zamowienie;
    @ManyToOne
    private Produkty produkt;
    private Integer ilosc;


    public Zamowienia_Produkty(){};

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Zamowienia getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienia zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Produkty getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkty produkt) {
        this.produkt = produkt;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }
}
