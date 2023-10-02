package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> matchedWords = GuessHelper.WordsMatchPattern(pattern, words);
        Map<Character, Integer> frequencyMap = getFrequencyMap(matchedWords);
        return GuessHelper.mostCommonWord(frequencyMap, guesses);
    }


    public Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character, Integer> frequencyMap = new TreeMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                frequencyMap.merge(ch, 1, Integer::sum);
            }
        }
        return frequencyMap;
    }
    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));


    }
}