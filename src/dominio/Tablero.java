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
    int[][] estadoActual = new int[DIMENSION+2][DIMENSION+2];
    int[][] estadoSiguiente = new int[DIMENSION+2][DIMENSION+2];

    public void leerEstadoActual(){

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

    public void generarEstadoActualPorMontecarlo(){
        try (FileWriter fw = new FileWriter("C:/Users/adolf/OneDrive/Desktop/CEU/Programacion II/Practica2/src/dominio/matriz.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
                
                Random random = new Random();
                for (int i = 0; i < estadoActual.length; i++) {
                    for (int j = 0; j < estadoActual.length; j++) {
                        estadoActual[i][j] = random.nextInt(2);
                    }
                }
            
            for (int i = 0; i < estadoActual.length; i++) {
                for (int j = 0; j < estadoActual[0].length; j++) {
                    estadoActual[i][j] = random.nextInt(2);
                    bw.write(estadoActual[i][j] + "");
                }
                bw.newLine();
            }

        }catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void transitarAlEstadoSiguiente(){
        for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
                // celula de estado actual
                int cel = estadoActual[i][j];
                // vecinos 
                int vecinos = estadoSiguiente[i][j + 1] + estadoSiguiente[i][j - 1] + estadoSiguiente[i - 1][j] + estadoSiguiente[i + 1][j] + estadoSiguiente[i + 1][j + 1] + estadoSiguiente[i + 1][j - 1] + estadoSiguiente[i - 1][j + 1] + estadoSiguiente[i - 1][j - 1];
                // celuola estado siguiente
                // regla a)
                if(cel == 1 && (vecinos == 2 || vecinos == 3)){
                    estadoSiguiente[i][j] = 1;
                // regla b)
                } else if(cel == 0 && vecinos == 3){
                    estadoSiguiente[i][j] = 1;
                // regla c)
                } else{
                    estadoSiguiente[i][j] = 0;
                }
            }
        }
    }
        

    public String toString(){
        String cadena = "";
        for(int i = 0; i < DIMENSION; i++){
            for(int j = 0; j < DIMENSION; j++){
             if(estadoActual[i][j] == 1){
                cadena += "1";
            } else{
                cadena += "0";
                }
            cadena += "";
            }
        }
        return cadena;
    }
}



/*  
for(int i = 0; i < estadoActual.length; i++){
            for(int j = 0; j < estadoActual[0].length; j++){
                int vecinosVivos = contarVecinosVivos(i, j);
                if(estadoActual[i][j] == 1 && (vecinosVivos == 2 || vecinosVivos == 3))
                    estadoSiguiente[i][j] = 1;
                else if(estadoActual[i][j] == 0 && vecinosVivos == 3)
                    estadoSiguiente[i][j] = 1;
                else
                    estadoSiguiente[i][j] = 0;
            }
        }
        estadoActual = estadoSiguiente;
        estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

    public int contarVecinosVivos(int fila, int columna){
        int vecinosVivos = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                int filaVecino = fila + i;
                int columnaVecino = columna + j;
                if(filaVecino >= 0 && filaVecino < DIMENSION && columnaVecino >= 0 && columnaVecino < DIMENSION && !(i == 0 && j == 0)){
                    vecinosVivos += estadoActual[filaVecino][columnaVecino];
                }
            }
        }
        return vecinosVivos;
    }


 */