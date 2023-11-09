import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PercolationComprehensiveTest {

    private Percolation perc;

    @Before
    public void initialize() {
        // Initialize Percolation with a 5x5 grid for basic tests
        perc = new Percolation(5);
    }

    @Test
    public void testInitialGrid() {
        assertEquals("Initial grid should have no open sites", 0, perc.numberOfOpenSites());
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                assertFalse("All sites should initially be blocked", perc.isOpen(row, col));
            }
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundsOnOpen() {
        perc.open(-1, 0); // Should throw IllegalArgumentException for negative index
        perc.open(0, -1); // Should throw IllegalArgumentException for negative index
        perc.open(5, 0); // Should throw IllegalArgumentException for index out of bounds
        perc.open(0, 5); // Should throw IllegalArgumentException for index out of bounds
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundsOnIsOpen() {
        perc.isOpen(-1, 0); // Should throw IllegalArgumentException for negative index
        perc.isOpen(0, -1); // Should throw IllegalArgumentException for negative index
        perc.isOpen(5, 0); // Should throw IllegalArgumentException for index out of bounds
        perc.isOpen(0, 5); // Should throw IllegalArgumentException for index out of bounds
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundsOnIsFull() {
        perc.isFull(-1, 0); // Should throw IllegalArgumentException for negative index
        perc.isFull(0, -1); // Should throw IllegalArgumentException for negative index
        perc.isFull(5, 0); // Should throw IllegalArgumentException for index out of bounds
        perc.isFull(0, 5); // Should throw IllegalArgumentException for index out of bounds
    }

    @Test
    public void testOpenAndIsFullComplexScenario() {
        // Open a site that is not on the top row
        perc.open(2, 2);
        assertFalse("Site should not be full if not connected to top row", perc.isFull(2, 2));

        // Open a path from top to the site (2,2)
        perc.open(0, 2); // Open top row site
        perc.open(1, 2); // Open adjacent site
        assertTrue("Site should be full if connected to top row", perc.isFull(2, 2));

        // Open another site that is not connected to the top row
        perc.open(4, 4);
        assertFalse("Site should not be full if not connected to top row", perc.isFull(4, 4));
    }

    @Test
    public void testPercolationThroughComplexPath() {
        // Open a zigzag path from top to bottom
        perc.open(0, 0);
        perc.open(1, 0);
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(3, 3);
        perc.open(4, 3);
        perc.open(4, 4);
        assertTrue("System should percolate if there is a path from top row to bottom row", perc.percolates());
    }

    @Test
    public void testNumberOfOpenSitesAfterMultipleOperations() {
        // Open multiple sites and check the number of open sites
        perc.open(0, 0);
        perc.open(1, 1);
        perc.open(1, 1); // Attempt to open the same site again should not change the count
        perc.open(2, 2);
        assertEquals("Number of open sites should match the number of unique opens", 3, perc.numberOfOpenSites());
    }

    // Additional test cases could check for:
    // - Percolation in a large grid to test performance.
    // - Randomized opening of sites until the system percolates to test various random scenarios.
    // - Testing edge cases where the grid has a size of 1.

}
