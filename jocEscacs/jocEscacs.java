package jocEscacs;

import java.util.Scanner;

/**
 * Valida si el peó es pot moure a la destinació seleccionada.
 * "tauler" Matriu del joc.
 * "return" true si el moviment és legal, false altrament.
 */

public class jocEscacs {

    Scanner Scanner1 = new Scanner(System.in); // IMPORTEM L'ESCANNER

    int colOrigen;
    int colDestino;
    int filaOrigen;
    int filaDestino;
    boolean esPot = false;

    boolean haGuanyat;

    int torn;

    public static void main(String[] args) {
        jocEscacs p = new jocEscacs();
        p.main2();
    }

    public void main2() {

        String jugador1 = "jugador1";
        String jugador2 = "jugador2";

        haGuanyat = false;
        torn = 0;

        String[][] tablero = new String[9][9];

        String menu = "m"; // PRIMER MENU

        do {
            System.out.println("--------------------------------");
            System.out.println("a) - Iniciar joc");
            System.out.println("b) - Llegir Normes d'escacs");
            System.out.println("c) - Sortir");
            System.out.println("--------------------------------");

        menu = Scanner1.next();

            switch (menu) {
                case "a":

                //  INICIAR JOC
                    System.out.println("> Introdueix el nom del Jugador 1 : ");
                    jugador1 = Scanner1.next();
                    System.out.println("> Introdueix el nom del Jugador 2 : ");
                    jugador2 = Scanner1.next();
                //  METODO

                    crearTablero(tablero);
                    verTablero(tablero);
                    //jugar();

                    torn = 0;
                    do {
                        if (torn % 2 == 0) {
                            System.out.println("Li toca a " + jugador1 + " (Blanques - 'P')");
                        } else {
                            System.out.println("Li toca a " + jugador2 + " (Negres - 'p')");
                        }
                        moverFicha(tablero);
                        comprobarReis(tablero);
                        torn++;

                    } while (!haGuanyat);

                    break;

                case "b":
                    System.out.println("1.-Las Blancas mouen primer");
                    break;

                default:
                    break;
            }
        } while (!(menu.equals("c")));
    }

    public void llenarTablero(String[][] tauler) {
        
        // LETRAS HORIZONTAL
        tauler[0][0]="   ";
        tauler[0][1]=" A ";
        tauler[0][2]=" B ";
        tauler[0][3]=" C ";
        tauler[0][4]=" D ";
        tauler[0][5]=" E ";
        tauler[0][6]=" F ";
        tauler[0][7]=" G ";
        tauler[0][8]=" H ";

        // NUMEROS VERTICAL
        tauler[1][0] =" 1 ";
        tauler[2][0] =" 2 ";
        tauler[3][0] =" 3 ";
        tauler[4][0] =" 4 ";
        tauler[5][0] =" 5 ";
        tauler[6][0] =" 6 ";
        tauler[7][0] =" 7 ";
        tauler[8][0] =" 8 ";

        // TORRES NEGRAS
        tauler[1][1] = " t ";
        tauler[1][8] = " t ";

        // TORRES BLANCAS
        tauler[8][1] = " T ";
        tauler[8][8] = " T ";

        // CABALLOS NEGROS
        tauler[1][2] = " c ";
        tauler[1][7] = " c ";

        // CABALLOS BLANCOS
        tauler[8][2] = " C ";
        tauler[8][7] = " C ";

        // ALFILES NEGROS
        tauler[1][3] = " a ";
        tauler[1][6] = " a ";

        // ALFILES BLANCOS
        tauler[8][3] = " A ";
        tauler[8][6] = " A ";

        //  REINA NEGRA
        tauler[1][4] = " q ";

        //  REINA BLANCA
        tauler[8][4] = " Q ";

        //  REY NEGRO
        tauler[1][5] = " r ";

        //  REY BLANCO
        tauler[8][5] = " R ";

        //  PEONES NEGROS
        tauler[2][1] = " p ";
        tauler[2][2] = " p ";
        tauler[2][3] = " p ";
        tauler[2][4] = " p ";
        tauler[2][5] = " p ";
        tauler[2][6] = " p ";
        tauler[2][7] = " p ";
        tauler[2][8] = " p ";

        // PEONES BLANCOS
        tauler[7][1] = " P ";
        tauler[7][2] = " P ";
        tauler[7][3] = " P ";
        tauler[7][4] = " P ";
        tauler[7][5] = " P ";
        tauler[7][6] = " P ";
        tauler[7][7] = " P ";
        tauler[7][8] = " P ";

    }

    public void crearTablero(String[][] tablero) {

    for (int x = 1; x < 9; x++) {
        for (int y = 1; y < 9; y++) {
            tablero[x][y] = " · ";
            
        }
    }
    llenarTablero(tablero);
    }

    public void verTablero(String[][] tablero){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(tablero[x][y]!=null){
                    System.out.print(tablero[x][y]);
                }
            }
            System.out.println();
        }
    }
    
    public void moverFicha(String[][] tauler) {
        try {
            System.out.println("Introdueix el moviment (ej:A2-A3): ");
        String inmov = Scanner1.next();
        String[] moviment = inmov.split("-");

        String origen = moviment[0].toUpperCase();
        String desti = moviment[1].toUpperCase();

        colOrigen = origen.charAt(0) - 'A' + 1;
        filaOrigen = origen.charAt(1) - '0';


        colDestino = desti.charAt(0) - 'A' + 1;
        filaDestino = desti.charAt(1) - '0';
        } catch (Exception e) {
            torn--;
        }
        

        try {
            validarMoviment(tauler);
        } catch (Exception e) {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);
        }
    }

    public void validarMoviment(String[][] tauler) { // AQUI VALIDEM TOTS ELS MOVIMENTS
        
        if (tauler[filaOrigen][colOrigen] == " p " || tauler[filaOrigen][colOrigen] == " P " ) {
            validarMovimentPeo(tauler);
        }
        else if (tauler[filaOrigen][colOrigen] == " c " || tauler[filaOrigen][colOrigen] == " C ") {
            validarMovimentCaball(tauler);
        }
        else if (tauler[filaOrigen][colOrigen] == " t " || tauler[filaOrigen][colOrigen] == " T ") {
            validarMovimentTorre(tauler);
        }
        else if (tauler[filaOrigen][colOrigen].equals(" r ") || tauler[filaOrigen][colOrigen].equals(" R ")) {
            validarMovimentRey(tauler);
        }
        else if (tauler[filaOrigen][colOrigen].equals(" a ") || tauler[filaOrigen][colOrigen].equals(" A ")) {
            validarMovimentAlfil(tauler);
        }
        else if (tauler[filaOrigen][colOrigen].equals(" q ") || tauler[filaOrigen][colOrigen].equals(" Q ")) {
            validarMovimentReina(tauler);
}
    }

    public void validarMovimentPeo(String[][] tauler) {
        String peo = tauler[filaOrigen][colOrigen];
        String destino = tauler[filaDestino][colDestino];
        esPot = false;
        
        
        if (peo.equals(" P ")) {

            if (filaDestino == filaOrigen -1 && colDestino == colOrigen -1 && !destino.equals(" · ")) {
                esPot = true;
            } else if (filaDestino == filaOrigen -1 && colDestino == colOrigen + 1 && !destino.equals(" · ")) {
                esPot = true;
            }

            if (filaDestino == filaOrigen - 1 && colDestino == colOrigen && destino.equals(" · ")) {
                esPot = true;
            }

        } else {
            if (peo.equals(" p ")) {

            if (filaDestino == filaOrigen +1 && colDestino == colOrigen +1 && !destino.equals(" · ")) {
                esPot = true;
            } else if (filaDestino == filaOrigen +1 && colDestino == colOrigen - 1 && !destino.equals(" · ")) {
                esPot = true;
            }

            if (filaDestino == filaOrigen +1 && colDestino == colOrigen && destino.equals(" · ")) {
                esPot = true;
            }
        }
        }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = peo;
            tauler[filaOrigen][colOrigen] = " · ";
            System.out.println("> Moviment fet!");
            verTablero(tauler);
        } else {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);
        }


    }
    public void validarMovimentCaball(String[][] tauler){
        String caballo = tauler[filaOrigen][colOrigen];
        String destino = tauler[filaDestino][colDestino];

            if (caballo.equals(" C ")) {
                if (filaDestino == filaOrigen + 2 && colDestino == colOrigen + 1) esPot = true;
                else if (filaDestino == filaOrigen + 2 && colDestino == colOrigen - 1){
                    esPot = true;
                } else if (filaDestino == filaOrigen - 2 && colDestino == colOrigen + 1) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 2 && colDestino == colOrigen - 1) {
                    esPot = true;
                } else if (filaDestino == filaOrigen + 1 && colDestino == colOrigen + 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen + 1 && colDestino == colOrigen - 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen + 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen - 2) {
                    esPot = true;
                }
            
            } else if (caballo.equals(" c ")) {
                if (filaDestino == filaOrigen + 2 && colDestino == colOrigen + 1) esPot = true;
                else if (filaDestino == filaOrigen + 2 && colDestino == colOrigen - 1){
                    esPot = true;
                } else if (filaDestino == filaOrigen - 2 && colDestino == colOrigen + 1) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 2 && colDestino == colOrigen - 1) {
                    esPot = true;
                } else if (filaDestino == filaOrigen + 1 && colDestino == colOrigen + 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen + 1 && colDestino == colOrigen - 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen + 2) {
                    esPot = true;
                } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen - 2) {
                    esPot = true;
                }
            }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = caballo;
            tauler[filaOrigen][colOrigen] = " · ";
            System.out.println("> Moviment fet!");
            verTablero(tauler);
        } else {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);

    }
    }
    public void validarMovimentTorre(String[][] tauler){
        String torre = tauler[filaOrigen][colOrigen];
        String desti = tauler[filaDestino][colDestino];
        esPot = false;

        if (torre.equals(" t ")) {
            if (colDestino == colOrigen && filaOrigen != filaDestino) {
                esPot = true;
            } else if (colDestino != colOrigen && filaOrigen == filaDestino) {
                esPot = true;
            }
        } else if (torre.equals(" T ")) {
            if (colDestino == colOrigen && filaOrigen != filaDestino) {
                esPot = true;
            } else if (colDestino != colOrigen && filaOrigen == filaDestino) {
                esPot = true;
            }
        }

        if (esPot == true) {
            if (torre.equals(" T ") && (desti.equals(" T ") || desti.equals(" C ") || desti.equals(" A ") || desti.equals(" Q ") || desti.equals(" R ") || desti.equals(" P "))) {
                esPot = false;
                System.out.println("No pots menjar la teva peça!");
            } else if (torre.equals(" t ") && (desti.equals(" t ") || desti.equals(" c ") || desti.equals(" a ") || desti.equals(" q ") || desti.equals(" r ") || desti.equals(" p "))) {
                esPot = false;
                System.out.println("No pots menjar la teva peça!");
            }
        }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = torre;
            tauler[filaOrigen][colOrigen] = " · ";
            System.out.println("> Moviment fet!");
            verTablero(tauler);
        } else {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);
        }


    }
    public void validarMovimentRey(String[][] tauler){
        String rey = tauler[filaOrigen][colOrigen];
        String desti = tauler[filaDestino][colDestino];

        if (filaDestino == filaOrigen + 1 && colDestino == colOrigen) {
            esPot = true;
        } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen) {
            esPot = true;
        } else if (filaDestino == filaOrigen && colDestino == colOrigen + 1) {
            esPot = true;
        } else if (filaDestino == filaOrigen && colDestino == colOrigen - 1) {
            esPot = true;
        } else if (filaDestino == filaOrigen +1 && colDestino == colOrigen +1) {
            esPot = true;
        } else if (filaDestino == filaOrigen + 1 && colDestino == colOrigen -1) {
            esPot = true;
        } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen +1) {
            esPot = true;
        } else if (filaDestino == filaOrigen - 1 && colDestino == colOrigen -1) {
            esPot = true;
        }

        if (esPot == true) {
            if (rey.equals(" R ")) {
                if (desti.equals(" T ") || desti.equals(" C ") || desti.equals(" A ") || desti.equals(" Q ") || desti.equals(" R ") || desti.equals(" P ")) {
                    esPot = false;
                    System.out.println("No pots menjar la teva peça!");
                }
            } else if (rey.equals(" r ")) {
                if (desti.equals(" t ") || desti.equals(" c ") || desti.equals(" a ") || desti.equals(" q ") || desti.equals(" r ") || desti.equals(" p ")) {
                    esPot = false;
                    System.out.println("No pots menjar la teva peça!");
                }
            }
        }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = rey;
            tauler[filaOrigen][colOrigen] = " · ";
            System.out.println("> Moviment fet!");
            verTablero(tauler);
        } else {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);
        }


    }
    public void validarMovimentAlfil(String[][] tauler) {
        String alfil = tauler[filaOrigen][colOrigen];
        String desti = tauler[filaDestino][colDestino];
        esPot = false;

        int restaFila = filaDestino - filaOrigen;
        int restaCol = colDestino - colOrigen;

        if (restaFila == restaCol) {
            esPot = true;
        } else if (restaFila == -restaCol) { 
            esPot = true;
        } else if (-restaFila == restaCol) {
            esPot = true;
        }


        if (esPot == true) {
            if (alfil.equals(" A ")) {
                if (desti.equals(" T ") || desti.equals(" C ") || desti.equals(" A ") || 
                    desti.equals(" Q ") || desti.equals(" R ") || desti.equals(" P ")) {
                    esPot = false;
                }
            } else if (alfil.equals(" a ")) { 
                if (desti.equals(" t ") || desti.equals(" c ") || desti.equals(" a ") || 
                    desti.equals(" q ") || desti.equals(" r ") || desti.equals(" p ")) {
                    esPot = false;
                }
            }
        }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = alfil;
            tauler[filaOrigen][colOrigen] = " · ";
            System.out.println("> Moviment fet!");
            verTablero(tauler);
        } else {
            System.out.println("Moviment NO realitzat :(");
            torn--;
            verTablero(tauler);
        }

    }
    
    public void validarMovimentReina(String[][] tauler){
        String reina = tauler[filaOrigen][colOrigen];
        String desti = tauler[filaDestino][colDestino];
        esPot = false;

        int restaFila = filaDestino - filaOrigen;
        int restaCol = colDestino - colOrigen;

        if (colDestino == colOrigen || filaDestino == filaOrigen) {
            esPot = true;
        } 
        else if (restaFila == restaCol || restaFila == -restaCol || -restaFila == restaCol) {
            esPot = true;
        }

        if (esPot == true) {
            if (reina.equals(" Q ")) {
                if (desti.equals(" T ") || desti.equals(" C ") || desti.equals(" A ") || 
                    desti.equals(" Q ") || desti.equals(" R ") || desti.equals(" P ")) {
                    esPot = false;
                }
            } else if (reina.equals(" q ")) {
                if (desti.equals(" t ") || desti.equals(" c ") || desti.equals(" a ") || 
                    desti.equals(" q ") || desti.equals(" r ") || desti.equals(" p ")) {
                    esPot = false;
                }
            }
        }

        if (esPot == true) {
            tauler[filaDestino][colDestino] = reina;
                tauler[filaOrigen][colOrigen] = " · ";
                System.out.println("> Moviment fet!");
                verTablero(tauler);
            } else {
                System.out.println("Moviment NO realitzat :(");
                torn--;
                verTablero(tauler);
            }

    }

    public void comprobarReis(String[][] tauler) {
        int reisTrobats = 0;

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                if (tauler[x][y].equals(" R ") || tauler[x][y].equals(" r ")) {
                    reisTrobats++;
                }
            }
        }

        
        if (reisTrobats < 2) {
            haGuanyat = true;
            System.out.println("----------------------------------------");
            System.out.println("-------ESCAC I MAT, S'HA ACABAT!---------");
            System.out.println("----------------------------------------");
        }
}


}


