package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class PieceTest{

    private Tetris tetris;

    @Before
    public void setUp() throws Exception {
        this.tetris = new Tetris(5, 6, "noColor");
        this.tetris.set(new Block());
    }

    @Test
    public void testGetColor() {
        Piece yellowPiece = new Piece(Color.YELLOW, "Y", 0);
        assertEquals(Color.YELLOW, yellowPiece.getColor());

        Piece whitePiece = new Piece(Color.WHITE, "W", 0);
        assertEquals(Color.WHITE, whitePiece.getColor());

        Piece greenPiece = new Piece(Color.GREEN, "G", 0);
        assertEquals(Color.GREEN, greenPiece.getColor());

        Piece cyanPiece = new Piece(Color.CYAN, "C", 0);
        assertEquals(Color.CYAN, cyanPiece.getColor());

        Piece bluePiece = new Piece(Color.BLUE, "B", 0);
        assertEquals(Color.BLUE, bluePiece.getColor());

        Piece redPiece = new Piece(Color.RED, "R", 0);
        assertEquals(Color.RED, redPiece.getColor());

        Piece magentaPiece = new Piece(Color.MAGENTA, "M", 0);
        assertEquals(Color.MAGENTA, magentaPiece.getColor());

        Piece blackPiece = new Piece(Color.BLACK, "K", 0);
        assertEquals(Color.BLACK, blackPiece.getColor());
    }



    @Test
    public void testGetSign() {
        Piece yellowPiece = new Piece(Color.YELLOW, "Y", 0);
        assertEquals("Y ", yellowPiece.getSign());

        Piece whitePiece = new Piece(Color.WHITE, "W", 0);
        assertEquals("W ", whitePiece.getSign());

        Piece greenPiece = new Piece(Color.GREEN, "G", 0);
        assertEquals("G ", greenPiece.getSign());

        Piece cyanPiece = new Piece(Color.CYAN, "C", 0);
        assertEquals("C ", cyanPiece.getSign());

        Piece bluePiece = new Piece(Color.BLUE, "B", 0);
        assertEquals("B ", bluePiece.getSign());

        Piece redPiece = new Piece(Color.RED, "R", 0);
        assertEquals("R ", redPiece.getSign());

        Piece magentaPiece = new Piece(Color.MAGENTA, "M", 0);
        assertEquals("M ", magentaPiece.getSign());

        Piece blackPiece = new Piece(Color.BLACK, "K", 0);
        assertEquals("K ", blackPiece.getSign());
    }

    @Test
    public void testSignIsEmpty() {
        Piece piece = new Piece(Color.NONE, "", 0);
        assertTrue(piece.isEmpty());
    }

    @Test
    public void testSignIsNotEmpty() {
        Piece piece = new Piece(Color.NONE, "Y", 0);
        assertFalse(piece.isEmpty());
    }

    



    
}