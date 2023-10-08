import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestSort {
    @Test
    public void testSort() {
        String[] input = {"CC", "BB", "DD", "AA"};
        String[] expected = {"AA", "BB", "CC", "DD"};
        Sort.sort(input);
        assertThat(input).isEqualTo(expected);
    }
    @Test
    public void testFindSmallest() {
        String[] input = {"eq", "distortion", "gain", "abc"};
        int expected = 3;
        int actual = Sort.findSmallest(input, 0);
        assertThat(actual).isEqualTo(expected);

        expected = 3;
        actual = Sort.findSmallest(input, 2);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testSwap() {
        String[] input = {"eq", "distortion", "gain", "abc"};
        String[] expected = {"abc", "distortion", "gain", "eq"};
        Sort.swap(input, 0, 3);
        assertThat(input).isEqualTo(expected);
    }
}
