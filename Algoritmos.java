import java.util.ArrayList;
import java.util.Arrays;

public class Algoritmos {

    public static int a = 0; // Asignaciones
    public static int c = 0; // Comparaciones

    /*
     * Algoritmo de fuerza bruta
     */
    public static ArrayList<Integer> algoritmoFuerzaBruta(String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        int n = texto.length();
        int m = patron.length();
        a += 3;
        for (int i = 0; i <= n - m; i++) {
            c += 2;
            a += 2;
            int j;
            for (j = 0; j < m; j++) {
                a += 2;
                c += 2;
                if (texto.charAt(i + j) != patron.charAt(j)) {
                    break;
                }
            }
            c++;
            if (j == m) {
                posiciones.add(i);
            }
        }
        c++;
        return posiciones;
    }

    /*
     * Algoritmo de Knuth-Morris-Pratt
     */
    public static ArrayList<Integer> algoritmoKnuthMorrisPratt(String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>();
        int n = texto.length();
        int m = patron.length();
        a += 3;
        int[] pi = preprocesarPatron(patron); // Se suman las asignaciones de pi y el argumento de la funcion                                // preprocesarPatron
        a += 2;
        int i = 0;
        int j = 0;
        a += 2;
        while (i < n) {
            c++;
            if (texto.charAt(i) == patron.charAt(j)) {
                c++;
                i++;
                j++;
                a += 2;
                if (j == m) {
                    c++;
                    posiciones.add(i - j);
                    j = pi[j - 1];
                    a++;
                }
            } else {
                if (j == 0) {
                    c++;
                    i++;
                    a++;
                } else {
                    j = pi[j - 1];
                    a++;
                }
            }
        }
        c++;
        return posiciones;
    }
    private static int[] preprocesarPatron(String patron) {
        int m = patron.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int j = 0;
        int i = 1;
        a += 5;
        while (i < m) {
            c++;
            if (patron.charAt(i) == patron.charAt(j)) {
                c++;
                j++;
                pi[i] = j;
                i++;
                a += 3;
            } else {
                if (j == 0) {
                    c++;
                    pi[i] = 0;
                    i++;
                    a += 2;
                } else {
                    j = pi[j - 1];
                    a++;
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
        int[] tablaSalto = crearTablaSalto(patron); // Se suman las asignaciones de tablaSalto y el argumento de la
                                                    // funcion crearTablaSalto
        a += 3;
        int n = texto.length();
        int m = patron.length();
        int i = m - 1;
        a += 3;
        while (i < n) {
            c++;
            int k = i;
            int j = m - 1;
            a += 2;
            while (j >= 0 && texto.charAt(k) == patron.charAt(j)) {
                c += 2;
                j--;
                k--;
                a += 2;
            }
            c++;// Como falsa del while
            if (j == -1) {
                c++;
                posiciones.add(k + 1);
                i++;
                a++;
            } else {
                i += Math.max(tablaSalto[texto.charAt(i)], m - j - 1);
                a++;
            }
        }
        c++;// Comparacion Falsa del While
        return posiciones;
    }

    private static int[] crearTablaSalto(String patron) {
        int m = patron.length();
        int[] tablaSalto = new int[256];
        a += 2;
        Arrays.fill(tablaSalto, m);
        a++;
        for (int i = 0; i < m - 1; i++) {
            c++;
            tablaSalto[patron.charAt(i)] = m - i - 1;
            a++;
        }
        c++; // Comparacion falsa
        return tablaSalto;
    }

}
