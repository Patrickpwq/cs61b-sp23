public class Sort {
    public static void sort(String[] input) {
        sort(input, 0);
    }
    private static void sort(String[] x, int start) {
        if (start == x.length) {
            return;
        }
        int smallest = findSmallest(x, start);
        swap(x, start, smallest);
        sort(x, start + 1);
    }
    public static int findSmallest(String[] input, int start) {
        String smallest = input[start];
        int smallestPos = start;
        for (int i = start; i < input.length; i++) {
            if (smallest.compareTo(input[i]) > 0) {
                smallest = input[i];
                smallestPos = i;
            }
        }
        return smallestPos;
    }

    public static void swap(String[] input, int s, int t) {
        String temp = input[t];
        input[t] = input[s];
        input[s] = temp;
    }
}