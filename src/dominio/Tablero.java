package dominio;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Tablero{
    private static int DIMENSION = 30;
    // Matriz que representa el estado actual.
    public static void main(String []args){

        int[][] matriz = new int[DIMENSION][DIMENSION];

        try (FileWriter fw = new FileWriter("C:/Users/adolf/OneDrive/Desktop/CEU/Programacion II/Practica2/src/dominio/matriz.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
                
                Random random = new Random();
                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz.length; j++) {
                        matriz[i][j] = random.nextInt(2);
                    }
                }
            
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = random.nextInt(2);
                    bw.write(matriz[i][j] + "");
                }
                bw.newLine();
            }
        
            System.out.println("La matriz se ha escrito en el archivo correctamente.");

        }catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }



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


/*  
    // Devuelve la siguiente generación del tablero según las reglas del juego de la vida
    public static int[][] getNextGeneration(int[][] board) {
        int[][] newBoard = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                int aliveNeighbors = countAliveNeighbors(board, i, j);
                if (board[i][j] == 1 && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
                    newBoard[i][j] = 1; // la célula sigue viva
                } else if (board[i][j] == 0 && aliveNeighbors == 3) {
                    newBoard[i][j] = 1; // la célula nace
                } else {
                    newBoard[i][j] = 0; // la célula muere
                }
            }
        }
        return newBoard;
    }

    // Cuenta el número de células vivas alrededor de una célula dada
    public static int countAliveNeighbors(int[][] board, int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < 30 && c >= 0 && c < 30 && !(i == 0 && j == 0)) {
                    count += board[r][c];
                }
            }
        }
        return count;
    }
}

 */