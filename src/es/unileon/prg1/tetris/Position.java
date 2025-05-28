package es.unileon.prg1.tetris;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * La clase Position, es una clase que hemos creado adicionalmente para facilitarnos la tarea de controlar
 * las variables de posicion de los bloques. 
 * Este aumenta, disminuye y devuelve la posición en X o en Y.
 * @author Miguel Ruz García
*/
public class Position {
    /**
     * Valores para representar donde está situado un bloque en el Board en el eje X.
     */
    private int x;
    /**
     * Valores para representar donde está situado un bloque en el Board en el eje Y.
     */
    private int y;
    private static final Logger Logger = LogManager.getLogger(Tetris.class.getName());


    /**
     * El constructor de Position, instancia la posicion inicial del Bloque es decir, x = 0, y = 0.
     * Así se dibujará el bloque arriba a la izquierda al inicio del Board.
     */

    public Position() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Aquí se fuerza a una posición al bloque, donde se le introduce dos valores que queremos que tomen, 
     * x e y, en ese orden.
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * El método getX, devuelve el valor en forma de entero de la posición en X.
     * @return
     */

    public int getX() {
        return this.x;
    }

    /**
     * El método getY, devuelve el valor en forma de entero de la posición en Y.
     * @return
     */

    public int getY() {
        return this.y;
    }

    /**
     * Este método le suma 1 a la posición en X.
     */

    public void moveRight() {
        this.x = this.x + 1;
    }

    /**
     * Este método le resta 1 a la posición en X.
     */

    public void moveLeft() {
        this.x = this.x - 1;
    }

    /**
     * Este método le suma 1 a la posición en Y, es decir baja en el Board.
     */

    public void moveDown() {
        this.y++;
    }

    /**
     * Este método le suma un número determinado de movimietnos a la posición en Y, es decir lo baja esa cantidad
     * de veces en el Board.
     */

    public void moveDown(int move){
        this.y = this.y + move;
        
    }

    /**
     * Este método le resta 1 a la posición en Y, es decir sube en el Board.
     */

    public void moveUp() {
        y--;
    }

    /**
     * Se utiliza para darle un valor específico a la variable en X, es decir darle una posición exacta.
     * @param x
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     * Se utiliza para darle un valor específico a la variable en Y, es decir darle una posición exacta.
     * @param y
     */

    public void setY(int y) {
        this.y = y;
    }
}