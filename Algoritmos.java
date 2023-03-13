import java.util.ArrayList;
import java.util.Arrays;

public class Algoritmos {

    /*
     * Algoritmo de fuerza bruta
     */
    public static ArrayList<Integer> algoritmoFuerzaBruta(String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        int n = texto.length();
        int m = patron.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (texto.charAt(i + j) != patron.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                posiciones.add(i);
            }
        }
        return posiciones;
    }

    /*
     * Algoritmo de Knuth-Morris-Pratt
     */
    public static ArrayList<Integer> algoritmoKnuthMorrisPratt (String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        int n = texto.length();
        int m = patron.length();
        int[] pi = preprocesarPatron(patron);

        int i = 0;
        int j = 0;
        while (i < n) {
            if (texto.charAt(i) == patron.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    posiciones.add(i - j);
                    j = pi[j - 1];
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = pi[j - 1];
                }
            }
        }
        return posiciones;
    }

    private static int[] preprocesarPatron(String patron) {
        int m = patron.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int j = 0;
        int i = 1;
        while (i < m) {
            if (patron.charAt(i) == patron.charAt(j)) {
                j++;
                pi[i] = j;
                i++;
            } else {
                if (j == 0) {
                    pi[i] = 0;
                    i++;
                } else {
                    j = pi[j - 1];
                }
            }
        }
        return pi;
    }


    /*
     * Algoritmo de Boyer-Moore
     */
    public static ArrayList<Integer> algoritmoBoyerMoore(String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        int n = texto.length();
        int m = patron.length();
        int[] tablaSalto = crearTablaSalto(patron);

        int i = m - 1;
        int j = m - 1;
        while (i < n) {
            if (texto.charAt(i) == patron.charAt(j)) {
                if (j == 0) {
                    posiciones.add(i);
                    i += m;
                    j = m - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                i += tablaSalto[texto.charAt(i)];
                j = m - 1;
            }
        }
        return posiciones;
    }

    private static int[] crearTablaSalto(String patron) {
        int m = patron.length();
        int[] tablaSalto = new int[256];
        Arrays.fill(tablaSalto, m);
        for (int i = 0; i < m - 1; i++) {
            tablaSalto[patron.charAt(i)] = m - i - 1;
        }
        return tablaSalto;
    }

}
