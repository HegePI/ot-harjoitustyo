
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;

    public MaksukorttiTest() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10.0);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        String vastaus = kortti.toString();

        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        // 10 - 4 = 6
        kortti.syoMaukkaasti();
        // 6 - 4 = 2
        kortti.syoMaukkaasti();
        // 2 - 4 = -2
        // Ei voida veloittaa negatiiviselle
        kortti.syoMaukkaasti();
        // Pitäisi olla 2.0 euroa

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());

    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }

    @Test
    public void negatiivinenLatausEiMuutaArvoa() {
        kortti.lataaRahaa(-5.0);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void edullisenSaaHankittuaTasarahalla() {
        kortti = new Maksukortti(2.5);
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());

    }

    @Test
    public void maukkaanSaaHankittuaTasarahalla() {
        kortti = new Maksukortti(4.0);
        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());

    }
}
