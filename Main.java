import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    /*
     * Imprime el resultado del algoritmo
     */
    public static void imprimirResultado(ArrayList<Integer> posiciones, String nombreAlgoritmo, String patron) {
        System.out.println("-------------------------------------");
        System.out.println("Nombre del algoritmo: " + nombreAlgoritmo);
        System.out.println("Patrón: " + patron);	
        if (posiciones.size() == 0) {
            System.out.println("El patrón no se encontró en el texto.");
        } else {
            System.out.println("El patrón se encontró en las siguientes posiciones: " + posiciones.toString());
        }

        // Almacena los resultados en un archivo de texto 
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("archivo.txt", true)));
            String linea = nombreAlgoritmo + "," + patron + "," + Algoritmos.a + "," + Algoritmos.c;
            writer.println(linea);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de texto plano: " + e.getMessage());
        }
    

        System.out.println("Cantidad de Asignaciones: " + Algoritmos.a);
        Algoritmos.a = 0;
        System.out.println("Cantidad de Comparaciones: " + Algoritmos.c);
        Algoritmos.c = 0;
    }

    public static void main(String[] args) {

        String[] patrones = { "ABCDEF", "ABCDEFGHIJKLNMOPQRSTOVXYZ", "ZZZZZ", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "ABABABA", "ABABABABABABABABABABABABA" };

        for (int i = 1; i <= 6; i++) {
            // Texto con base a los archivos (prueba)
            String rutaArchivo = "textos/texto" + i + ".txt";
            File archivo = new File(rutaArchivo);
            long tamanoBytes = archivo.length();

            // Lectura del texto del archivo
            Scanner scanner;
            String contenido = ""; // Tendrá el texto del archivo
            if (archivo.exists() && archivo.canRead()) { // verifica si el archivo existe y si el programa tiene permiso
                                                         // para leerlo
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

            // Establezca el texto basado en el texto del archivo
            String texto = contenido;

            System.out.println(".............................................................................");
            System.out.println("El tamaño del archivo " + rutaArchivo + " es de " + tamanoBytes + " bytes.");

            for (String patron : patrones) {
                ArrayList<Integer> posicionesFB = Algoritmos.algoritmoFuerzaBruta(texto, patron);
                imprimirResultado(posicionesFB, "Fuerza bruta" , patron);

                // Algoritmo Knuth-Morris-Pratt
                ArrayList<Integer> posicionesKMP = Algoritmos.algoritmoKnuthMorrisPratt(texto, patron);
                imprimirResultado(posicionesKMP, "Knuth-Morris-Pratt", patron);

                // Algoritmo Boyer-Moore
                ArrayList<Integer> posicionesBM = Algoritmos.algoritmoBoyerMoore(texto, patron);
                imprimirResultado(posicionesBM, "Boyer-Moore", patron);

            }

        }

    }
}