package com.example.patrykmarchewka.demo;

import com.example.patrykmarchewka.demo.API.Adresy.Adresy;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyRequestBody;
import com.example.patrykmarchewka.demo.API.Adresy.AdresyService;
import com.example.patrykmarchewka.demo.API.Kraje;
import com.example.patrykmarchewka.demo.API.Produkty.Produkty;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyRequestBody;
import com.example.patrykmarchewka.demo.API.Produkty.ProduktyService;
import com.example.patrykmarchewka.demo.API.RoleUzytkownikow;
import com.example.patrykmarchewka.demo.API.StawkiVAT;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.Uzytkownicy;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRepository;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyRequestBody;
import com.example.patrykmarchewka.demo.API.Uzytkownicy.UzytkownicyService;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaRequestBody;
import com.example.patrykmarchewka.demo.API.Zamowienia.ZamowieniaService;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_Produkty;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyRequestBody;
import com.example.patrykmarchewka.demo.API.Zamowienia_Produkty.Zamowienia_ProduktyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {


	@Autowired
	public UzytkownicyService uzytkownicyService;
    @Autowired
    private ProduktyService produktyService;
    @Autowired
    private AdresyService adresyService;
    @Autowired
    private ZamowieniaService zamowieniaService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void ToRun(){
		//Do testow
		try {
			StworzUzytkownikaKlient();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzUzytkownikaPracownik();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzProdukt();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzProduktDrugi();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzAdresWPolsce();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzAdresWUnii();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzAdresSpozaUnii();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			StworzZamowienia(uzytkownicyService.getUzytkownikByID(1L));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void StworzUzytkownikaKlient(){
		UzytkownicyRequestBody body = new UzytkownicyRequestBody();
		body.setEmail("test@test.pl");
		body.setHaslo("test");
		body.setVatID("123-123-123");
		uzytkownicyService.createUzytkownicy(body,() -> RoleUzytkownikow.KLIENT);
	}

	private void StworzUzytkownikaPracownik(){
		UzytkownicyRequestBody body = new UzytkownicyRequestBody();
		body.setEmail("praca@praca.pl");
		body.setHaslo("praca");
		uzytkownicyService.createUzytkownicy(body, () -> RoleUzytkownikow.PRACOWNIK);
	}

	private void StworzProdukt(){
		ProduktyRequestBody body = new ProduktyRequestBody();
		body.setNazwa("Komputer");
		body.setCena(new BigDecimal("5000"));
		body.setStawkaVat(StawkiVAT.DWADZIESCIA_TRZY);
		body.setCzyDostepny(true);
		body.setDostepnaIlosc(50);
		produktyService.createProdukty(body);
	}

	private void StworzProduktDrugi(){
		ProduktyRequestBody body = new ProduktyRequestBody();
		body.setNazwa("Inny komputer");
		body.setCena(new BigDecimal("9999"));
		body.setNiestandarowyVat(new BigDecimal("5.5"));
		body.setCzyDostepny(false);
		body.setDostepnaIlosc(1);
		produktyService.createProdukty(body);
	}

	@Transactional
    protected void StworzAdresWPolsce(){
		AdresyRequestBody body = new AdresyRequestBody();
		body.setKraj(Kraje.POLSKA);
		body.setKodPocztowy("80-800");
		body.setMiasto("Bydgoszcz");
		body.setUlica("Fajna");
		body.setNumerDomu("1");
		body.setNumerMieszkania("2");
		adresyService.createAdres(body,() -> uzytkownicyService.getUzytkownikByID(1L));
	}

	@Transactional
	protected void StworzAdresWUnii(){
		AdresyRequestBody body = new AdresyRequestBody();
		body.setKraj(Kraje.INNY_KRAJ_W_UNII_EUROPEJSKIEJ);
		body.setKodPocztowy("111-111");
		body.setMiasto("Berlin");
		body.setUlica("Eine");
		body.setNumerDomu("5B");
		body.setNumerMieszkania(null);
		adresyService.createAdres(body,() -> uzytkownicyService.getUzytkownikByID(1L));
	}

	@Transactional
	protected void StworzAdresSpozaUnii(){
		AdresyRequestBody body = new AdresyRequestBody();
		body.setKraj(Kraje.INNY_KRAJ_SPOZA_UNII_EUROPEJSKIEJ);
		body.setKodPocztowy(null);
		body.setMiasto("Tokyo");
		body.setNumerDomu("12");
		body.setNumerMieszkania("173C");
		body.setUlica("Hi");

		adresyService.createAdres(body,() -> uzytkownicyService.getUzytkownikByID(1L));
	}

	@Transactional
	protected void StworzZamowienia(Uzytkownicy kupujacy){
		List<Zamowienia_ProduktyRequestBody> lista =
			List.of(
					new Zamowienia_ProduktyRequestBody(1L,2),
					new Zamowienia_ProduktyRequestBody(2L,3)
			);

		ZamowieniaRequestBody body = new ZamowieniaRequestBody(kupujacy,lista,2L);
		zamowieniaService.createZamowienia(body,() -> LocalDateTime.now());
	}

}
