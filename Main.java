import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Main {

    /*
     * Imprime el resultado del algoritmo
     */
    public static void imprimirResultado(ArrayList<Integer> posiciones, String nombreAlgoritmo, String patron,
            Double tiempoDeEjecucion) {
        System.out.println("-------------------------------------");
        System.out.println("Nombre del algoritmo: " + nombreAlgoritmo);
        System.out.println("Patrón: " + patron);
        if (posiciones.size() == 0) {
            System.out.println("El patrón no se encontró en el texto.");
        } else {
            System.out.println("El patrón se encontró en las siguientes posiciones: " + posiciones.toString());
        }
        DecimalFormat df = new DecimalFormat("#.###");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    
        System.out.println("Tiempo de ejecución: " + df.format(tiempoDeEjecucion) + " ms");
        // Almacena los resultados en un archivo de texto
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("archivo.txt", true)));
            String linea = nombreAlgoritmo + "," + patron + "," + Algoritmos.a + "," + Algoritmos.c + ","
                    + df.format(tiempoDeEjecucion);
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

        String[] patrones = { "FFDANG", "ABCDEFGHIJKLNMOPQRSTOVXYZ", "ZZZZZ", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "ABGFCHK",
                "DBOPOBAJHUABAFGGABABABCDA" };

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

                // Algoritmo Fuerza Bruta
                long startTime = System.nanoTime();
                ArrayList<Integer> posicionesFB = Algoritmos.algoritmoFuerzaBruta(texto, patron);
                long endTime = System.nanoTime();
                double tiempoDeEjecucion = (endTime - startTime) / 1e6;
                imprimirResultado(posicionesFB, "Fuerza bruta", patron, tiempoDeEjecucion);

                // Algoritmo Knuth-Morris-Pratt
                long startTime2 = System.nanoTime();
                ArrayList<Integer> posicionesKMP = Algoritmos.algoritmoKnuthMorrisPratt(texto, patron);
                long endTime2 = System.nanoTime();
                
                double tiempoDeEjecucion2 = (endTime2 - startTime2) / 1e6;
                imprimirResultado(posicionesKMP, "Knuth-Morris-Pratt", patron, tiempoDeEjecucion2);

                // Algoritmo Boyer-Moore
                long startTime3 = System.nanoTime();
                ArrayList<Integer> posicionesBM = Algoritmos.algoritmoBoyerMoore(texto, patron);
                long endTime3 = System.nanoTime();
                double tiempoDeEjecucion3 = (endTime3 - startTime3) / 1e6;
                imprimirResultado(posicionesBM, "Boyer-Moore", patron, tiempoDeEjecucion3);

            }

        }

    }
}