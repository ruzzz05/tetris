package es.unileon.prg1.tetris;

/**
 * La clase tetris, es la intermediaria en la mayoria de procesos, recibe una orden del TextUI, y este habla 
 * con los Block o con el Board, dependiendo de la acción.
 * También crea los bloques, utilizando un random, así no se sabrá el orden de los bloques.
 * @author Miguel Ruz García
*/
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import es.unileon.prg1.tetris.strategy.*;

public class Tetris{

    private Board board;
    private int points;
    static int columns;
    private Block block;
    private int limit;
    private int selector;
    private Random randomNumber;
    /**
     * Con esto podemos hacer Loggers a lo largo del código y saber que está pasando
     */
    private static final Logger Logger = LogManager.getLogger(Tetris.class.getName());

    /**
     * En el Constructor de Tetris, recibe las rows, y las columns que ha introducido el usuario a la hora de ejecutar el 
     * juego. Además comprueba si esos valores son correctos y válidos. En el caso contrario lanzará un eror que terminará
     * el juego.
     * Luego crea un Board, con los rows y columns que ya hemos mencionado, además de reiniciar los puntos a 0. Además al 
     * recibir el colorMode, se crea el Singleton, que será el encargado de elegir el color de los bloques. 
     * @param rows
     * @param columns
     * @param colorMode
     * @throws TetrisException
     */
    
    public Tetris(int rows, int columns, String colorMode) throws TetrisException{
        if (rows < 5 || rows > 20 || columns < 6 || columns > 10) {
            throw new TetrisException("Invalid input");
        }
        this.columns = columns;
        this.board = new Board(rows, columns);
        this.points = 0;
        ColorStrategySingleton.getInstance(colorMode);
    }

    /**
     * Este método pasa los puntos, que son enteros a un String, así lo podrán usar otros métodos o clases a la hora de hacer un print o 
     * a la hora de hacer un test poder compararlo.
     * @return
     */

    public String getPointstoString(){
        this.getPoints();
        return Integer.toString(this.points);
    }
   
    /**
     * Este método es usado por otras clases para poder forzar una cantidad de puntos, por ejemplo en los test.
     * @param pointsAdd
     */

    public void setPoints(int pointsAdd){
        this.board.setPoints(pointsAdd);
    }

    /**
     * El método toString crea un StringBuilder, donde meterá el bloque en forma de String y el Board de la misma forma.
     * Finalmente este añadirá los puntos debajo del Board, y así el return de este StringBuilder que será pasado a String
     * contendrá el Block, Board y los puntos.
     * @return
     * 
      */

    public String toString(){
        StringBuilder finalTetris = new StringBuilder();
        String message = "              ¨W¨ --> spin left    ¨E¨ --> spin right \n ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n ¨Salir¨ to leave tetris)";
        String voidLine = "\n";
        String finalBlock = this.block.toString();
        String finalBoard = this.getBoard();
        finalTetris.append(voidLine);
        finalTetris.append(finalBlock);
        finalTetris.append(finalBoard);
        finalTetris.append(voidLine);
        finalTetris.append("POINTS: " + getPointstoString());
        finalTetris.append(voidLine);
        finalTetris.append(message);
        return finalTetris.toString();  
    }

 
    /**
     * Este método es usado para conseguir el Board actualizado, es decir si se ha añadido un
     * bloque al hacer drop y demás.
     * @return
     */

    public String getBoard(){
        return this.board.getBoardString();
    }

    /**
     * Este método es usado para conseguir el bloque que está en juego.
     * @return
     */

    public Block getActuaBlock(){
        return this.block;
    }

    /**
     * Este método funciona de forma que develve la posición actual del Bloque en el eje X.
     * @return
     */

    public int getPositionX(){
        return this.block.getBlockPosX();
    }

    /**
     * Este método funciona de forma que develve la posición actual del Bloque en el eje Y.
     * @return
     */
    public int getPositionY(){
        return this.block.getBlockPosY();
    }
    /**
     * Método usado para conseguir el valor del drop. Este mide si se ha hecho un drop correctamente o no.
     * @return
     */

    public Boolean getDrop(){
        return this.block.getDrop();
    }

    /**
     * Este método es usado para establecer un Board en específico como el que se va a usar en el juego.
     * @param board
     */

    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Este método lo usamos para poder conseguir el Bloque ya que nuestro método que lo crea es privado.
     * @return
     */

    public Block getBlock(){
        this.block = this.randomBlock();
        return this.block;
    }

    /**
     * Este será el método encargado de crear de forma aleatoria un bloque. EL getBlock, está llamado por
     * el TetrisTextUI cuando detecta que se ha hecho un drop, o inicialmente para crear un bloque.
     * Primero hace un Random, entre el 0 y el 6, y al sumarle 1, las opciones están entre 1 y 7, y dependiendo
     * del número se creará un tipo distinto de Bloque.
     * @return
     */

    private Block randomBlock(){
        randomNumber = new Random();
        randomNumber.setSeed(System.currentTimeMillis());
        selector = randomNumber.nextInt(7) + 1;
        String loggerInfo1 = "La posición se resetea a 0,0";
        switch (selector) {
            case 1:
                Logger.info("Desde el Tetris se ha creado un bloque Cuadrado");
                Logger.info(loggerInfo1);
                block = new Block();
                break;
            case 2:
                Logger.info("Desde el Tetris se ha creado un bloque I");
                Logger.info(loggerInfo1);
                block = new BlockI();
                break;
            case 3:
                Logger.info("Desde el Tetris se ha creado un bloque J");
                Logger.info(loggerInfo1);
                block = new BlockJ();
                break;
            case 4:
                Logger.info("Desde el Tetris se ha creado un bloque L");
                Logger.info(loggerInfo1);
                block = new BlockL();
                break;
            case 5:
                Logger.info("Desde el Tetris se ha creado un bloque T");
                Logger.info(loggerInfo1);
                block = new BlockT();
                break;
            case 6:
                Logger.info("Desde el Tetris se ha creado un bloque S");
                Logger.info(loggerInfo1);
                block = new BlockS();
                break;
            case 7:
                Logger.info("Desde el Tetris se ha creado un bloque Z");
                Logger.info(loggerInfo1);
                block = new BlockZ();
                break;
            default:
                Logger.info("Desde el Tetris se ha creado un bloque Cuadrado COMO DEFAULT");
                Logger.info(loggerInfo1);
                block = new Block();
                break;
                
        }


        return this.block;
    }

    /**
     * Este método, calcula y devuelve el límite. Este se calcula con las columnas que mide el Board, 
     * es decir el ancho, y lo resta a cuanto mide de ancho el bloque, y el resultado será cuantas veces
     * se podrá mover el Block.
     * @return
     */

    public int getLimit(){
        limit = columns - block.length();
        return limit;
    }

    /**
     * Este método, se encarga de llamar al método moveRight del Block, este será el que lo moverá.
     * En el caso de que el bloque esté en el límite, lanzará una excepción que será capturada.
     */

     public void moveRight() throws TetrisException{
        this.getLimit();
        Logger.info("El tetris manda MOVER al Bloque a la DERECHA");
        if(this.block.getBlockPosX() >= limit){
            throw new TetrisException("No se puede mover a la derecha");
        }else{
            this.block.moveRight();
        }
    }

    /**
     * Este método, se encarga de llamar al método moveLeft del Block, este será el que lo moverá.
     * En el caso de que el bloque esté en el límite, lanzará una excepción que será capturada.
     * @throws TetrisException
     */

    public void moveLeft() throws TetrisException{
        Logger.info("El tetris manda MOVER al Bloque a la IZQUIERDA");
        if(this.block.getBlockPosX() != 0){
            this.block.moveLeft();  
        }else{
            throw new TetrisException("No se puede mover a la izquierda");
        }
    }

    /**
     * Este método, se encarga de llamar al método spinRight del Block, este será el que lo girará.
     * 
     */

    public void spinRight(){
        Logger.info("El tetris manda GIRAR al Bloque a la DERECHA");
        this.block.spinRight(board.getColumns());
        this.getLimit();
    }

    /**
     * Este método, se encarga de llamar al método spinLeft del Block, este será el que lo girará.
     */

    public void spinLeft(){
        Logger.info("El tetris manda GIRAR al Bloque a la IZQUIERDA");
        this.block.spinLeft(board.getColumns());
        this.getLimit();
    }

    /**
     * El método drop, llama al método moveDown que se encarga de realizar en sí el drop, 
     * para ello le pasamos el board actualizado, el bloque que se está actualizando en ese
     * momento. Además si el drop no se puede hacer, lanzará una excepción que será capturada.
     * @return
     * @throws TetrisException
     */

    public Boolean drop() throws TetrisException{
        Boolean drop;
        Logger.info("El tetris manda hacer un DROP al block");
        drop = this.block.moveDown(this.board);
        return drop;
    }


    
    /**
     * El método set, recibe un bloque y este convierte ese bloque en el bloque a usar, 
     * es usado para los test. 
     * @param block
     * @return
     */

    public Block set(Block block){
        this.block = block;
        return this.block;
    }

    /**
     * Este método es usado sobretodo por los test, para forzar la creación de un bloque, en específico.
     * @param selector
     * @return
     */

    public Block create(int selector){

        switch (selector) {
            case 1:
                Logger.info("Se ha creado un bloque Cuadrado");

                block = new Block();
                this.getLimit();

                break;
            case 2:
                Logger.info("Se ha creado un bloque I");
                block = new BlockI();
                this.getLimit();

                break;
            case 3:
                Logger.info("Se ha creado un bloque J");
                block = new BlockJ();
                this.getLimit();

                break;
            case 4:
                Logger.info("Se ha creado un bloque L");
                block = new BlockL();
                this.getLimit();

                break;
            case 5:
                Logger.info("Se ha creado un bloque T");
                block = new BlockT();
                this.getLimit();

                break;
            case 6:
                Logger.info("Se ha creado un bloque S");
                block = new BlockS();
                this.getLimit();

                break;
            case 7:
                Logger.info("Se ha creado un bloque Z");
                block = new BlockZ();
                this.getLimit();

                break;
            default:
                block = new Block();
                this.getLimit();

                break;
        }
    
        return this.block;
    }
   

    /**
     * Este método es usado para poder devolver los puntos actualizados al borrar una línea por el Board
     * @return
     */
    public int getPoints(){
        this.points = board.getPoints();
        return this.points;
    }
  

    

}

