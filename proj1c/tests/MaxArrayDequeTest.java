import deque.MaxArrayDeque;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void IntegerComparatorTest() {
        Comparator<Integer> c1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        Comparator<Integer> c2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 % 10 - o2 % 10;
            }
        };
        MaxArrayDeque <Integer> a1 = new MaxArrayDeque<>(c1);
        a1.addLast(21);
        a1.addLast(35);
        a1.addLast(41);
        a1.addLast(15);

        assertThat(a1.max()).isEqualTo(41);
        assertThat(a1.max(c2)).isAnyOf(35, 15);
    }
    @Test
    public void StringComparatorTest() {
        Comparator<String> c1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        Comparator<String> c2 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(0) - o2.charAt(0);
            }
        };
        MaxArrayDeque<String> a1 = new MaxArrayDeque<>(c1);
        a1.addLast("tree");
        a1.addLast("heap");
        a1.addLast("linkedlist");
        a1.addLast("set");
        assertThat(a1.max()).isEqualTo("linkedlist");
        assertThat(a1.max(c2)).isEqualTo("tree");
    }
}
