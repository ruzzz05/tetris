package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;



import org.junit.Before;
import org.junit.Test;

public class TetrisTest {

    private Tetris tetris;

    @Before
    public void setUp() throws Exception {
        this.tetris = new Tetris(5, 6, "noColor");
        this.tetris.set(new Block());
    }

    @Test
    public void testTetrisIntInt() throws TetrisException {
        assertEquals("\n" +
                     " O O \n" + //
                     " O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" + 
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test(expected = TetrisException.class)
    public void testTetrisTooFewRows() throws TetrisException {
        new Tetris(4, 10, "noColor");
    }

    @Test(expected = TetrisException.class)
    public void testTetrisTooManyRows() throws TetrisException {
        new Tetris(21, 10, "noColor");
    }

    @Test(expected = TetrisException.class)
    public void testTetrisTooFewColumns() throws TetrisException {
        new Tetris(10, 5, "noColor");
    }

    @Test(expected = TetrisException.class)
    public void testTetrisTooManyColumns() throws TetrisException {
        new Tetris(10, 11, "noColor");
    }

    @Test
    public void testSet() {
        this.tetris.set(new BlockI());
        assertEquals("\n I \n" + //
                     " I \n" + //
                     " I \n" + //
                     " I \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" + 
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test
    public void testCreate() {
        assertEquals(" O O \n" + //
                     " O O \n"
                     , this.tetris.create(1).toString());
        assertEquals(" I \n" + //
                     " I \n" + //
                     " I \n" + //
                     " I \n"
                     , this.tetris.create(2).toString());
        assertEquals("   J \n" + //
                     "   J \n" + //
                     " J J \n" + //
                             ""
                     , this.tetris.create(3).toString());
        assertEquals(" L   \n" + //
                     " L   \n" + //
                     " L L \n" + //
                             ""
                     , this.tetris.create(4).toString());
        assertEquals(" T T T \n" + //
                     "   T   \n" + //
                             ""
                     , this.tetris.create(5).toString());
        assertEquals("   S S \n" + //
                     " S S   \n" + //
                             ""
                     , this.tetris.create(6).toString());
        assertEquals(" Z Z   \n" + //
                     "   Z Z \n" + //
                             ""
                     , this.tetris.create(7).toString());
        assertEquals(" O O \n" + //
                     " O O \n" + //
                             ""
                     , this.tetris.create(0).toString());
    }

    @Test
    public void testGetPoints() throws TetrisException {
        this.tetris.create(1);
        assertEquals(0, this.tetris.getPoints());
        this.tetris.drop();
        this.tetris.create(1);
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.drop();
        this.tetris.create(1);
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.drop();
        assertEquals(12, this.tetris.getPoints());
    }

    @Test
    public void testmoveRight() throws TetrisException {
        assertEquals("\n O O \n" + //
                       " O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" + 
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.moveRight();
        assertEquals("\n   O O \n" + //
                       "   O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test(expected = TetrisException.class)
    public void testMoveRightOut() throws TetrisException {
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
    }

    @Test
    public void testMoveLeft() throws TetrisException {
        this.tetris.moveRight();
        assertEquals("\n   O O \n" + //
                     "   O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.moveLeft();
        assertEquals("\n O O \n" + //
                     " O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test(expected = TetrisException.class)
    public void testMoveLeftOut() throws TetrisException {
        this.tetris.moveLeft();
    }

    @Test
    public void testSpinRight() throws TetrisException {
        this.tetris.set(new BlockT());
        assertEquals("\n T T T \n" + //
                     "   T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" + 
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinRight();
        assertEquals("\n   T \n" + //
                     " T T \n" + //
                     "   T \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" + 
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinRight();
        assertEquals("\n   T   \n" + //
                     " T T T \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinRight();
        assertEquals("\n T   \n" + //
                     " T T \n" + //
                     " T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinRight();
        assertEquals("\n T T T \n" + //
                     "   T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test
    public void testSpinLeft() throws TetrisException {
        this.tetris.set(new BlockT());
        assertEquals("\n T T T \n" + //
                     "   T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinLeft();
        assertEquals("\n T   \n" + //
                     " T T \n" + //
                     " T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinLeft();
        assertEquals("\n   T   \n" + //
                     " T T T \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinLeft();
        assertEquals("\n   T \n" + //
                     " T T \n" + //
                     "   T \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.spinLeft();
        assertEquals("\n T T T \n" + //
                     "   T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test
    public void testDropOT() throws TetrisException {
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockT());
        this.tetris.moveRight();
        assertEquals("\n   T T T \n" + //
                     "     T   \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│O O         │\n" + //
                     "│O O         │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockI());
        assertEquals("\n I \n" + //
                     " I \n" + //
                     " I \n" + //
                     " I \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│  T T T     │\n" + //
                     "│O O T       │\n" + //
                     "│O O         │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertFalse(this.tetris.drop());
    }

    @Test
    public void testDropTT() throws TetrisException {
        this.tetris.set(new BlockT());
        this.tetris.spinLeft();
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockT());
        this.tetris.spinRight();
        assertEquals("\n   T \n" + //
                     " T T \n" + //
                     "   T \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│T           │\n" + //
                     "│T T         │\n" + //
                     "│T           │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertTrue(this.tetris.drop());
        this.tetris.set(new Block());
        assertEquals("\n O O \n" + //
                     " O O \n" + //
                     "│  T         │\n" + //
                     "│T T         │\n" + //
                     "│T T         │\n" + //
                     "│T T         │\n" + //
                     "│T           │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertFalse(this.tetris.drop());
    }

    @Test
    public void testDropOTI() throws TetrisException {
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockT());
        this.tetris.spinLeft();
        this.tetris.spinLeft();
        this.tetris.moveRight();
        this.tetris.moveRight();
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockI());
        this.tetris.spinRight();
        this.tetris.moveRight();
        this.tetris.moveRight();
        assertEquals("\n     I I I I \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│O O   T     │\n" + //
                     "│O O T T T   │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertTrue(this.tetris.drop());
        this.tetris.set(new BlockL());
        this.tetris.moveRight();
        assertEquals("\n   L   \n" + //
                     "   L   \n" + //
                     "   L L \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│    I I I I │\n" + //
                     "│O O   T     │\n" + //
                     "│O O T T T   │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        assertFalse(this.tetris.drop());
    }

    @Test
    public void testToString() throws TetrisException {
        assertEquals("\n O O \n" + //
                     " O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
        this.tetris.drop();
        this.tetris.set(new Block());
        assertEquals("\n O O \n" + //
                     " O O \n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│            │\n" + //
                     "│O O         │\n" + //
                     "│O O         │\n" + //
                     "└────────────┘\n" + //
                     "\nPOINTS: 0\n" +
                     "              ¨W¨ --> spin left    ¨E¨ --> spin right \n" + //
                             " ¨A¨ --> move left    ¨S¨ --> drop    ¨D¨ -->move right \n" + //
                             " ¨Salir¨ to leave tetris)"
                     , this.tetris.toString());
    }

    @Test
    public void testSetPoints(){
        tetris.setPoints(10);
        assertEquals(10, tetris.getPoints());
    }

    @Test
    public void testGetActualBlock() {
        Block block;
        block = tetris.create(0);
        assertEquals(block, tetris.getActuaBlock());
    }


    @Test
    public void test_returns_block_object() {
        Block block = tetris.getBlock();
        assertNotNull(block);
        assertTrue(block instanceof Block);
    }
}