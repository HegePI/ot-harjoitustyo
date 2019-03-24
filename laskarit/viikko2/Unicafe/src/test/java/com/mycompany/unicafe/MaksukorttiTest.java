package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void saldonLisaaminenOikein() {
        kortti.lataaRahaa(5);

        assertEquals("saldo: 15.0", kortti.toString());
    }

    @Test
    public void saldonVahentaminenToimii() {
        kortti.otaRahaa(5);

        assertEquals("saldo: 5.0", kortti.toString());
    }

    @Test
    public void eiMeneNegatiiviselle() {
        kortti.otaRahaa(12);

        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void trueKunRiittaa() {
        boolean riittaa = kortti.otaRahaa(5);

        assertEquals(riittaa, true);
    }

    @Test
    public void falseKunEiRiita() {
        boolean riittaa = kortti.otaRahaa(12);

        assertEquals(riittaa, false);
    }
}
