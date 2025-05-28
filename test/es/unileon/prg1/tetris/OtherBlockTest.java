package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OtherBlockTest {
    
    private BlockI blockI;
    private BlockJ blockJ;
    private BlockL blockL;
    private BlockS blockS;
    private BlockT blockT;
    private BlockZ blockZ;
    private Tetris tetris;

    @Before
    public void setUp() throws Exception {
        this.blockI = new BlockI();
        this.blockJ = new BlockJ();
        this.blockL = new BlockL();
        this.blockS = new BlockS();
        this.blockT = new BlockT();
        this.blockZ = new BlockZ();
    }

    @Test
    public void testToString() {
        assertEquals(" I \n" +
                     " I \n" +
                     " I \n" +
                     " I \n" +
                     ""
                     , this.blockI.toString());

        assertEquals("   J \n" +
                     "   J \n" +
                     " J J \n" +
                      ""
                     , this.blockJ.toString());

        assertEquals(" L   \n" +
                     " L   \n" +
                     " L L \n" +
                     ""
                     , this.blockL.toString());

        assertEquals("   S S \n" +
                     " S S   \n" +
                     ""
                     , this.blockS.toString());

        assertEquals(" Z Z   \n" +
                     "   Z Z \n" +
                     ""
                     , this.blockZ.toString());

        assertEquals(" T T T \n" +
                     "   T   \n" +
                     ""
                     , this.blockT.toString());
    }

    @Test
    public void testSpinRight() {
        this.blockI.spinRight(6);
        assertEquals(" I I I I \n", this.blockI.toString());

        this.blockJ.spinRight(6);
        assertEquals(" J     \n" +
                     " J J J \n"
                     , this.blockJ.toString());
                 
        this.blockL.spinRight(6);
        assertEquals(" L L L \n" +
                     " L     \n"
                     , this.blockL.toString());

        this.blockS.spinRight(6);
        assertEquals(" S   \n" +
                     " S S \n" +
                     "   S \n"
                     , this.blockS.toString());

        this.blockZ.spinRight(6);
        assertEquals("   Z \n" +
                     " Z Z \n" +
                     " Z   \n"
                     , this.blockZ.toString());

        this.blockT.spinRight(6);
        assertEquals("   T \n" +
                     " T T \n" +
                     "   T \n"
                     , this.blockT.toString());

        BlockI blockI2 = new BlockI();
        blockI2.moveRight();
        blockI2.moveRight();
        blockI2.moveRight();
        blockI2.spinRight(6);
        assertEquals(2, blockI2.getBlockPosX());

        BlockI blockI3 = new BlockI();
        blockI3.position.setX(-1);
        blockI3.spinRight(6);
        assertEquals(0, blockI3.getBlockPosX());
    }

    @Test
    public void testSpinLeft() {
        this.blockI.spinLeft(6);
        /*assertEquals(" I I I I \n", this.blockI.toString());*/

        this.blockJ.spinLeft(6);
        assertEquals(" J J J \n" +
                     "     J \n"
                     , this.blockJ.toString());
                     
        this.blockL.spinLeft(6);
        assertEquals("     L \n" +
                     " L L L \n"
                     , this.blockL.toString());

        this.blockS.spinLeft(6);
        assertEquals(" S   \n" +
                     " S S \n" +
                     "   S \n"
                     , this.blockS.toString());

        this.blockZ.spinLeft(6);
        assertEquals("   Z \n" +
                     " Z Z \n" +
                     " Z   \n"
                     , this.blockZ.toString());

        this.blockT.spinLeft(6);
        assertEquals(" T   \n" +
                     " T T \n" +
                     " T   \n"
                     , this.blockT.toString());

        BlockI blockI2 = new BlockI();
        blockI2.moveRight();
        blockI2.moveRight();
        blockI2.moveRight();
        blockI2.spinLeft(6);
        assertEquals(2, blockI2.getBlockPosX());
        
        BlockI blockI3 = new BlockI();
        blockI3.position.setX(-1);
        blockI3.spinLeft(6);
        assertEquals(0, blockI3.getBlockPosX());
    }

    @Test
    public void testGetBlockPos(){
        assertTrue(0==this.blockI.getBlockPosX());
        assertTrue(0==this.blockI.getBlockPosY());
        assertFalse(0!=this.blockI.getBlockPosX());

        assertTrue(0==this.blockJ.getBlockPosX());
        assertTrue(0==this.blockJ.getBlockPosY());
        assertFalse(0!=this.blockJ.getBlockPosX());

        assertTrue(0==this.blockL.getBlockPosX());
        assertTrue(0==this.blockL.getBlockPosY());
        assertFalse(0!=this.blockL.getBlockPosX());

        assertTrue(0==this.blockS.getBlockPosX());
        assertTrue(0==this.blockS.getBlockPosY());
        assertFalse(0!=this.blockS.getBlockPosX());

        assertTrue(0==this.blockZ.getBlockPosX());
        assertTrue(0==this.blockZ.getBlockPosY());
        assertFalse(0!=this.blockZ.getBlockPosX());

        assertTrue(0==this.blockT.getBlockPosX());
        assertTrue(0==this.blockT.getBlockPosY());
        assertFalse(0!=this.blockT.getBlockPosX());
    }

    @Test
    public void testMoveRight() {
        assertTrue(0==this.blockI.getBlockPosX());
            this.blockI.moveRight();
        assertTrue(1==this.blockI.getBlockPosX());
            this.blockI.moveRight();
        assertTrue(1!=this.blockI.getBlockPosX());
        assertTrue(2==this.blockI.getBlockPosX());


        assertTrue(0==this.blockJ.getBlockPosX());
            this.blockJ.moveRight();
        assertTrue(1==this.blockJ.getBlockPosX());
            this.blockJ.moveRight();
        assertTrue(1!=this.blockJ.getBlockPosX());
        assertTrue(2==this.blockJ.getBlockPosX());


        assertTrue(0==this.blockL.getBlockPosX());
            this.blockL.moveRight();
        assertTrue(1==this.blockL.getBlockPosX());
            this.blockL.moveRight();
        assertTrue(1!=this.blockL.getBlockPosX());
        assertTrue(2==this.blockL.getBlockPosX());


        assertTrue(0==this.blockS.getBlockPosX());
            this.blockS.moveRight();
        assertTrue(1==this.blockS.getBlockPosX());
            this.blockS.moveRight();
        assertTrue(1!=this.blockS.getBlockPosX());
        assertTrue(2==this.blockS.getBlockPosX());


        assertTrue(0==this.blockZ.getBlockPosX());
            this.blockZ.moveRight();
        assertTrue(1==this.blockZ.getBlockPosX());
            this.blockZ.moveRight();
        assertTrue(1!=this.blockZ.getBlockPosX());
        assertTrue(2==this.blockZ.getBlockPosX());


        assertTrue(0==this.blockT.getBlockPosX());
            this.blockT.moveRight();
        assertTrue(1==this.blockT.getBlockPosX());
            this.blockT.moveRight();
        assertTrue(1!=this.blockT.getBlockPosX());
        assertTrue(2==this.blockT.getBlockPosX());
    }

   

    @Test
    public void testMoveLeft() {
        assertTrue(0==this.blockI.getBlockPosX());
            this.blockI.moveRight();
            this.blockI.moveRight();
        assertTrue(2==this.blockI.getBlockPosX());
            this.blockI.moveLeft();
        assertTrue(2!=this.blockI.getBlockPosX());
        assertTrue(1==this.blockI.getBlockPosX());


        assertTrue(0==this.blockJ.getBlockPosX());
            this.blockJ.moveRight();
            this.blockJ.moveRight();
        assertTrue(2==this.blockJ.getBlockPosX());
            this.blockJ.moveLeft();
        assertTrue(2!=this.blockJ.getBlockPosX());
        assertTrue(1==this.blockJ.getBlockPosX());


        assertTrue(0==this.blockL.getBlockPosX());
            this.blockL.moveRight();
            this.blockL.moveRight();
        assertTrue(2==this.blockL.getBlockPosX());
            this.blockL.moveLeft();
        assertTrue(2!=this.blockL.getBlockPosX());
        assertTrue(1==this.blockL.getBlockPosX());


        assertTrue(0==this.blockS.getBlockPosX());
            this.blockS.moveRight();
            this.blockS.moveRight();
        assertTrue(2==this.blockS.getBlockPosX());
            this.blockS.moveLeft();
        assertTrue(2!=this.blockS.getBlockPosX());
        assertTrue(1==this.blockS.getBlockPosX());


        assertTrue(0==this.blockZ.getBlockPosX());
            this.blockZ.moveRight();
            this.blockZ.moveRight();
        assertTrue(2==this.blockZ.getBlockPosX());
            this.blockZ.moveLeft();
        assertTrue(2!=this.blockZ.getBlockPosX());
        assertTrue(1==this.blockZ.getBlockPosX());


        assertTrue(0==this.blockT.getBlockPosX());
            this.blockT.moveRight();
            this.blockT.moveRight();
        assertTrue(2==this.blockT.getBlockPosX());
            this.blockT.moveLeft();
        assertTrue(2!=this.blockT.getBlockPosX());
        assertTrue(1==this.blockT.getBlockPosX());
    }



}
