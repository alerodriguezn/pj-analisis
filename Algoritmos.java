import java.util.ArrayList;
import java.util.Arrays;

/*
 * Fecha Inicio: 12 de Marzo del 2023
 * Fecha Ultima Modificación: 5 de abril del 2023
 */
public class Algoritmos {

    public static int a = 0; // Asignaciones
    public static int c = 0; // Comparaciones

    /*
     * Algoritmo de fuerza bruta
     * Recibe el texto y el patrón a buscar
     */
    public static ArrayList<Integer> algoritmoFuerzaBruta(String texto, String patron) {
        ArrayList<Integer> posiciones = new ArrayList<>(); // Almacena las posiciones donde se encuentra el patrón
        int n = texto.length(); // Tamaño del texto
        int m = patron.length(); // Tamaño del patrón
        a += 3;
        for (int i = 0; i <= n - m; i++) { // Comienza a buscar el patron dentro del texto mediante un bucle for
            c += 2;
            a += 2;
            int j;
            for (j = 0; j < m; j++) {  // Recorre cada caracter del patron
                a += 2;
                c += 2;
                if (texto.charAt(i + j) != patron.charAt(j)) {
                    break;
                }
            }
            c++;
            if (j == m) {
                posiciones.add(i); // Agrega la posicion donde se encuentra el patron
            }
        }
        c++;
        return posiciones;
    }

    /*
     * Algoritmo de Knuth-Morris-Pratt
     * Recibe el texto y el patrón a buscar
     *
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
        while (i < n) { //recorrido de todo el texto
            c++;
            if (texto.charAt(i) == patron.charAt(j)) { // Comparacion de los caracteres
                c++;
                i++;
                j++;
                a += 2;
                if (j == m) { // Compara para ver si se llego al final del patron
                    c++;
                    posiciones.add(i - j); // Se agrega la posicion
                    j = pi[j - 1]; // se actualiza el índice de recorrido del patrón con el valor de pi[j-1]
                    a++;
                }
            } else { // si lo caracteres son diferentes
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
    /*
     * Preprocesamiento del patrón
     * Recibe el patrón a buscar
     */
    private static int[] preprocesarPatron(String patron) {
        int m = patron.length();
        int[] pi = new int[m]; // se almacena el "prefijo sufijo más largo" de cada posición del "patron".
        pi[0] = 0;
        int j = 0;
        int i = 1;
        a += 5;
        // Se itera a través del patrón para preprocesarlo
        while (i < m) { 
            c++;
            // Si los caracteres en la posición i y j son iguales, incrementamos j y almacenamos su valor en pi[i]
            if (patron.charAt(i) == patron.charAt(j)) {
                c++;
                j++;
                pi[i] = j;
                i++;
                a += 3;
            } else { // Si no son iguales
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
         // Devolvemos el array pi preprocesado
        return pi;
    }

    /*
     * Algoritmo de Boyer-Moore
     * Recibe el texto y el patrón a buscar
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
        while (i < n) { // Recorrido del texto
            c++;
            int k = i;
            int j = m - 1;
            a += 2;
            while (j >= 0 && texto.charAt(k) == patron.charAt(j)) { // comparacion de los caracteres
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

    /*
     * Crear tabla de saltos
     * Recibe el patrón a buscar
     */
    private static int[] crearTablaSalto(String patron) {
        int m = patron.length();
        int[] tablaSalto = new int[256];
        a += 2;
        Arrays.fill(tablaSalto, m);
        a++;
        //Se genera una tabla de salto que se utiliza para mover el índice en la búsqueda del patrón en el texto.
        for (int i = 0; i < m - 1; i++) { // Se recorre el patrón
            c++;
            tablaSalto[patron.charAt(i)] = m - i - 1;
            a++;
        }
        c++; // Comparacion falsa
        return tablaSalto;
    }

}
