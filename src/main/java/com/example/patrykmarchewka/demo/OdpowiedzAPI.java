package com.example.patrykmarchewka.demo;

import java.time.LocalDateTime;

public class OdpowiedzAPI<T> {
    private final String wiadomosc;
    private final T dane;
    private final String data;

    public OdpowiedzAPI(String wiadomosc, T dane){
        this.wiadomosc = wiadomosc;
        this.dane = dane;
        this.data = LocalDateTime.now().toString();
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public T getDane() {
        return dane;
    }

    public String getData() {
        return data;
    }
}
