import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        if (L.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int ele : L) {
            sum += ele;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> newL = new ArrayList<>();
        if (L.size() <= 1) {
            return null;
        }
        for (int i = 1; i < L.size(); i += 2) {
            newL.add(L.get(i));
        }
        return newL;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonList = new ArrayList<>();
        for (int i : L1) {
            if (L2.contains(i) && !commonList.contains(i)) {
                commonList.add(i);
            }
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int occurrences = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    occurrences++;
                }
            }
        }
        return occurrences;
    }
}
