package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

public class PositionTest{

    private Tetris tetris;

    @Before
    public void setUp() throws Exception {
        this.tetris = new Tetris(5, 6, "noColor");
        this.tetris.set(new Block());
    }

    @Test
    public void testGetX0() {
        Position position = new Position();
        int result = position.getX();
        assertEquals(0, result);
    }

    @Test
    public void testGetX5() {
        Position position = new Position(5, 0);
        int result = position.getX();
        assertEquals(5, result);
    }

    @Test
    public void testGetX10() {
        Position position = new Position();
        position.setX(10);
        int result = position.getX();
        assertEquals(10, result);
    }

    @Test
    public void testGetXMAX() {
        Position position = new Position(Integer.MAX_VALUE, 0);
        int result = position.getX();
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void testGetXMIN() {
        Position position = new Position(Integer.MIN_VALUE, 0);
        int result = position.getX();
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    public void testGetXSet() {
        Position position = new Position();
        position.setY(7);
        int result = position.getX();
        assertEquals(0, result);
    }

    @Test
    public void testGetY0() {
        Position position = new Position();
        int result = position.getX();
        assertEquals(0, result);
    }

    @Test
    public void testGetY5() {
        Position position = new Position(5, 0);
        int result = position.getX();
        assertEquals(5, result);
    }

    @Test
    public void testGetY10() {
        Position position = new Position();
        position.setX(10);
        int result = position.getX();
        assertEquals(10, result);
    }

    @Test
    public void testGetYMAX() {
        Position position = new Position(Integer.MAX_VALUE, 0);
        int result = position.getX();
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void testGetYMIN() {
        Position position = new Position(Integer.MIN_VALUE, 0);
        int result = position.getX();
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    public void testGetYSet() {
        Position position = new Position();
        position.setY(7);
        int result = position.getX();
        assertEquals(0, result);
    }

    @Test
    public void testMoveRight1() {
        Position position = new Position();
        int initialX = position.getX();
        position.moveRight();
        int newX = position.getX();
        assertEquals(initialX + 1, newX);
    }

    @Test
    public void testMoveRightYSame() {
        Position position = new Position();
        int initialY = position.getY();
        position.moveRight();
        int newY = position.getY();
        assertEquals(initialY, newY);
    }

    @Test
    public void testPosition1() {
        Position position = new Position(0, 0);
        int initialX = position.getX();
        position.moveRight();
        int newX = position.getX();
        assertEquals(initialX + 1, newX);
    }

    @Test
    public void testPosition3() {
        Position position = new Position();
        int initialX = position.getX();
        position.moveRight();
        position.moveRight();
        position.moveRight();
        int newX = position.getX();
        assertEquals(initialX + 3, newX);
    }

    @Test
    public void testMoveLeft1() {
        Position position = new Position(5, 0);
        position.moveLeft();
        assertEquals(4, position.getX());
    }

    @Test
    public void testMoveLeft2() {
        Position position = new Position(5, 0);
        position.moveLeft();
        position.moveLeft();
        assertEquals(3, position.getX());
    }

    @Test
    public void testMoveLeftMinus1() {
        Position position = new Position(0, 0);
        position.moveLeft();
        assertEquals(-1, position.getX());
    }

    @Test
    public void testMoveleftX() {
        Position position = new Position(Integer.MIN_VALUE, 0);
        position.moveLeft();
        assertEquals(Integer.MIN_VALUE - 1, position.getX());
    }

    @Test
    public void testMoveLeftY() {
        Position position = new Position(5, 10);
        position.moveLeft();
        assertEquals(10, position.getY());
    }

    @Test
    public void testDown1() {
        Position position = new Position();
        int initialY = position.getY();
        position.moveDown();
        int newY = position.getY();
        assertEquals(initialY + 1, newY);
    }

    @Test
    public void testDown3() {
        Position position = new Position();
        int initialY = position.getY();
        position.moveDown();
        position.moveDown();
        position.moveDown();
        int newY = position.getY();
        assertEquals(initialY + 3, newY);
    }

    @Test
    public void testDownX() {
        Position position = new Position();
        int initialX = position.getX();
        position.moveDown();
        int newX = position.getX();
        assertEquals(initialX, newX);
    }

    @Test
    public void test_maxYCoordinate() {
        Position position = new Position();
        position.setY(Integer.MAX_VALUE);
        int initialY = position.getY();
        position.moveDown();
        int newY = position.getY();
        assertEquals(initialY + 1, newY);
    }

    @Test
    public void testDownSet() {
        Position position = new Position(0, 0);
        position.moveDown(1);
        assertEquals(1, position.getY());
    }

    @Test
    public void testDownSet3() {
        Position position = new Position(0, 2);
        position.moveDown(3);
        assertEquals(5, position.getY());
    }


    @Test
    public void testMoveUp1() {
        Position position = new Position();
        int initialY = position.getY();
    
        position.moveUp();
    
        assertEquals(initialY - 1, position.getY());
    }

    @Test
    public void testMoveUp3() {
        Position position = new Position();
        int initialY = position.getY();
        int timesCalled = 3;
    
        for (int i = 0; i < timesCalled; i++) {
            position.moveUp();
        }
    
        assertEquals(initialY - timesCalled, position.getY());
    }

    @Test
    public void testMoveUpDown() {
        Position position = new Position();
        int boardHeight = 10;
    
        position.moveDown(boardHeight);
        assertEquals(boardHeight, position.getY());
    
        position.moveUp();
        assertEquals(9, position.getY());
    }

    @Test
    public void testSet() {
        Position position = new Position();
        position.setX(5);
        assertEquals(5, position.getX());
    }

    @Test
    public void testSet2() {
        Position position = new Position();
        position.setY(3);
        position.setX(5);
        assertEquals(3, position.getY());
    }

    @Test
    public void testSet0() {
        Position position = new Position();
        position.setX(0);
        assertEquals(0, position.getX());
    }

    @Test
    public void testSetMAX() {
        Position position = new Position();
        position.setX(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, position.getX());
    }

    @Test
    public void testSetNegative() {
        Position position = new Position();
        position.setX(-5);
        assertEquals(-5, position.getX());
    }

    @Test
    public void testSetY() {
        Position position = new Position();
        position.setY(5);
        assertEquals(5, position.getY());
    }

    @Test
    public void testSetY2() {
        Position position = new Position();
        position.setY(3);
        position.setX(5);
        assertEquals(5, position.getX());
    }

    @Test
    public void testSetY0() {
        Position position = new Position();
        position.setY(0);
        assertEquals(0, position.getY());
    }

    @Test
    public void testSetYMAX() {
        Position position = new Position();
        position.setY(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, position.getY());
    }

    @Test
    public void testSetYNegative() {
        Position position = new Position();
        position.setY(-5);
        assertEquals(-5, position.getY());
    }


}