package es.unileon.prg1.tetris;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * La clase Board, crea lo que es el Board en sí, además de encargarse del Drop, y de fusionar
 * el Bloque en el Board.
 * @author Miguel Ruz García
*/

public class Board {

    private int columns;
    private int rows;
    private Piece[][] board;
    private String stringBoard;
    private int points;
    private static final Logger Logger = LogManager.getLogger(Block.class.getName());


    /**
     * El constructor de Board, requiere del tamaño de este, que es introducido por el usuario al iniciar el juego.
     * Aquí se crea un Board de tipo Piece, del tamaño de esos rows y columns.
     * Además rellena el Board con Piezas vacías.
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.board = new Piece[rows][columns];
        this.getStringBoard();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = new Piece();
            }
        }
    }

    /**
     * Este método, devuelve el Board que se ha convertido en un String, útil a la hora de hacer un print o similar.
     * @return
     */

    public String getStringBoard(){
        return this.stringBoard;
    }

    /**
     * Este método devuelve los puntos que se han conseguido hasta el momento.
     * @return
     */

    public int getPoints(){
        return points;
    }

    /**
     * Este método recibe un número entero, y con ello actualiza los puntos que se han conseguido hasta el momento.
     * @param pointsAdd
     */

    public void setPoints(int pointsAdd){
        this.points = pointsAdd;
    }


    /**
     * El método get, recibe dos parámetros enteros y con ello devuelve la Piece que se encuentra en esa posición dentro
     * del Board.
     * @param row
     * @param col
     * @return
     */

    public Piece get(int row, int col) {
        return board[row][col];
    }

    /**
     * El método set, recibe dos números enteros y una Piece, este método fuerza que en esa posición con esas cordenadas
     * dentro del Board tenga el valor de esa Piece.
     * @param row
     * @param col
     * @param value
     */

    
    public void set(int row, int col, Piece value) {
        board[row][col] = value;
    }
    
        /**
     * El método canMoveDown es el encargado de comprobar que el bloque puede bajar una posición
     * hacia abajo, este recibe el Board, y cuanto mide el Board de alto, y la línea que tiene que
     * comprobar.
     * Este verifica primero que el actualRow no sea menor que 0, es decir que sea una línea existente
     * dentro del Board. Usamos i como la línia que tiene que comprobar, y le restamos el alto del bloque
     * y le tenemos que sumar 1 porque las rows y columns empiezan a contar desde 0, y j como la columna,
     * dependiendo de la posición en X del bloque.
     * Mientras no detecte una posición no válida para el bloque. Tenemos la condición donde x empieza
     * desde el valor máximo que tiene la row más hacia abajo que exista, así leemos de arriba hacia abajo.
     * Comprobamos que en el bloque existe un 1, es decir ahí hay un trozo del bloque y que en el Board
     * no hay nada, es decir que está vacío. En el caso de que en ambos sitios haya algo, entonces no se
     * podría hacer el drop, y se devolvería false. En el caso contrario, se seguiría leyendo por si podemos
     * encontrar una posición no válida.
     * @param actualRow
     * @param blockRows
     * @param blockCols
     * @param blockX
     * @param blockY
     * @param block
     * @return
     * */    

    public boolean canMoveDown(int actualRow, int blockRows,int blockCols, int blockX, ArrayMxN block) {
        Boolean canMoveDown = true;
        // Verificar si hay alguna obstrucción en la siguiente fila hacia abajo
        if(actualRow >= 0 ){
            int i = actualRow - blockRows + 1;
            int j = blockX; 
            for(int x = blockRows - 1; x >= 0; x--){
                for(int y = 0; y < blockCols; y++){
                // Verificar si la siguiente posición hacia abajo está ocupada en el tablero
                    if(canMoveDown && i + x >= 0 && block.get(x, y) == 1 && !this.get(i + x,j + y).isEmpty()){
                        canMoveDown = false;
                    }
                }
            }            
            
        }
        return canMoveDown;
    }
        

    



    /**
     * El método placeBlock es el método que coloca el bloque dentro del Board cuando el jugador inicia un drop. Este
     * recibe dos números enteros, que son la posición desde donde debe empezar a dibujar el bloque, además del Bloque
     * que se esté usando en ese momento.
     * Este crea un bloque temporal de tipo Piece, y este ya tiene el dibujo correspondiente con las letras y forma,
     * dependiendo del tipo de Pieza que sea.
     * Entonces este va revisando en que posicion de ese Bloque no está vacio, y lo va introduciendo en el Board en la 
     * posición a la cual corresponde.
     * Finalmente llama al método lineFull, para que compruebe si hay alguna línea llena.
     * @param row
     * @param col
     * @param block
     */

    public void placeBlock(int row, int col, ArrayMxN block, Piece piece) {
        Piece[][] blockData = new Piece[block.rows()][block.columns()];
        for (int i = 0; i < blockData.length; i++) {     
            for (int j = 0; j < blockData[0].length; j++) {
                if(block.get(i, j) == 0){
                    blockData[i][j] = new Piece();
                }else{
                    blockData[i][j] = new Piece(piece);
                }
                if (!blockData[i][j].isEmpty()) {
                    board[row + i][col + j] = new Piece(blockData[i][j]);
                }
            }
        }
        this.lineFull();
    }

    /**
     * El método lineFull, recorre el Board, y comprueba si hay alguna línea llena, es decir que no haya ningún espacio
     * vacío en esa línea. En el caso de que haya una línea llena, llama al método cleanLine, para que borre esa línea
     * y sume los puntos correspondientes.
     */

    public Boolean  lineFull(){
        Boolean full = false;
        Logger.info("Comprobamos si hay alguna Linea entera");
        for(int line = 0; line < rows; line ++){
            int cont = 0;
            for(int j = 0; j < columns; j++){
                    if(!board[line][j].isEmpty()){
                        cont = cont + 1;
                    }
                    if(cont == columns){
                        full = true;
                        this.cleanLine(line);
                    }
            }
        }
  
        return full;
    }

    /**
     * El método cleanLine, recibe un número entero, que es la línea que se tiene que borrar. Este método borra la línea
     * y suma los puntos correspondientes.
     * Este borra la línea igualando esa línea a su superior, y así sucesivamente hasta llegar a la primera línea, que será
     * igualada a piezas vacías.
     * @param line
     * @return
     */
    
    public int cleanLine(int line){
        Logger.info("Empezamos a limpiar una línea.......");
        int row = line;
        for(int m = 0; m < columns; m++){
            points = points + board[row][m].getPoints();
        }
        for(int i = line; i > 0; i--){
            for(int j = 0; j < columns; j++){

                board[i][j] = board[i - 1][j];
                }
        }
        for(int j = 0; j < columns; j++){
            board[0][j] = new Piece();
        }

        Logger.info("Hemos sumado un total de: " + points + " PUNTOS");

        return points;
    }



    /**
     * Devuelve la cantidad de rows que tiene el Board.
     * @return
     */

    public int getRows() {
        return rows;
    }

    /**
     * Devuelve la cantidad de columns que tiene el Board.
     * @return
     */

    public int getColumns() {
        return columns;
    }

    /**
     * Este método crea un Board en forma de String.
     * Primero crea un StringBuilder donde almacenar esos String. Va leyendo línea por línea, en el caso que sea la
     * columna inicialo final, dibuja una barra vertical, y así se delimitará el Board, en el caso que sea final 
     * también añade un \n para bajar a dibujar la siguiente línea. Además en los espacios vacíos coge 
     * un espacio vacío de la clase Piece. Posteriormente dibuja la esquina de abajo a la izquierda, y dibuja las líneas 
     * de abajo, y finalmente la esquina derecha y un \n. Finalmente esto se pasa a un String.
     * @return
     */

    public String getBoardString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            boardString.append("│"); // Lateral izquierdo del tablero
            for (int j = 0; j < this.columns; j++) {
                    boardString.append(this.board[i][j].toString());
            }
            boardString.append("│\n"); // Lateral derecho del tablero y salto de línea para cada fila
        }
        // Esquina inferior izquierda
        boardString.append("└");
        // Borde inferior
        for (int i = 0; i < this.columns * 2; i++) {
            boardString.append("─");
        }
        // Esquina inferior derecha
        boardString.append("┘\n");
        return boardString.toString();
    }
    
    
    /**
     * Este método recibe las cordenadas de donde queremos saber el valor del Board. Con esto podemos saber
     * donde están los bordes, y donde los huevos intermedios.
     * @param row
     * @param column
     * @return
     */

    public int getValueBoard(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (column == 0){
                return 1;
            } 
            if (column == columns - 1) {
                return 1; 
            } 
            if (row == rows - 1){
                return 1;
            } 
            else {
                return 0; 
            }
        } 
        else {
            return -1; 
        }
    }
    
 
 }