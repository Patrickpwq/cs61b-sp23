import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PercolationBackwashTest {
    private Percolation perc;

    @Before
    public void initialize() {
        // Initialize Percolation with a 5x5 grid for basic tests
        perc = new Percolation(5);
    }

    @Test
    public void testBackwash() {
        perc.open(0, 0);
        perc.open(1, 0);
        perc.open(2, 0);
        perc.open(3, 0);
        perc.open(4, 0);
        perc.open(4, 3);
        perc.open(3, 3);
        perc.open(2, 3);
        assertFalse(perc.isFull(2, 3));
    }
}