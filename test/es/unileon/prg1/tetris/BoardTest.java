package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardTest{

    private Board board;
    private Tetris tetris;
    private Block block;


    @Before
    public void setUp() throws TetrisException{
        this.board = new Board(6, 6);
        this.tetris = new Tetris(6, 6, "noColor");
    }

    @Test
    public void testBoardConstructor(){
        assertEquals(6, this.board.getRows());
        assertEquals(6, this.board.getColumns());
    }

    @Test
    public void testBoardValue(){
        assertEquals(1, this.board.getValueBoard(0,0));
        assertEquals(1, this.board.getValueBoard(1,0));
        assertEquals(1, this.board.getValueBoard(2,0));
        assertEquals(1, this.board.getValueBoard(3,0));
        assertEquals(1, this.board.getValueBoard(4,0));
        assertEquals(1, this.board.getValueBoard(5,0));
        assertEquals(1, this.board.getValueBoard(0,5));
        assertEquals(1, this.board.getValueBoard(1,5));
        assertEquals(1, this.board.getValueBoard(2,5));
        assertEquals(1, this.board.getValueBoard(3,5));
        assertEquals(1, this.board.getValueBoard(4,5));
        assertEquals(1, this.board.getValueBoard(5,5));
        assertEquals(1, this.board.getValueBoard(5,1));
        assertEquals(1, this.board.getValueBoard(5,2));
        assertEquals(1, this.board.getValueBoard(5,3));
        assertEquals(1, this.board.getValueBoard(5,4));
        assertEquals(1, this.board.getValueBoard(5,5));
    }


    @Test
    public void testBoardNOValue(){
        assertEquals(0, this.board.getValueBoard(0,1));
        assertEquals(0, this.board.getValueBoard(1,4));
        assertEquals(0, this.board.getValueBoard(2,3));
        assertEquals(0, this.board.getValueBoard(3,3));
        assertEquals(0, this.board.getValueBoard(4,1));
        assertEquals(0, this.board.getValueBoard(4,2));
    }

    @Test
    public void testSetPoints() {
        Board board = new Board(5, 5);
        board.setPoints(10);
        assertEquals(10, board.getPoints());
    }

    @Test
    public void testSetPoints2() {
        Board board = new Board(5, 5);
        int maxInt = Integer.MAX_VALUE;
        board.setPoints(maxInt);
        assertEquals(maxInt, board.getPoints());
    }

    @Test
    public void testSet() {
        Board board = new Board(5, 5);
        Piece piece = new Piece();
        board.set(2, 3, piece);
        assertEquals(piece, board.get(2, 3));
    }

    @Test
    public void testSet2() {
        Board board = new Board(5, 5);
        Piece piece = new Piece();
        board.set(4, 4, piece);
        assertEquals(piece, board.get(4, 4));
    }

    @Test
    public void testLineFullNoFullLines() {
        Board board = new Board(10, 10);
        board.set(0, 0, new Piece(Color.RED, "x", 1));
        board.set(1, 1, new Piece(Color.BLUE, "o", 2));
        boolean areLinesFull = board.lineFull();
        assertFalse(areLinesFull);
    }

    @Test
    public void test_non_empty_line() {
        Board board = new Board(5, 5);
        Piece piece = new Piece(Color.RED, "R", 10);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board.set(i, j, piece);
            }
        }
        int points = board.cleanLine(4);
        assertEquals(50, points);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0) {
                    assertTrue(board.get(i, j).isEmpty());
                } else {
                    assertFalse(board.get(i, j).isEmpty());
                }
            }
        }
    }

    @Test
    public void testBoard3() {
        Board board = new Board(3, 4);
        String expected = "│        │\n" +
                          "│        │\n" +
                          "│        │\n" +
                          "└────────┘\n";
        assertEquals(expected, board.getBoardString());
    }

    

  @Test
    public void testBoardString() {
        Board board = new Board(2, 2);
        board.set(0, 0, new Piece(Color.YELLOW, "O", 1));
        board.set(1, 1, new Piece(Color.YELLOW, "O", 1));
        String expected = "│O   │\n" +
                          "│  O │\n" +
                          "└────┘\n";
        assertEquals(expected, board.getBoardString());
    }

    @Test
    public void testGetValueBoard() {
        Board board = new Board(5, 5);
        int result = board.getValueBoard(2, 0);
        assertEquals(1, result);
    }

    @Test
    public void testGetValueBoard2() {
        Board board = new Board(5, 5);
        int result = board.getValueBoard(-2, 2);
        assertEquals(-1, result);
        int result2 = board.getValueBoard(0, -2);
        assertEquals(-1, result2);
        int result3 = board.getValueBoard(100, 2);
        assertEquals(-1, result3);
        int result4 = board.getValueBoard(2, 100);
        assertEquals(-1, result4);


    }
}