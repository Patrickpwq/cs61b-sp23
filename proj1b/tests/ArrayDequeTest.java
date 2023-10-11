import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void toListTest() {

        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('c');
        ad.addFirst('b');
        ad.addFirst('a');
        ad.addLast('d');
        ad.addLast('e');
        List<Character> list = ad.toList();
        List<Character> expected = List.of('a', 'b', 'c', 'd', 'e');
        assertThat(list).isEqualTo(expected);

        // Edge case: empty deque
        Deque<Character> emptyDeque = new ArrayDeque<>();
        List<Character> emptyList = emptyDeque.toList();
        assertThat(emptyList).isEmpty();

        // Edge case: deque with one element
        Deque<Character> oneElementDeque = new ArrayDeque<>();
        oneElementDeque.addFirst('a');
        List<Character> oneElementList = oneElementDeque.toList();
        assertThat(oneElementList).isEqualTo(List.of('a'));

        // Edge case: deque with elements added only at the beginning
        Deque<Character> beginningDeque = new ArrayDeque<>();
        beginningDeque.addFirst('c');
        beginningDeque.addFirst('b');
        beginningDeque.addFirst('a');
        List<Character> beginningList = beginningDeque.toList();
        assertThat(beginningList).isEqualTo(List.of('a', 'b', 'c'));

        // Edge case: deque with elements added only at the end
        Deque<Character> endDeque = new ArrayDeque<>();
        endDeque.addLast('a');
        endDeque.addLast('b');
        endDeque.addLast('c');
        List<Character> endList = endDeque.toList();
        assertThat(Arrays.asList('a', 'b', 'c')).isEqualTo(endList);


    }

    @Test
    public void isEmptyTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.isEmpty()).isTrue();

        ad.addFirst(1);
        assertThat(ad.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(2); ad.addLast(3); ad.addFirst(4);
        assertThat(ad.size()).isEqualTo(3);
    }
    @Test
    public void getTest() {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('c');
        ad.addFirst('b');
        ad.addFirst('a');
        ad.addLast('d');
        ad.addLast('e');

        assertThat(ad.get(0)).isEqualTo('a');
        assertThat(ad.get(1)).isEqualTo('b');
        assertThat(ad.get(2)).isEqualTo('c');
        assertThat(ad.get(3)).isEqualTo('d');
        assertThat(ad.get(4)).isEqualTo('e');
    }
    @Test
    public void removeFirstTest() {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('c');
        ad.addFirst('b');
        ad.addFirst('a');

        // Test removeFirst method
        assertThat(ad.removeFirst()).isEqualTo('a');
        assertThat(ad.removeFirst()).isEqualTo('b');
        assertThat(ad.removeFirst()).isEqualTo('c');
    }
    @Test
    public void removeLastTest() {
        ArrayDeque<Character> ad = new ArrayDeque<>();
        ad.addLast('a');
        ad.addLast('b');
        ad.addLast('c');

        // Test removeLast method
        assertThat(ad.removeLast()).isEqualTo('c');
        assertThat(ad.removeLast()).isEqualTo('b');
        assertThat(ad.removeLast()).isEqualTo('a');
    }
}
