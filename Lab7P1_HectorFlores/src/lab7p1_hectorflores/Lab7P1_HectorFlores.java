package lab7p1_hectorflores;
import java.util.Scanner;
import java.util.Random;

//Fila 3, asiento 7, lado derecho
//te quiero emilio <3

public class Lab7P1_HectorFlores {
    
    static Scanner sc = new Scanner(System.in);
    static Scanner scChar = new Scanner(System.in);
    static Random random = new Random();
    
    public static void menu() {//genera el menu
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos de silla");
        System.out.println("Ingresar cualquier otro numero sale del programa");
        System.out.println("");
    }
    
    public static void imprimir_matriz_char(char [][]x){//imprime una matriz de caracteres
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                System.out.print("[" + x[i][j] + "]");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void imprimir_matriz_int(int [][]x){//imprimeuna matriz de enteros
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                System.out.print("[" + x[i][j] + "]");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static char [][] generarTablero(char [][]x){//Genera el tablero vacio de tres en raya
        char [][] res = new char [x.length][x[0].length];
        
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                res[i][j] = ' ';
            }
        }
        
        return res;
    }
    
    public static boolean verificarPosicionValida(char [][]x, int i, int j){//verifica si la posicion ingresada por el usuario no esta llena o no existe en la matriz
        boolean vof = false;
        
        if (i >= 0 && i <= 2 && j >= 0 && j <= 2 && (int)x[i][j] == 32){
            vof = true;
        }
        
        return vof;
    }
    
    public static boolean verificarVictoria(char [][]x, char xo0){//chequea todos los casos en los quue se puede dar una victoria de x o de 0
        boolean victoria = false;
        
        if (x[0][0] == xo0 && x[0][1] == xo0 && x[0][2] == xo0){
            victoria = true;
        } else if (x[1][0] == xo0 && x[1][1] == xo0 && x[1][2] == xo0){
            victoria = true;
        } else if (x[2][0] == xo0 && x[2][1] == xo0 && x[2][2] == xo0){
            victoria = true;
        } else if (x[0][0] == xo0 && x[1][0] == xo0 && x[2][0] == xo0){
            victoria = true;
        } else if (x[0][1] == xo0 && x[1][1] == xo0 && x[2][1] == xo0){
            victoria = true;
        } else if (x[0][2] == xo0 && x[1][2] == xo0 && x[2][2] == xo0){
            victoria = true;
        } else if (x[0][0] == xo0 && x[1][1] == xo0 && x[2][2] == xo0){
            victoria = true;
        } else if (x[2][0] == xo0 && x[1][1] == xo0 && x[0][2] == xo0){
            victoria = true;
        }
        
        return victoria;
    }
    
    public static int casillasVacias(char [][] x){//cuenta las casillas vacias de una matriz
        int cuenta_vacias = 0;
        
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                if (x[i][j] == ' '){
                    cuenta_vacias++;
                }
            }
        }
        
        return cuenta_vacias;
    }
    
    public static int [][] generarIntMatrizAleatoria(int f, int c){//genera una matriz aleatorias de dimensiones f y c con numeros entre 0 y 99
        int [][] temporal = new int [f][c];
        
        for (int i = 0; i < f; i++){
            for (int j = 0; j < c; j++){
                temporal [i][j] = random.nextInt((99-0)+1)+0;
            }
        }
        
        return temporal;
    }
    
    public static int menor_fila(int [][]x, int fila){//busca el valor menor en una fila 
        int menor = 100;
        
        for (int i = 0; i < x[0].length; i++){
            if (x[fila][i] < menor){
                menor = x[fila][i];
            }
        }
        
        return menor;
    }
    
    public static int mayor_columna(int [][]x, int columna){//busca el valor mayor de una columna
        int mayor = -1;
        
        for (int i = 0; i < x.length; i++){
            if (x[i][columna] > mayor){
                mayor = x[i][columna];
            }
        }
        
        return mayor;
    }
    
    public static void encontrarPuntosSilla(int [][]x){//encuentra los puntos de silla de una matriz
        System.out.println("");
        int contador = 0;
        
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                if (x[i][j] == menor_fila(x, i) && x[i][j] == mayor_columna(x, j)){
                    contador++;
                    System.out.println("Elemento de silla encontrado en casilla [" + i + "]" + "[" + j +"]: " + x[i][j]);
                }
            }
        }
        
        if (contador == 0){
            System.out.println("No hay puntos de silla");
        }
    }
    
    public static void main(String[] args) {
        menu();
        System.out.println("Ingrese su opcion:");
        int opcion = sc.nextInt();
        
        while (opcion > 0 && opcion < 3){
            switch (opcion){
                case 1:
                    System.out.println("Tres en raya: ");//genera e imprime el tablero
                    char [][] tablero = new char [3][3];//genera el size del tablero
                    
                    char res = 's';
                    while (res == 's' || res == 'S'){
                        tablero = generarTablero(tablero);//llena el tablero de espacios
                        imprimir_matriz_char(tablero);//muestra el tablero
                        boolean gane = false;
                        while (gane == false){//chequea si alguien ha ganado o si no se puede seguir jugando
                            System.out.println("Ingrese la casilla que quiere llenar con X");
                            System.out.print("Fila (0 , 1, 2): ");
                            int fila = sc.nextInt();//solicita indice de fila
                            System.out.println("");
                            System.out.print("Columna (0, 1, 2): ");
                            int columna = sc.nextInt();//solicita indice de columns
                            System.out.println("");

                            while (verificarPosicionValida(tablero, fila, columna) == false){//verifica si la casilla existe o no esta llena y en caso de que no exista o este llena, solicita una casilla nueva
                                System.out.println("Ingrese una fila y columna valida y que no este llena ya");
                                System.out.print("Fila (0, 1, 2): ");
                                fila = sc.nextInt();
                                System.out.println("");
                                System.out.print("Columna (0, 1, 2): ");
                                columna = sc.nextInt();
                                System.out.println("");
                            }

                            tablero [fila][columna] = 'X';//si la casilla existe, llena esa casilla con X
                            imprimir_matriz_char(tablero);//muestra el tablero con la x agregada

                            if (verificarVictoria(tablero, 'X')){//verifica si x gano
                                System.out.println("X gana");
                                break;
                            }

                            if (casillasVacias(tablero) == 0){//verifica si hay casillas vacias en el tablero
                                System.out.println("Empate. La maquina y vos son malos");
                                break;
                            }

                            fila = random.nextInt((2-0)+1)+0;//genera un valor de fila random
                            columna = random.nextInt((2-0)+1)+0;//genera un valor de columna random

                            while (verificarPosicionValida(tablero, fila, columna) == false){//verifica si la casilla generada con fila y columna no esta llena ya
                                fila = random.nextInt((2-0)+1)+0;
                                columna = random.nextInt((2-0)+1)+0;
                            }

                            tablero [fila][columna] = '0';//llena dicha casilla con 0
                            imprimir_matriz_char(tablero);//muestra el tablero despues del turno de la maquina

                            if (verificarVictoria(tablero, '0')){
                                System.out.println("Gana 0, sos malo, te gano una maquina randommizada (tonta)");
                                break;
                            }//verifica si 0 gano
                        }
                        System.out.println("Desea continuar? [s/n]");
                        res = scChar.next().charAt(0);//solicita respuesta de usuario
                    }
                    break;
                case 2:
                    System.out.println("Puntos de silla");
                    System.out.println("Ingrese filas de su matriz:");
                    int filas = sc.nextInt();//pide la cantidad de filas parala matriz a generar
                    while (filas < 1){//verifica que la fila sea 1 o mayor
                        System.out.println("Ingrese un numero mayor a 0");
                        filas = sc.nextInt();
                    }
                    System.out.println("Ingrese columnas de su matriz");
                    int columnas = sc.nextInt();//pide columnas de la matriz a generar
                    while (columnas < 1){//verifica que la cantidad de columnas sea mayor o igual a 1
                        System.out.println("Ingrese un numero mayor a 0");
                        columnas = sc.nextInt();
                    }
                    
                    int [][] matriz = generarIntMatrizAleatoria(filas, columnas);//genera la matriz con numero aleatorios
                    imprimir_matriz_int(matriz);//muestra dicha matriz
                    
                    encontrarPuntosSilla(matriz);//muestra los puntos de silla de la matriz
                    break;
            }
            menu();//vuelve a mostrar el menu
            opcion = sc.nextInt();//pide opcion
        }
        System.out.println("Salio del programa");
    }
}