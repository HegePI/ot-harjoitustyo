package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void saldonLisaaminenOikein() {
        kortti.lataaRahaa(500);

        assertEquals("saldo: 15.0", kortti.toString());
    }

    @Test
    public void saldonVahentaminenToimii() {
        kortti.otaRahaa(500);

        assertEquals("saldo: 5.0", kortti.toString());
    }

    @Test
    public void eiMeneNegatiiviselle() {
        kortti.otaRahaa(1200);

        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void trueKunRiittaa() {
        boolean riittaa = kortti.otaRahaa(500);

        assertEquals(riittaa, true);
    }

    @Test
    public void falseKunEiRiita() {
        boolean riittaa = kortti.otaRahaa(1200);

        assertEquals(riittaa, false);
    }
}
