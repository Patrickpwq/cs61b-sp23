package aoa.guessers;

import java.util.*;

public class GuessHelper {
    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> frequencyMap = new TreeMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                frequencyMap.merge(ch, 1, Integer::sum);
            }
        }
        return frequencyMap;
    }
    public static Character mostCommonWord(Map<Character, Integer> frequencyMap, List<Character> guesses){
        List<Map.Entry<Character, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
        Collections.reverse(frequencyList);
        int maxFrequency = -1;
        Character mostCommon = '?';
        for (Map.Entry<Character, Integer> pair : frequencyList) {
            Character ch = pair.getKey();
            Integer frequency = pair.getValue();
            if (frequency >= maxFrequency && !guesses.contains(ch)) {
                maxFrequency = frequency;
                mostCommon = ch;
            }
        }
        return mostCommon;
    }
    public static List<String> WordsMatchPattern(String pattern, List<String> words) {
        List<String> matchedWords = new ArrayList<>();
        int patternLen = pattern.length();
        for (String word : words) {
            if (word.length() != patternLen) {
                continue;
            }
            boolean isMatched = true;
            for (int i = 0; i < patternLen; i++) {
                char p = pattern.charAt(i);
                if (p != '-') {
                    if (p != word.charAt(i)) {
                        isMatched = false;
                    }
                }
            }
            if (isMatched) {
                matchedWords.add(word);
            }
        }
        return matchedWords;
    }

}
