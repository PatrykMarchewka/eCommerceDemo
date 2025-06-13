package com.example.patrykmarchewka.demo.API;



//Potrzebne do obliczania podatku VAT,
//Jezeli kupujacy jest spoza Unii Europejskiej lub posiada VatID to wtedy liczymy VAT jako 0%, inaczej wedlug normalnej stawki
//Dlatego enum zamiast posiadac liste wszystkich krajow jest zminimalizowany do tych trzech pozycji
public enum Kraje {
    POLSKA,
    INNY_KRAJ_W_UNII_EUROPEJSKIEJ,
    INNY_KRAJ_SPOZA_UNII_EUROPEJSKIEJ
}
