package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("wordLength is less than 1");
        }
        List<String> words = new ArrayList<>();
        words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = words.size();
        if (numWords == 0) {
            throw new IllegalStateException("no words found of wordLength");
        }
        int randomChosenNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomChosenNumber);

        pattern = "-";
        for (int i = 1; i < wordLength; i++) {
            pattern += '-';
        }

    }

    @Override
    public int makeGuess(char letter) {
        int occurrences = 0;
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter) {
                pattern = pattern.substring(0, i) + letter + pattern.substring(i + 1);
                occurrences++;
            }
        }
        return occurrences;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
