import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> letterToNum = new HashMap<>();
        for (int i = 'a'; i <= 'z'; i++) {
            letterToNum.put((char)i, i - 'a' + 1);
        }
        return letterToNum;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> squares = new HashMap<>();
        for (int ele : nums) {
            squares.put(ele, ele * ele);
        }
        return squares;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String singleString : words) {
            int cnt = 0;
            for (String iterateString : words) {
                if (singleString.equals(iterateString)) {
                    cnt++;
                }
            }
            countMap.put(singleString, cnt);
        }
        return countMap;
    }
}
