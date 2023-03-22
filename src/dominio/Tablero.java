package dominio;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Tablero{
    private static int DIMENSION = 30;
    int[][] estadoActual = new int[DIMENSION+2][DIMENSION+2];
    int[][] estadoSiguiente = new int[DIMENSION+2][DIMENSION+2];

    public void leerEstadoActual(){

    try{
        BufferedReader fr1 = new BufferedReader(new FileReader("C:/Users/adolf/OneDrive/Desktop/CEU/Programacion II/Practica2/src/dominio/matriz.txt"));
        estadoActual = new int[DIMENSION+2][DIMENSION+2];
         BufferedReader br = new BufferedReader(fr1);
           for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
            estadoActual[i][j] = br.read() == '0'?0:1;
            }
            br.read();
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


    public int numRandom(){
        Random r = new Random();
        int num = r.nextInt(2);
        return num;
    }

    public void generarEstadoActualPorMontecarlo(){
                for(int i = 0; i < DIMENSION + 2; i++){
                    estadoActual[i][0] = 0;
                        estadoActual[i][DIMENSION + 1] = 0;
                        estadoActual[0][i] = 0; 
                        estadoActual[DIMENSION][0] = 0;
                }

                for (int i = 0; i < estadoActual.length; i++) {
                    for (int j = 0; j < estadoActual.length; j++) {
                        estadoActual[i][j] = numRandom();
                    }
                }
            
            for (int i = 0; i < estadoActual.length; i++) {
                for (int j = 0; j < estadoActual[0].length; j++) {
                    estadoActual[i][j] = numRandom();
            }
        }
    }

    public void transitarAlEstadoSiguiente(){
        for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
                // celula de estado actual
                int cel = estadoActual[i][j];
                // vecinos 
                int vecinos = estadoSiguiente[i][j + 1] + estadoSiguiente[i][j - 1] + estadoSiguiente[i - 1][j] + estadoSiguiente[i + 1][j] + estadoSiguiente[i + 1][j + 1] + estadoSiguiente[i + 1][j - 1] + estadoSiguiente[i - 1][j + 1] + estadoSiguiente[i - 1][j - 1];
                // celula estado siguiente
                if(cel == 1 && (vecinos == 2 || vecinos == 3)){
                    estadoSiguiente[i][j] = 1;
                
                } else if(cel == 0 && vecinos == 3){
                    estadoSiguiente[i][j] = 1;
                
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
             if(estadoActual[i][j] == 0){
                cadena += "0";
            } else{
                cadena += "1";
                }
            }
            cadena += "\n";
        }
        return cadena;
    }
}