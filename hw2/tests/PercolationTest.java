import static org.junit.Assert.*;
import org.junit.Test;

public class PercolationTest {

    @Test
    public void testPercolationConstructor() {
        Percolation perc = new Percolation(5);
        assertNotNull(perc);
        assertEquals(0, perc.numberOfOpenSites());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPercolationConstructorWithInvalidArgument() {
        new Percolation(0); // Should throw IllegalArgumentException
    }

    @Test
    public void testOpen() {
        Percolation perc = new Percolation(5);
        perc.open(1, 1);
        assertTrue(perc.isOpen(1, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenWithInvalidIndices() {
        Percolation perc = new Percolation(5);
        perc.open(-1, 1); // Should throw IndexOutOfBoundsException
    }

    @Test
    public void testIsOpen() {
        Percolation perc = new Percolation(5);
        assertFalse(perc.isOpen(1, 1));
        perc.open(1, 1);
        assertTrue(perc.isOpen(1, 1));
    }

    @Test
    public void testIsFull() {
        Percolation perc = new Percolation(5);
        perc.open(3, 1);
        assertFalse(perc.isFull(3, 1));

        perc.open(0, 0);
        perc.open(0, 1);
        perc.open(1, 1);
        perc.open(2, 1);
        assertTrue(perc.isFull(3, 1));
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation perc = new Percolation(5);
        assertEquals(0, perc.numberOfOpenSites());
        perc.open(1, 1);
        perc.open(2, 2);
        assertEquals(2, perc.numberOfOpenSites());
        perc.open(2, 2); // Opening the same site should not change the count
        assertEquals(2, perc.numberOfOpenSites());
    }

    @Test
    public void testPercolates() {
        Percolation perc = new Percolation(5);
        // Initially, should not percolate
        assertFalse(perc.percolates());
        // Open a path from top to bottom
        for (int i = 0; i < 5; i++) {
            perc.open(i, 0);
        }
        assertTrue(perc.percolates());
    }

}
