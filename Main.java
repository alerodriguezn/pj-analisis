import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
            System.out.println("El patrón se encontró en las siguientes posiciones: "+posiciones.toString());
        }
        System.out.println("Cantidad de Asignaciones: " + Algoritmos.a);
        Algoritmos.a = 0;
    }

    public static void main(String[] args) {

        // Texto y patrón de prueba
        String texto = "AABAACAADAABAABA";
        String patron = "AABA";

        //Texto con base a los archivos (prueba)
        String rutaArchivo = "texto1.txt"; 
        File archivo = new File(rutaArchivo);
        long tamanoBytes = archivo.length();

        //Lectura del texto del archivo
        Scanner scanner;
        String contenido = ""; //Tendrá el texto del archivo
        if (archivo.exists() && archivo.canRead()) { //verifica si el archivo existe y si el programa tiene permiso para leerlo
            try {
                scanner = new Scanner(archivo);
                while (scanner.hasNextLine()) {
                    contenido += scanner.nextLine();
                }    
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Surgió un error al intentar leer el archivo");
            }
        } else {
            System.out.println("El archivo no existe o no se puede leer");
        }


        //Establezca el texto basado en el texto del archivo
        texto = contenido;
        

        System.out.println("El tamaño del archivo es de " + tamanoBytes + " bytes.");

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