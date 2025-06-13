package com.example.patrykmarchewka.demo.API.Adresy;

import com.example.patrykmarchewka.demo.API.Kraje;
import com.example.patrykmarchewka.demo.API.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdresyRequestBody {
    @NotNull(groups = OnCreate.class)
    private Kraje kraj;
    @NotBlank(groups = OnCreate.class)
    private String miasto;
    @NotBlank(groups = OnCreate.class)
    private String ulica;
    private String kodPocztowy;
    @NotBlank(groups = OnCreate.class)
    private String numerDomu;
    private String numerMieszkania;

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
}
