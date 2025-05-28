    package es.unileon.prg1.tetris;

/**
 * La clase Block, es el tipo principal dentro de los Block, los Block* son subtipos de este, así
 * el código se simplifica. Este es el único que contiene métodos, ya que los demás son solo para 
 * tener un constructor y un piece propio. 
 * Esta clase mueve, gira y convierte el array en Pieces, para que se pueda dibujar en el tablero.
 * @author Miguel Ruz García & Adrián Pérez Galán
*/

    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;


    
    public class Block {
        
        protected ArrayMxN block;
        protected Position position;
        protected Boolean drop;
        protected Piece piece;
        private static final Logger Logger = LogManager.getLogger(Block.class.getName());

    /**
     * Es el constructor del Block, crea un bloque de 2x2. Initicializa la posición a 0, y crea una pieza
     * que guardará el color, el símbolo y los puntos que vale al eliminar una línea.
     */

        public Block() {
            drop = false;
            this.block = new ArrayMxN(2,2);
            this.block.set(0, 0, 1);
            this.block.set(0, 1, 1);
            this.block.set(1, 0, 1);
            this.block.set(1, 1, 1);
            this.position = new Position();
            this.piece = new Piece(Color.YELLOW, "O", 1);
        }

    /**
     * Devuelve el número de filas que tiene el Bloque, es decir, cuanto mide de alto.
     * @return
     */
        public int getRows(){
            return this.block.rows();
        }
    /**
     * Devuelve el número de columnas que tiene el Bloque, es decir, cuanto mide de ancho.
     * @return
     */
        public int getColumns(){
            return this.block.columns();
        }

    /**
     * Devuelve la posición en el ejeX del bloque, es decir, cuantas veces se ha movido a la derecha.
     * @return
     */

        public int getBlockPosX(){
            return this.position.getX();
        }

    /**
     * Devuelve la posición Y del bloque, es decir, cuantas veces ha bajado.
     * @return
     */

    public int getBlockPosY(){
        return this.position.getY();
    }

    /**
     * Se crea un StringBuilder, que es donde se va a guardar el bloque, y se va a devolver.
     * Se recorre el bloque, y se va añadiendo al StringBuilder, si el valor del bloque es 1, se añade
     * una pieza, si es 0, se añade un espacio en blanco.
     * @return
     */


    public String toString(){
        StringBuilder blockToString = new StringBuilder();
        for(int i = 0; i <= this.getRows() - 1; i ++ ){
            blockToString.append(" ");
            int laps = 0;
            while( laps < this.position.getX()){
                blockToString.append(new Piece().toString());
                laps++;
            }
            for(int j = 0; j < this.getColumns(); j++){
                if(this.block.get(i,j) == 1){
                    blockToString.append(new Piece(this.piece).toString());
                }else{
                    blockToString.append(new Piece().toString());
                }

            }

            blockToString.append("\n");
        }
        return blockToString.toString();
    
    }

    /**
     * Manda a la clase Position usar su método moveReft().
     * (Aumenta la posición en X por 1)
     */

    public void moveRight(){
        Logger.info("Aumenta la posición en X por 1");
        Logger.info("Moviendo Bloque...");
        this.position.moveRight();
    }

    /**
     * Manda a la clase Position usar su método moveLeft().
     * (Disminuye la posición en X por 1)
     */

    public void moveLeft() {
        Logger.info("Disminuye la posición en X por 1");
        Logger.info("Moviendo Bloque...");
        this.position.moveLeft();
    }

    /**
     * Recibe el Board, además de un Bloque, es decir el bloque que se quiere bajar.
     * Hemos implementado un método, donde va leyendo línea por línea, de ahí el actualRow.
     * Mientras el método canMoveDown de como correcta la posición inferior, iremos añadiendo uno a esta
     * variable ya mencionada. Así cuando este finalice, tendremos la fila de más abajo en la que podemos
     * colocar el Bloque. 
     * Luego tenemos un if necesario para que el drop funcione correctamente, y finalmente mandamos bajar la
     * posición tantas veces como valga el actualRow. 
     * Finalmente consigue la posición final del Bloque, y le resta a la posición en Y lo que mide el bloque 
     * de alto y le suma 1 para que cua6dre correctamente.
     * Luego comprueba que el drop sea correcto, y declara el drop como true, es decir se creará un bloque nuevo
     * en el siguiente print, y se llamaŕa al placeBlock del Board para añadir el Bloque en el Board.
     * @param board
     * @return
     */

    public Boolean moveDown(Board board) {
        int rows = board.getRows();
        int actualRow = rows - 1;
        Logger.info("Se comprueba donde encaja el Bloque en el Board");
        while(!board.canMoveDown(actualRow, this.block.rows(), this.block.columns(), this.position.getX(), this.block)) {
            actualRow--;
        }
        if(actualRow >= block.rows() - 1){
            this.position.moveDown(actualRow);
            int blockX = this.position.getX();
            int blockY = (actualRow + 1) - this.block.rows();
            drop = true;
            board.placeBlock(blockY, blockX, this.block, this.piece);
            Logger.info("El bloque se ha colocado dentro del tablero");
        }
        return drop;
    } 

    /**
     * Este método, inicia consiguiendo el tamaño del Bloque, y crea un bloque de tipo Piece siguiendo eso.
     * Este método es usado por el PlaceBlock en el Board, ya que ese es de tipo Piece y para unirlo ambos
     * tenían que ser de dicho tipo.
     * Entonces donde el Array no vale 0, introduce una letra dependiendo del tipo de Blocque, en el caso de
     * encontrarse un 0, lo llena de un Piece vacío.
     *  @return
     */

    public Piece[][] getBlockData() {
        int rows = this.block.rows();
        int cols = this.block.columns();
        Piece[][] blockData = new Piece[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.block.get(i, j) != 0) {
                    blockData[i][j] = new Piece(this.piece);
                } else {
                        blockData[i][j] = new Piece(); //asigna un Piece vacío si es 0 en el ArrayMxN
                }
            }
        }
            return blockData;
    }

    /**
     * Devuelve el valor en una posición del Board.
     * @param i
     * @param j
     * @return
     */

    public int getPiece(int i, int j) {
        return block.get(i,j);
    }

    /**
     * Usado por otras clases para conseguir el valor que tiene el drop.
     * @return
     */

    public Boolean getDrop(){
        return this.drop;
    }

    /**
     * Devuelve cuanto mide el Bloque de ancho.
     * @return
     */

    public int length(){
        return this.getColumns();

    }

    /**
     * Crea un bloque de tipo NxN (cuadrado) con las dimensiones del bloque actual
     * y lo gira usando un método de la clase ArrayNxN en el sentido indicado. Tras
     * ello, reduce su tamaño al mínimo posible y comprueba si se sale de los bordes
     * del tablero. En caso afirmativo, regula sus coordenadas y lo reposiciona.
     * Añadimos las comprobaciones para que no se salga del tablero, en caso de que se salga,
     * lo reposiciona.
     * @param gameCols
     * @return
     */

    public void spinRight(int gameCols) {
        Logger.info("El Block gira el Bloque");
        ArrayNxN spinnedblock = new ArrayNxN(this.block);
        spinnedblock = spinnedblock.spinRight();
        this.block = spinnedblock.getMinArray();
        if (this.position.getX() + this.block.columns() > gameCols) {
            this.position.setX(gameCols - this.block.columns());
        }
        if (this.position.getX() < 0) {
            this.position.setX(0);
        }
    }

    /**
     * Crea un bloque de tipo NxN (cuadrado) con las dimensiones del bloque actual
     * y lo gira usando un método de la clase ArrayNxN en el sentido indicado. Tras
     * ello, reduce su tamaño al mínimo posible y comprueba si se sale de los bordes
     * del tablero. En caso afirmativo, regula sus coordenadas y lo reposiciona.
     * Añadimos las comprobaciones para que no se salga del tablero, en caso de que se salga,
     * lo reposiciona.
     * @param gameCols
     * @return
     */

    public void spinLeft(int gameCols) {
        Logger.info("El Block gira el Bloque");
        ArrayNxN spinnedblock = new ArrayNxN(this.block);
        spinnedblock = spinnedblock.spinLeft();
        this.block = spinnedblock.getMinArray();
        if (this.position.getX() + this.block.columns() > gameCols) {
            this.position.setX(gameCols - this.block.columns());
        }
        if (this.position.getX() < 0) {
            this.position.setX(0);
        }
    }
}