import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class BoardPositionTest {
    BoardPosition b;

    @Before
    public void setUp() throws Exception {
        b = new BoardPosition("xxxxxBxWWWWWBBBBxxxxxxx");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSwap() throws Exception {
        assertEquals("xxxxxWxBBBBBWWWWxxxxxxx", b.swap().toString());
    }

    @Test
    public void testGetNumWhitePieces() throws Exception {
        assertEquals(5, b.getNumWhitePieces());
    }

    @Test
    public void testGetNumBlackPieces() throws Exception {
        assertEquals(5, b.getNumBlackPieces());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertEquals(true, b.isEmpty(3));
        assertEquals(false, b.isEmpty(5));
        assertEquals(false, b.isEmpty(8));
    }

    @Test
    public void testIsWhite() throws Exception {
        assertEquals(false, b.isWhite(3));
        assertEquals(false, b.isWhite(5));
        assertEquals(true, b.isWhite(8));
    }

    @Test
    public void testIsBlack() throws Exception {
        assertEquals(false, b.isBlack(3));
        assertEquals(true, b.isBlack(5));
        assertEquals(false, b.isBlack(8));
    }

    @Test
    public void testGet() throws Exception {
        assertEquals('x', b.get(20));
        assertEquals('B', b.get(5));
        assertEquals('W', b.get(10));
    }

    @Test
    public void testSetWhiteAt() throws Exception {
        b.setWhiteAt(2);
        b.setWhiteAt(5);
        b.setWhiteAt(20);
        assertEquals('W', b.get(2));
        assertEquals('W', b.get(5));
        assertEquals('W', b.get(20));
    }

    @Test
    public void testSetBlackAt() throws Exception {
        b.setBlackAt(2);
        b.setBlackAt(5);
        b.setBlackAt(20);
        assertEquals('B', b.get(2));
        assertEquals('B', b.get(5));
        assertEquals('B', b.get(20));
    }

    @Test
    public void testSetEmptyAt() throws Exception {
        b.setEmptyAt(2);
        b.setEmptyAt(5);
        b.setEmptyAt(20);
        assertEquals('x', b.get(2));
        assertEquals('x', b.get(5));
        assertEquals('x', b.get(20));
    }

    @Test
    public void testDisp() throws Exception {
        b.disp();
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("xxxxxBxWWWWWBBBBxxxxxxx", b.toString());
    }

    @Test
    public void testNeighbors() throws Exception {
        assertEquals(Arrays.asList(new Integer[]{1, 3, 8}), b.neighbors(0));
        assertEquals(Arrays.asList(new Integer[]{0, 2, 4}), b.neighbors(1));
        assertEquals(Arrays.asList(new Integer[]{1, 5, 13}), b.neighbors(2));
        assertEquals(Arrays.asList(new Integer[]{0, 4, 6, 9}), b.neighbors(3));
        assertEquals(Arrays.asList(new Integer[]{1, 3, 5}), b.neighbors(4));
        assertEquals(Arrays.asList(new Integer[]{2, 4, 7, 12}), b.neighbors(5));
        assertEquals(Arrays.asList(new Integer[]{3, 7, 10}), b.neighbors(6));
        assertEquals(Arrays.asList(new Integer[]{5, 6, 11}), b.neighbors(7));
        assertEquals(Arrays.asList(new Integer[]{0, 9, 20}), b.neighbors(8));
        assertEquals(Arrays.asList(new Integer[]{3, 8, 10, 17}), b.neighbors(9));
        assertEquals(Arrays.asList(new Integer[]{6, 9, 14}), b.neighbors(10));
        assertEquals(Arrays.asList(new Integer[]{7, 12, 16}), b.neighbors(11));
        assertEquals(Arrays.asList(new Integer[]{5, 11, 13, 19}), b.neighbors(12));
        assertEquals(Arrays.asList(new Integer[]{2, 12, 22}), b.neighbors(13));
        assertEquals(Arrays.asList(new Integer[]{10, 15, 17}), b.neighbors(14));
        assertEquals(Arrays.asList(new Integer[]{14, 16, 18}), b.neighbors(15));
        assertEquals(Arrays.asList(new Integer[]{11, 15, 19}), b.neighbors(16));
        assertEquals(Arrays.asList(new Integer[]{9, 14, 18, 20}), b.neighbors(17));
        assertEquals(Arrays.asList(new Integer[]{15, 17, 19, 21}), b.neighbors(18));
        assertEquals(Arrays.asList(new Integer[]{12, 16, 18, 22}), b.neighbors(19));
        assertEquals(Arrays.asList(new Integer[]{8, 17, 21}), b.neighbors(20));
        assertEquals(Arrays.asList(new Integer[]{18, 20, 22}), b.neighbors(21));
        assertEquals(Arrays.asList(new Integer[]{13, 19, 21}), b.neighbors(22));
    }

    @Test
    public void testCloseMill() throws Exception {
        assertFalse(b.closeMill(9));
    }
}
