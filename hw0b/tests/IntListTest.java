import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntListTest {

    @Test
    public void testSize() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        assertEquals(3, list.size());
    }

    @Test
    public void testIterativeSize() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        assertEquals(3, list.iterativeSize());
    }

    @Test
    public void testGet() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        assertEquals(2, list.get(2));
    }

    @Test
    public void testIncrList() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        IntList newList = IntList.incrList(list, 1);
        assertEquals(4, newList.get(1));
    }

    @Test
    public void testDincrList() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        IntList newList = IntList.dincrList(list, 1);
        assertEquals(4, newList.get(1));
    }
    @Test
    public void testaddFirst() {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);
        list.addFirst(4);
        assertEquals(4, list.get(1));
        assertEquals(2, list.get(3));
        assertEquals(1, list.get(4));


    }
}
