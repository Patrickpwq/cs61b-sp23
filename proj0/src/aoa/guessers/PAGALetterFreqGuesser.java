package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> patternMatchedWords = GuessHelper.WordsMatchPattern(pattern, words);
        List<String> pagMatchedWords = new ArrayList<>();
        List<Integer> dashPostions = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '-') {
                dashPostions.add(i);
            }
        }
        for (String word : patternMatchedWords) {
            boolean matched = true;
            for (int pos : dashPostions) {
                if (guesses.contains(word.charAt(pos))) {
                    matched = false;
                    break;
                }
            }
            if (matched)
                pagMatchedWords.add(word);
        }
        Map<Character, Integer> frequencyMap = GuessHelper.getFrequencyMap(pagMatchedWords);
        return GuessHelper.mostCommonWord(frequencyMap, guesses);
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
