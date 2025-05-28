package es.unileon.prg1.tetris;

/**
 * Esta clase pertenece a los Block*, es decir, a los subtipos de Block. Este en concreto es el BlockT, que tiene forma de T.
 * Estos están formados por un ArrayMxN, que es donde se guarda la forma del bloque, una posición. Se inicializa la posición
 * es decir se pone a 0, y se crea una pieza, que es la que se va a dibujar en el tablero, esta tiene el color, el símbolo y
 * los puntos que vale al eliminar una línea.
 * @author Miguel Ruz García  y Adrián Pérez Galán
*/


public class BlockJ extends Block {




    public BlockJ() {
        drop = false;
        this.block = new ArrayMxN(3,2);
        this.block.set(0, 1, 1);
        this.block.set(1, 1, 1);
        this.block.set(2, 1, 1);
        this.block.set(2, 0, 1);
        this.position = new Position();
        this.piece = new Piece(Color.BLUE, "J", 2);
    }
} 
