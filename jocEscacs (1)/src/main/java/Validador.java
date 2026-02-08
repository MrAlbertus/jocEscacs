package src.main.java;

public class Validador {

    // Lògica per als Peons (Mínim 6 tests en la rúbrica)
    public static boolean esValidPeo(int fO, int cO, int fD, int cD, String peo, String destino) {
        if (peo.equals(" P ")) { // Blanques puja (fila disminueix)
            if (fD == fO - 1 && cD == cO && destino.equals(" · ")) return true;
            if (fD == fO - 1 && Math.abs(cD - cO) == 1 && !destino.equals(" · ")) return true;
        } else if (peo.equals(" p ")) { // Negres baixa (fila augmenta)
            if (fD == fO + 1 && cD == cO && destino.equals(" · ")) return true;
            if (fD == fO + 1 && Math.abs(cD - cO) == 1 && !destino.equals(" · ")) return true;
        }
        return false;
    }

    // Lògica per als Cavalls (Mínim 6 tests en la rúbrica)
    public static boolean esValidCaball(int fO, int cO, int fD, int cD) {
        int diffFila = Math.abs(fD - fO);
        int diffCol = Math.abs(cD - cO);
        // Moviment en L: (2 i 1) o (1 i 2)
        return (diffFila == 2 && diffCol == 1) || (diffFila == 1 && diffCol == 2);
    }
}