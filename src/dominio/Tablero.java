package dominio;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tablero{
    private static int DIMENSION = 30;
    // Matriz que representa el estado actual.
    public static void main(String []args){
    int[][] estadoActual;
    try{
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/adolf/OneDrive/Desktop/CEU/Programacion II/Practica2/src/dominio/matriz.txt"));
        estadoActual = new int[DIMENSION][DIMENSION];
        String linea  = br.readLine();
        int fila = 0; //Para recorrer las filas de la matriz
			while(linea != null) {
                String[] numeros = linea.split("");
                for(int i = 0; i < numeros.length; i++) 
                    estadoActual[fila][i] = Integer.parseInt(numeros[i]);
                fila++;
                linea = br.readLine();
            }
            br.close();
            //Mostramos la matriz leida
            for(int i=0; i < DIMENSION; i++){
                for(int j=0; j < DIMENSION; j++)
                    System.out.print(estadoActual[i][j] + " ");
                System.out.println();
            }
    }catch(FileNotFoundException e){
        System.out.println("No se ha encontrado el archivo");
        e.printStackTrace();
     }catch(NumberFormatException ex1){
        System.out.println("Error en el formato de los numeros");
        ex1.printStackTrace();
    }catch(IOException ex2){
        System.out.println("Error de entrada/salida");
        ex2.printStackTrace();
        }
    
 }
}
