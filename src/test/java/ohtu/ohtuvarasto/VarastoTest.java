package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void negatiivisenMaaranLisaaminenVarastoonEiTeeMitaan() {
        varasto.lisaaVarastoon(-10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisattaessaLiikaaYlimaaraHeitetaanHukkaan() {
        varasto.lisaaVarastoon(100);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttaminenVarastostaEiTeeMitaan() {
        varasto.otaVarastosta(-10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonSaldoksiAsetetaanNollaKunOtetaanEnemmanKuinVarastossaOn() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonTekstiesitysOnOikea() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

    @Test
    public void konstruktoriAsettaaSaldonNollaanAnnettaessaNegatiivinenAlkusaldo() {
        Varasto saldolla = new Varasto(10, -10);
        assertEquals(0, saldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaAnnetunSaldon() {
        Varasto saldolla = new Varasto(10, 10);
        assertEquals(10, saldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusLuoNollatilavuuksisenVarastonMyosAnnettaessaAlkuSaldo() {
        Varasto nollavarastoSaldolla = new Varasto(-10, 10);
        assertEquals(0, nollavarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusMyosAnnettaessaAlkuSaldo() {
        Varasto saldolla = new Varasto(10, 10);
        assertEquals(10, saldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusLuoNollatilavuuksisenVaraston() {
        Varasto nollavarasto = new Varasto(-10);
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
