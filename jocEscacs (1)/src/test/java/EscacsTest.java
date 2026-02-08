package src.test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EscacsTest {

    // --- TESTS DEL PEÓ (Mínim 6) ---
    @Test
    void testPeoBlancMouEndavant() {
        // Simulem moviment A2-A3 (fila 7 a 6)
        assertTrue(Validador.esValidPeo(7, 1, 6, 1, " P ", " · "));
    }

    @Test
    void testPeoBlancNoMouEnrere() {
        assertFalse(Validador.esValidPeo(6, 1, 7, 1, " P ", " · "));
    }

    @Test
    void testPeoNegreMouEndavant() {
        // Simulem moviment A7-A6 (fila 2 a 3)
        assertTrue(Validador.esValidPeo(2, 1, 3, 1, " p ", " · "));
    }

    @Test
    void testPeoMenjaDiagonal() {
        assertTrue(Validador.esValidPeo(7, 2, 6, 1, " P ", " p "));
    }

    @Test
    void testPeoNoMouDiagonalSiBuit() {
        assertFalse(Validador.esValidPeo(7, 2, 6, 1, " P ", " · "));
    }

    @Test
    void testPeoNoSaltaPeça() {
        assertFalse(Validador.esValidPeo(7, 1, 6, 1, " P ", " T "));
    }

    // --- TESTS DEL CAVALL (Mínim 6) ---
    @Test
    void testCavallMovimentL1() { assertTrue(Validador.esValidCaball(8, 2, 6, 3, " C ")); }
    @Test
    void testCavallMovimentL2() { assertTrue(Validador.esValidCaball(8, 2, 6, 1, " C ")); }
    @Test
    void testCavallMovimentL3() { assertTrue(Validador.esValidCaball(5, 5, 3, 4, " C ")); }
    @Test
    void testCavallNoMouRecte() { assertFalse(Validador.esValidCaball(8, 2, 5, 2, " C ")); }
    @Test
    void testCavallNoMouDiagonal() { assertFalse(Validador.esValidCaball(8, 2, 6, 4, " C ")); }
    @Test
    void testCavallNoSurtDelTauler() { assertFalse(Validador.esValidCaball(1, 1, -1, 2, " c ")); }
}