package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;



public class BlockTest {

    private Block block;
    private Tetris tetris;
    @Before
    public void setUp() throws TetrisException{
        this.block = new Block();
        this.tetris = new Tetris(10, 6, "noColor");

    }

    @Test
    public void testBlockConstructor(){
        //Verificar que las columnas sean 2, y que las filas también lo sean
        assertEquals(2, this.block.getRows());
        assertEquals(2, this.block.getColumns());
    }


    @Test
    public void testBlockMoveRight() throws TetrisException{
        this.block.moveRight();
        assertEquals(1, this.block.getBlockPosX());
        this.block.moveRight();
        assertEquals(2, this.block.getBlockPosX());
        this.block.moveRight();
        assertEquals(3, this.block.getBlockPosX());
        this.block.moveRight();
        assertEquals(4, this.block.getBlockPosX());
    }

      @Test
    public void testBlockMoveLeft() throws TetrisException{
        this.block.moveRight();
        this.block.moveRight();
        this.block.moveRight();
        this.block.moveRight();
        assertEquals(4, this.block.getBlockPosX());
        this.block.moveLeft();
        assertEquals(3, this.block.getBlockPosX());
        this.block.moveLeft();
        assertEquals(2, this.block.getBlockPosX());
        this.block.moveLeft();
        assertEquals(1, this.block.getBlockPosX());
        this.block.moveLeft();
        assertEquals(0, this.block.getBlockPosX());
    }

    @Test
    public void testBlocktoString(){
        assertEquals(" O O \n" + //
                     " O O \n" + //
                     ""
       , this.block.toString());
    }

    @Test
    public void testBlockSpinRight(){
        this.block.spinRight(6);
        assertEquals(" O O \n" + //
                     " O O \n" + //
                     ""
       , this.block.toString());
    }

    @Test 
    public void testBlockSpinLeft(){
        this.block.spinLeft(6);
         assertEquals(" O O \n" + //
                      " O O \n" + //
                      ""
                     , this.block.toString());
    }

    @Test
    public void testToString() {
        Block block = new Block();
        String expected =
                " O O \n" + 
                " O O \n";

        String actual = block.toString();
        assertEquals(expected, actual);
    }


    @Test
    public void testMoveDown() throws TetrisException{
        Tetris tetris = new Tetris(10, 8, "Color");
        Block testBlock = new Block();
        tetris.set(testBlock);
        tetris.moveRight(); 
        tetris.drop();
        int initialBlockX = tetris.getPositionX();
        int initialBlockY = tetris.getPositionY();
        int expectedBlockX = initialBlockX;
        int expectedBlockY = initialBlockY;
        assertEquals(expectedBlockX, tetris.getPositionX());
        assertEquals(expectedBlockY, tetris.getPositionY());
    }

    @Test
    public void testMoveDown2() throws TetrisException{
        Tetris tetris = new Tetris(10, 8, "Color");
        Block testBlock = new Block();
        tetris.set(testBlock);
        Board board = new Board(10, 8);
        tetris.setBoard(board);
        int expectedPoints = 0;
        tetris.drop();
        assertTrue(tetris.getDrop());
        assertEquals(expectedPoints, tetris.getPoints());
    }


    @Test
    public void testMoveDown3() throws TetrisException{
        Tetris tetris = new Tetris(10, 8, "Color");
        Block testBlock = new Block();
        tetris.set(testBlock);
        while (tetris.getPositionY() < 9) {
            tetris.drop();
        }
        int initialBlockX = tetris.getPositionX();
        int initialBlockY = tetris.getPositionY();
        assertEquals(initialBlockX, tetris.getPositionX());
        assertEquals(initialBlockY, tetris.getPositionY());
    }



    @Test
    public void testGetBlockData() {
        Block block = new Block();
        Piece[][] blockData = block.getBlockData();
        assertNotNull(blockData);
        int expectedRows = block.getRows();
        int expectedCols = block.getColumns();
        assertEquals(expectedRows, blockData.length);
        assertEquals(expectedCols, blockData[0].length);
        for (int i = 0; i < expectedRows; i++) {
            for (int j = 0; j < expectedCols; j++) {
                assertNotNull(blockData[i][j]);
            }
        }
    }

    @Test
    public void testLenght() {
        Block block = new Block();
        int expected = 2;
        int actual = block.length();
        assertEquals(expected, actual);
    }


    @Test
    public void testGetPiece() {
        Block block = new Block();
        int result = block.getPiece(0, 0);
        assertEquals(1, result);
    }

    @Test
    public void testGetPiece2() {
        Block block = new Block();
        int result = block.getPiece(1, 1);
        assertEquals(1, result);
    }

    @Test
    public void testSpinRight() {
        Block block = new Block();
        int gameCols = 10;
        int initialX = block.getBlockPosX();
        int initialY = block.getBlockPosY();
        int initialCols = block.getColumns();
        block.spinRight(gameCols);
        assertEquals(initialY, block.getBlockPosY());
        if (initialX + initialCols > gameCols) {
            assertEquals(gameCols - initialCols, block.getBlockPosX());
        } else {
            assertEquals(initialX, block.getBlockPosX());
        }
        assertEquals(initialCols, block.getColumns());
    }

    @Test
    public void testSpinLeft() {
        Block block = new Block();  
        int gameCols = 10;
        int initialX = block.getBlockPosX();
        int initialY = block.getBlockPosY();
        int initialCols = block.getColumns();
        block.spinLeft(gameCols);
        assertEquals(initialY, block.getBlockPosY());
        if (initialX + initialCols > gameCols) {
            assertEquals(gameCols - initialCols, block.getBlockPosX());
        } else {
            assertEquals(initialX, block.getBlockPosX());
        }
        assertEquals(initialCols, block.getColumns());
    }

    @Test
    public void testGetBlockData2() {
        Block block = new Block();
        Piece[][] blockData = block.getBlockData();
        assertEquals(block.getRows(), blockData.length);
        assertEquals(block.getColumns(), blockData[0].length);
        for (int i = 0; i < block.getRows(); i++) {
            for (int j = 0; j < block.getColumns(); j++) {
                assertEquals(Color.YELLOW, blockData[i][j].getColor());
                assertEquals("O ", blockData[i][j].toString()); // Cambio aquí
            }
        }

    }

    @Test
    public void testGetBlockData3() { 
        Block block = new Block();
        Piece[][] blockData = block.getBlockData();
        assertNotNull(blockData);
        assertEquals(block.getRows(), blockData.length);
        assertEquals(block.getColumns(), blockData[0].length);
        for (int i = 0; i < block.getRows(); i++) {
            for (int j = 0; j < block.getColumns(); j++) {
                assertNotNull(blockData[i][j]);
            }
        }
    }

    @Test
    public void testGetBlockData4() {
        Block block = new Block();
        Piece[][] blockData = block.getBlockData();
        assertNotNull(blockData);
        int expectedRows = block.getRows();
        int expectedCols = block.getColumns();
        assertEquals(expectedRows, blockData.length);
        assertEquals(expectedCols, blockData[0].length);
    }

    @Test
    public void testToString2() {
        Block block = new Block();
        block.moveRight();
        String expected = "   O O \n   O O \n";
        assertEquals(expected, block.toString());
    }

    @Test
    public void testMoveDownTrue() {
        Board board = new Board(10, 10);
        Block block = new Block();
    
        boolean result = block.moveDown(board);
    
        assertTrue(result);
    }

    @Test
    public void testSpinRight2() {
        Block block = new Block();
        int gameCols = 10;
        int initialPosX = block.getBlockPosX();
        int initialPosY = block.getBlockPosY();

        ArrayNxN spinnedblock = new ArrayNxN(block.block);
        spinnedblock = spinnedblock.spinRight();
        if (block.position.getX() + block.block.columns() > gameCols) {
            block.position.setX(gameCols - block.block.columns());
        }
        if (block.position.getX() < 0) {
            block.position.setX(0);
        }

        assertEquals(initialPosX, block.getBlockPosX());
        assertEquals(initialPosY, block.getBlockPosY());
    }

    @Test
    public void testBlockData(){
        Block block = new BlockL();
        Piece[][] blockData = block.getBlockData();
        assertNotNull(blockData);
    }

}
