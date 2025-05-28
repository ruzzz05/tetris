package es.unileon.prg1.tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ArrayNxNTest {

    private ArrayNxN array2, array3, array4;

    @Before
    public void setUp() throws Exception {
        this.array2 = new ArrayNxN(2);

        this.array3 = new ArrayNxN(3);
        array3.set(0, 0, 1);
        array3.set(0, 1, 1);
        array3.set(0, 2, 1);
        array3.set(1, 1, 1);

        this.array4 = new ArrayNxN(4);
        array4.set(0, 3, 1);
        array4.set(1, 3, 1);
        array4.set(2, 3, 1);
        array4.set(3, 3, 1);
    }

    @Test
    public void testArrayInt() {
        assertEquals("     \n" + //
                     "     \n", this.array2.toString());
        assertEquals(" 1 1 1 \n" + //
                     "   1   \n" + //
                     "       \n" //
                , this.array3.toString());
        assertEquals("       1 \n" + //
                     "       1 \n" + //
                     "       1 \n" + //
                     "       1 \n", this.array4.toString());
    }

    @Test
    public void testArrayArray(){
        ArrayMxN newArray = new ArrayMxN(4,1);
        newArray.set(0,0,1);
        newArray.set(1,0,1);
        newArray.set(2,0,1);
        newArray.set(3,0,1);
        assertEquals(" 1       \n" + //
                     " 1       \n" + //
                     " 1       \n" + //
                     " 1       \n" //
                     , new ArrayNxN(newArray).toString('1'));

    }

    @Test
    public void testGet() {
        assertEquals(1, this.array3.get(0, 0));
        assertEquals(1, this.array3.get(0, 1));
        assertEquals(1, this.array3.get(0, 2));
        assertEquals(0, this.array3.get(1, 0));
        assertEquals(1, this.array3.get(1, 1));
        assertEquals(0, this.array3.get(1, 2));
        assertEquals(0, this.array3.get(2, 0));
        assertEquals(0, this.array3.get(2, 1));
        assertEquals(0, this.array3.get(2, 2));
    }

    @Test
    public void testGetLessThanMinRow() {
        assertEquals(Integer.MIN_VALUE, this.array3.get(-1, 0));
    }

    @Test
    public void testGetMoreThanMaxRow() {
        assertEquals(Integer.MIN_VALUE, this.array3.get(3, 0));
    }

    @Test
    public void testGetLessThanMinColumn() {
        assertEquals(Integer.MIN_VALUE, this.array3.get(0, -1));
    }

    @Test
    public void testGetMoreThanMaxColumn() {
        assertEquals(Integer.MIN_VALUE, this.array3.get(0, 3));
    }

    @Test
    public void testSet() {
        assertEquals(1, this.array3.get(0, 0));
        assertTrue(this.array3.set(0, 0, 0));
        assertEquals(0, this.array3.get(0, 0));
    }

    @Test
    public void testSetLessThanMinRow() {
        assertFalse(this.array3.set(-1, 0, 0));
    }

    @Test
    public void testSetMoreThanMaxRow() {
        assertFalse(this.array3.set(3, 0, 0));
    }

    @Test
    public void testSetLessThanMinColumn() {
        assertFalse(this.array3.set(0, -1, 0));
    }

    @Test
    public void testSetMoreThanMaxColumn() {
        assertFalse(this.array3.set(0, 3, 0));
    }

    @Test
    public void testGetIdentity() {
        assertEquals(" 1   \n" + //
                "   1 \n" //
                , this.array2.getIdentity().toString());
        assertEquals(" 1     \n" + //
                     "   1   \n" + //
                     "     1 \n" //
                , this.array3.getIdentity().toString());
        assertEquals(" 1       \n" + //
                     "   1     \n" + //
                     "     1   \n" + //
                     "       1 \n" //
                , this.array4.getIdentity().toString());
    }

    @Test
    public void testMirrorH() {
        ArrayNxN minArray = new ArrayNxN(1);
        minArray.set(0,0,0);
        assertEquals("   \n"
                , minArray.toString());
        assertEquals("     \n" + //
                     "     \n" //
                , this.array2.mirrorH().toString());
        assertEquals("       \n" + //
                     "   1   \n" + //
                     " 1 1 1 \n" //
                , this.array3.mirrorH().toString());
        assertEquals("       1 \n" + //
                     "       1 \n" + //
                     "       1 \n" + //
                     "       1 \n" //
                , this.array4.mirrorH().toString());
    }

    @Test
    public void testMirrorV() {
        assertEquals("     \n" + //
                     "     \n" //
                , this.array2.mirrorV().toString());
        assertEquals(" 1 1 1 \n" + //
                     "   1   \n" + //
                     "       \n" //
                , this.array3.mirrorV().toString());
        assertEquals(" 1       \n" + //
                     " 1       \n" + //
                     " 1       \n" + //
                     " 1       \n" //
                , this.array4.mirrorV().toString());
    }

    @Test
    public void testTranspose() {
        assertEquals("     \n" + //
                     "     \n" //
                , this.array2.transpose().toString());
        assertEquals(" 1     \n" + //
                     " 1 1   \n" + //
                     " 1     \n" //
                , this.array3.transpose().toString());
        assertEquals("         \n" + //
                     "         \n" + //
                     "         \n" + //
                     " 1 1 1 1 \n" //
                , this.array4.transpose().toString());
    }

    @Test
    public void testMultiply() {
        assertEquals(" 1 1 1 \n" + //
                     "   1   \n" + //
                     "       \n" //
                , this.array3.multiply(this.array3.getIdentity()).toString());
        assertEquals("       1 \n" + //
                     "       1 \n" + //
                     "       1 \n" + //
                     "       1 \n" //
                , this.array4.multiply(this.array4.getIdentity()).toString());
    }

    @Test
    public void testMultiplyWrongDimension() {
        assertNull(this.array3.multiply(this.array4.getIdentity()));
        assertNull(this.array4.multiply(this.array3.getIdentity()));
    }

    @Test
    public void testSpinRight() {
        ArrayNxN spinned;
        spinned = this.array3.spinRight();
        assertEquals("     1 \n" + //
                     "   1 1 \n" + //
                     "     1 \n" //
                , spinned.toString());
        spinned = spinned.spinRight();
        assertEquals("       \n" + //
                     "   1   \n" + //
                     " 1 1 1 \n" //
                , spinned.toString());
        spinned = spinned.spinRight();
        assertEquals(" 1     \n" + //
                     " 1 1   \n" + //
                     " 1     \n" //
                , spinned.toString());
        spinned = spinned.spinRight();
        assertEquals(" 1 1 1 \n" + //
                     "   1   \n" + //
                     "       \n" //
                , spinned.toString());
    }

    @Test
    public void testSpinLeft() {
        ArrayNxN spinned;
        spinned = this.array3.spinLeft();
        assertEquals(" 1     \n" + //
                     " 1 1   \n" + //
                     " 1     \n" //
                , spinned.toString());
        spinned = spinned.spinLeft();
        assertEquals("       \n" + //
                     "   1   \n" + //
                     " 1 1 1 \n" //
                , spinned.toString());
        spinned = spinned.spinLeft();
        assertEquals("     1 \n" + //
                     "   1 1 \n" + //
                     "     1 \n" //
                , spinned.toString());
        spinned = spinned.spinLeft();
        assertEquals(" 1 1 1 \n" + //
                     "   1   \n" + //
                     "       \n" //
                , spinned.toString());
    }

    @Test
    public void testToString() {
        assertEquals(" 1 1 1 \n" + //
                "   1   \n" + //
                "       \n" //
                , this.array3.toString());
        assertEquals("       1 \n" + //
                "       1 \n" + //
                "       1 \n" + //
                "       1 \n", this.array4.toString());
    }

    @Test
    public void testNoEmptyColumnsLeft() {
        ArrayNxN array = new ArrayNxN(3);
        array.set(0, 0, 1);
        array.set(1, 0, 1);
        array.set(2, 0, 1);
    
        int result = array.getEmptyColumnsLeft();
    
        assertEquals(0, result);
    }

    @Test
    public void testEmptyColumn() {
        ArrayNxN array = new ArrayNxN(3);
        array.set(0, 1, 1);
        array.set(1, 1, 1);
        array.set(2, 1, 1);
    
        int result = array.getEmptyColumnsLeft() + array.getEmptyColumnsRight();
    
        assertEquals(2, result);
    }


    @Test
    public void testAllCellsEmpty() {
        ArrayNxN array = new ArrayNxN(3);
        ArrayMxN minArray = array.getMinArray();
        assertNull(minArray);
    }

    @Test
    public void testSameSize() {
        ArrayNxN array = new ArrayNxN(3);
        array.set(0, 0, 1);
        array.set(0, 1, 1);
        array.set(0, 2, 1);
        array.set(1, 0, 1);
        array.set(1, 1, 1);
        array.set(1, 2, 1);
        array.set(2, 0, 1);
        array.set(2, 1, 1);
        array.set(2, 2, 1);
        //ArrayMxN minArray = array.getMinArray();
        //assertNotNull(minArray);
        //assertEquals(3, minArray.rows());
        //assertEquals(3, minArray.columns());
    }
    @Test
    public void testGetEmptyColumnsLeft() {
        ArrayMxN arrayMxN = new ArrayMxN(3, 3);
        arrayMxN.set(0, 0, 1);
        arrayMxN.set(1, 1, 1);
        ArrayNxN arrayNxN = new ArrayNxN(arrayMxN);
        assertEquals("Todas las columnas de la izquierda estarían ocupadas", 0, arrayNxN.getEmptyColumnsLeft());

        arrayNxN.set(0, 0, 0);
        arrayNxN.set(1, 1, 1);

        assertEquals("Solo debería haber una columna vacía (la primera a la izquierda)", 1, arrayNxN.getEmptyColumnsLeft());
    }

    @Test
    public void test_all_cells_filled() {
        ArrayNxN array = new ArrayNxN(3);
        array.set(0, 0, 1);
        array.set(0, 1, 1);
        array.set(0, 2, 1);
        array.set(1, 0, 1);
        array.set(1, 1, 1);
        array.set(1, 2, 1);
        array.set(2, 0, 1);
        array.set(2, 1, 1);
        array.set(2, 2, 1);
    }

    @Test
    public void testGetMinArray() {
        ArrayNxN arrayNxN = new ArrayNxN(3);
        arrayNxN.set(0, 0, 1);
        arrayNxN.set(0, 1, 1);
        arrayNxN.set(0, 2, 0);
        arrayNxN.set(1, 0, 0);
        arrayNxN.set(1, 1, 0);
        arrayNxN.set(1, 2, 0);
        arrayNxN.set(2, 0, 1);
        arrayNxN.set(2, 1, 0);
        arrayNxN.set(2, 2, 0);
        ArrayMxN minArray = arrayNxN.getMinArray();
        assertEquals(" 1 1 \n" +
                     "     \n" +
                     " 1   \n", minArray.toString('1'));

        ArrayNxN arrayNxN2 = new ArrayNxN(1);
        ArrayMxN minArray2 = arrayNxN2.getMinArray();
        assertNull(minArray2);

        ArrayNxN arrayNxN3 = new ArrayNxN(3);
        arrayNxN3.set(0, 0, 1);
        arrayNxN3.set(0, 1, 1);
        arrayNxN3.set(0, 2, 1);
        arrayNxN3.set(1, 0, 1);
        arrayNxN3.set(1, 2, 1);
        arrayNxN3.set(2, 0, 1);
        arrayNxN3.set(2, 1, 1);
        arrayNxN3.set(2, 2, 1);
        ArrayMxN minArray3 = arrayNxN3.getMinArray();
        assertEquals(" 1 1 1 \n" +
                     " 1   1 \n" +
                     " 1 1 1 \n", minArray3.toString('1'));

    }


}