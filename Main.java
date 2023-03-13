import java.util.ArrayList;

public class Main{

    /*
     * Imprime el resultado del algoritmo
     */
    public static void imprimirResultado(ArrayList<Integer> posiciones, String nombreAlgoritmo) {
        System.out.println("-------------------------------------");
        System.out.println("Nombre del algoritmo: " + nombreAlgoritmo);
        if (posiciones.size() == 0) {
            System.out.println("El patrón no se encontró en el texto.");
        } else {
            System.out.println("El patrón se encontró en las siguientes posiciones:");
            for (int i = 0; i < posiciones.size(); i++) {
                System.out.println(posiciones.get(i));
            }
        }
    }

    public static void main(String[] args) {

        // Texto y patrón de prueba
        String texto = "AABAACAADAABAABA";
        String patron = "AABA";

        // Algoritmo Fuerza Bruta
        ArrayList<Integer> posicionesFB = Algoritmos.algoritmoFuerzaBruta(texto, patron);
        imprimirResultado(posicionesFB, "Fuerza bruta");

        // Algoritmo Knuth-Morris-Pratt
        ArrayList<Integer> posicionesKMP = Algoritmos.algoritmoKnuthMorrisPratt(texto, patron);
        imprimirResultado(posicionesKMP, "Knuth-Morris-Pratt");
        
        // Algoritmo Boyer-Moore
        ArrayList<Integer> posicionesBM = Algoritmos.algoritmoBoyerMoore(texto, patron);
        imprimirResultado(posicionesBM, "Boyer-Moore");
        

    }
}