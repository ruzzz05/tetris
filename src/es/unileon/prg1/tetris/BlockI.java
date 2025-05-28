package es.unileon.prg1.tetris;

/**
 * Esta clase pertenece a los Block*, es decir, a los subtipos de Block. Este en concreto es el BlockT, que tiene forma de T.
 * Estos están formados por un ArrayMxN, que es donde se guarda la forma del bloque, una posición. Se inicializa la posición
 * es decir se pone a 0, y se crea una pieza, que es la que se va a dibujar en el tablero, esta tiene el color, el símbolo y
 * los puntos que vale al eliminar una línea.
 * @author Miguel Ruz García  y Adrián Pérez Galán
*/



public class BlockI extends Block {

    public BlockI() {
        drop = false;
        this.block = new ArrayMxN(4,1);
        this.block.set(0, 0, 1);
        this.block.set(1, 0, 1);
        this.block.set(2, 0, 1);
        this.block.set(3, 0, 1);
        this.position = new Position();
        this.piece = new Piece(Color.CYAN, "I", 1);
    }
}
