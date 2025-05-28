package es.unileon.prg1.tetris;

/**
 * @author Miguel Ruz García & Adrián Pérez Galán
*/

import es.unileon.prg1.tetris.strategy.*;

public class Piece {

    private Color color;
    private String sign;
    private int points;

    /**
     * El constructor de Piece que no recibe nada, se dedica a crear un "bloque" vacío.
     */

    public Piece() {
        this.color = Color.NONE;
        this.sign = "  ";
        this.points = 0;
    }

    /**
     * Cuando el método recibe unos parámetros, es decir el color que tiene el bloque y el símbolo que tiene
     * pinta este de ese color, y con ese símbolo en forma de String.
     * @param color
     * @param sign
     */

    public Piece(Color color, String sign, int points) {
        this.color = color;
        this.sign = sign + " ";
        this.points = points;

    }

    /**
     * Este recibe otra variable de tipo Piece, y le da a color ese color y ese Sign, es decir la forma o el String.
     * @param another
     */

    public Piece(Piece another){
        this.color = another.getColor();
        this.sign = another.getSign();
        this.points = another.getPoints();
    }

    /**
     * Devuelve el color de una Piece.
     * @return
     */

    public Color getColor() {
        return this.color;
    }

    /**
     * Devuelve el caracter "sign" de una Piece.
     * @return
     */

    public String getSign() {
        return this.sign;
    }

    /**
     * Devuelve el valor "points" de una Piece.
     * @return
     */

     public int getPoints() {
        return this.points;
     }

    /**
     * Este método, revisa si ese Piece está vacio, es decir en esa casilla hay un 0.
     * @return
     */

    public boolean isEmpty() {
        return this.sign.trim().length() == 0;
    }
    
    public String toString() {
        return ColorStrategySingleton.getInstance().toString(this);
    }   

}