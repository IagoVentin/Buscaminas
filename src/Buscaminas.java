import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Buscaminas, conten a loxica do programa
 */
public class Buscaminas {
    static Scanner scan = new Scanner(System.in);
    private static int[][] xogoMinas = new int[6][6];       //Array que conten as minas
    private static int[][] xogoTaboleiro = new int[6][6];   //Array que se mostra o xogador

    private int x, y;

    /**
     * Imprime por pantalla o taboleiro
     */
    private void mostrarXogo(int[][] xogoTaboleiro) {
        //Primera liena de la tabla
        System.out.print("\t");
        for (int i = 0; i < 6; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.println();
        System.out.print("\t");
        for (int j = 0; j < 6; j++) {
            System.out.print("─  ");
        }

        //Primera columna de la tabla
        System.out.println();
        for (int k = 0; k < xogoTaboleiro.length; k++) {
            System.out.print(k + 1 + " | ");

            //Contenido de la tabla
            for (int l = 0; l < xogoTaboleiro[k].length; l++) {
                System.out.print(xogoTaboleiro[k][l] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * Asina minas en posicions aleatorias a "xogoMinas"
     */
    private void meterMinas(int[][] xogoMinas, int numeroMinas) {

        int max_val = 6;
        Random ran = new Random();

        for (int i = 0; i < numeroMinas; i++) {
            int j = ran.nextInt(max_val);
            int k = ran.nextInt(max_val);
            xogoMinas[j][k] = 1;
        }
    }

    /**
     * Enche os dous Arrays con 0
     */
    private void resetearTaboleiro(int[][] xogoMinas, int[][] xogoTaboleiro) {
        for (int[] xogoMina : xogoMinas) {
            Arrays.fill(xogoMina, 0);
            System.out.println();
        }

        for (int[] ints : xogoTaboleiro) {
            Arrays.fill(ints, 0);
            System.out.println();
        }
    }

    /**
     * Cambia o valor 0 por un 2 se non hay unha mina, e remata o xogo se hay unha
     */
    private int destapar() {

        int resultado;
        boolean x_vali = false;
        boolean y_vali = false;
        boolean repetido = false;

        do {
            //Pide unha linea e comproba se e valida
            System.out.println("Inserta a linea desexada: ");
            do {
                x = scan.nextInt();
                if ((x >= 1) && (x <= 6)) {
                    x = x - 1;
                    x_vali = true;
                } else {
                    System.out.println("Error, inserta unha linea valida ");
                }
            } while (!x_vali);

            //Pide unha columna e comproba se e valida
            System.out.println("Inserta a columna desexada: ");
            do {
                y = scan.nextInt();
                if ((y >= 1) && (y <= 6)) {
                    y = y - 1;
                    y_vali = true;
                } else {
                    System.out.println("Error, inserta unha columna valida ");
                }
            } while (!y_vali);

            //Comprobar posicion repetida
            if (xogoTaboleiro[x][y] == 2) {
                repetido = true;
                System.out.println("Posicion repetida, proba de novo");
            }
        } while (repetido);

        //Comproba se nesa posicion hai unha mina
        if (xogoMinas[x][y] == 1) {
            resultado = 1;
        } else {
            resultado = 0;
        }
        return resultado;
    }

    /**
     * Comeza o xogo, segun o nivel selesccionado engadira un numero de minas determinado
     */
    private void comezarNivel(int numeroMinas) {
        /*
        //Descomentar para comprobar onde estan as minas
        mostrarXogo(xogoMinas);
        System.out.println("=======================");
        */
        meterMinas(xogoMinas, numeroMinas);

        mostrarXogo(xogoTaboleiro);

        boolean mina_estoupou = false;
        for (int i = 0; i < 10; i++) {
            int crt_dstp;   //Caracter Destapado
            crt_dstp = destapar();
            if (crt_dstp == 0) {
                xogoTaboleiro[x][y] = 2;
                System.out.println("============");
                System.out.println("O xogo segue");
                System.out.println("============");

                /*
                //Descomentar para comprobar onde estan as minas
                mostrarXogo(xogoMinas);
                System.out.println("=======================");
                */

                mostrarXogo(xogoTaboleiro);

            } else if (crt_dstp == 1) {
                mina_estoupou = true;
            }

            if (mina_estoupou) {
                System.out.println("==================");
                System.out.println("Estoupou unha mina");
                System.out.println("==================");
                break;
            }
        }

        if (!mina_estoupou) {
            System.out.println("=====================");
            System.out.println("Felicidades, ganaches!!!");
            System.out.println("=====================");
        }

        resetearTaboleiro(xogoMinas, xogoTaboleiro);
    }

    /**
     * Comeza o xogo
     */
    private void nivel4(int numeroMinas) {
        meterMinas(xogoMinas, numeroMinas);

        /*
        //Descomentar para comprobar onde estan as minas
        mostrarXogo(xogoMinas);
        System.out.println("=======================");
        */

        mostrarXogo(xogoTaboleiro);

        int intentos = 5;

        boolean mina_estoupou = true;
        for (int i = 0; i < numeroMinas; i++) {
            int crt_dstp;
            crt_dstp = destapar();
            if (crt_dstp == 1) {
                xogoTaboleiro[x][y] = 1;
                System.out.println("============");
                System.out.println("O xogo segue");
                System.out.println("============");
                
                /*
                //Descomentar para comprobar onde estan as minas
                mostrarXogo(xogoMinas);
                System.out.println("=======================");
                */
                mostrarXogo(xogoTaboleiro);

            } else {
                intentos--;
                System.out.println("Non estoupou ninhuna mina, quedanche " + intentos + " intentos");
                if (intentos == 0) {
                    mina_estoupou = false;
                    break;
                }
            }
        }

        if (!mina_estoupou) {
            System.out.println("=====================");
            System.out.println("Non che quedan intentos");
            System.out.println("=====================");
        } else {
            System.out.println("==================");
            System.out.println("Felicidades, ganaches");
            System.out.println("==================");
        }

        resetearTaboleiro(xogoMinas, xogoTaboleiro);
    }

    /**
     * Menu principal da aplicacion
     */
    public void xogar() {

        boolean sair = false;
        int opcion;

        while (!sair) {
            System.out.println(
                    "    ____  __  _______ _________    __  ________   _____   _____\n" +
                            "   / __ )/ / / / ___// ____/   |  /  |/  /  _/ | / /   | / ___/\n" +
                            "  / __  / / / /\\__ \\/ /   / /| | / /|_/ // //  |/ / /| | \\__ \\ \n" +
                            " / /_/ / /_/ /___/ / /___/ ___ |/ /  / // // /|  / ___ |___/ / \n" +
                            "/_____/\\____//____/\\____/_/  |_/_/  /_/___/_/ |_/_/  |_/____/ ");
            System.out.println("===============================================================");
            System.out.println("\t\t\t\t\t1.- Nivel 1 [10 minas]");
            System.out.println("\t\t\t\t\t2.- Nivel 2 [15 minas]");
            System.out.println("\t\t\t\t\t3.- Nivel 3 [20 minas]");
            System.out.println("\t\t\t\t\t4.- Atopa todas as minas [10 minas]");
            System.out.println("\t\t\t\t\t5.- Sair");
            System.out.println("===============================================================");
            System.out.println("Selecciona unha opcion: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Seleccionaches a opcion 1");

                    comezarNivel(10);
                    break;
                case 2:
                    System.out.println("Seleccionaches a opcion 2");
                    comezarNivel(15);
                    break;
                case 3:
                    System.out.println("Seleccionaches a opcion 3");
                    comezarNivel(20);
                    break;
                case 4:
                    System.out.println("Seleccionaches a opcion 4");
                    nivel4(10);
                    break;
                case 5:
                    sair = true;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("So números entre 1 e 4");
            }
        }
    }

}

