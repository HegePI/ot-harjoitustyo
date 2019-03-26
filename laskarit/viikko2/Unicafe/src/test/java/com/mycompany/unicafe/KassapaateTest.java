package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heikki
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void alustettuOikein() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisenOstoOnnistuu() {
        boolean onnistui = paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(true, onnistui);
    }

    @Test
    public void edullisenOstoOnnistuuIlmanKorttia() {
        int vaihtoRaha = paate.syoEdullisesti(1000);
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(760, vaihtoRaha);
    }

    @Test
    public void edullistenLounaidenMaaraKasvaaOstossa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullistenLounaidenMaaraKasvaaOstossaIlmanKorttia() {
        paate.syoEdullisesti(1000);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaanOstoOnnistuu() {
        boolean onnistui = paate.syoMaukkaasti(kortti);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(600, kortti.saldo());
        assertEquals(true, onnistui);
    }

    @Test
    public void maukkaanOstoOnnistuuIlmanKorttia() {
        int vaihtoRaha = paate.syoMaukkaasti(1000);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(600, vaihtoRaha);

    }

    @Test
    public void maukkaidenLounaidenMaaraKasvaaOstossa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMaaraKasvaaOstossaIlmanKorttia() {
        paate.syoMaukkaasti(1000);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void eiEdullisenOstoaJosEiRahaaKortilla() {
        kortti = new Maksukortti(230);
        boolean onnistui = paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(230, kortti.saldo());
        assertEquals(false, onnistui);
    }
    
        @Test
    public void eiEdullisenOstoaJosEiRahaa() {
        int vaihtoraha = paate.syoEdullisesti(230);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(230, vaihtoraha);
    }
    
    @Test
    public void eiMaukkaanOstoaJosEiRahaaKortilla() {
        kortti = new Maksukortti(390);
        boolean onnistui = paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(390, kortti.saldo());
        assertEquals(false, onnistui);
    }
    
    @Test
    public void eiMaukkaanOstoaJosEiRahaa() {
        int vaihtoraha = paate.syoMaukkaasti(390);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(390, vaihtoraha);
    }

    @Test
    public void lataaminenOnnistuu() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void eiVoiLadataNegatiivista() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }

}
