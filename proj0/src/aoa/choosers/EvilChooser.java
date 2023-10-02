package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.apache.commons.collections.ArrayStack;
import org.checkerframework.checker.units.qual.A;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("word length less than 1");
        }
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordPool.isEmpty()) {
            throw new IllegalStateException("no word is of length");
        }
        pattern = "-";
        for (int i = 1; i < wordLength; i++) {
            pattern += '-';
        }
    }

    @Override
    public int makeGuess(char letter) {
        Map<String, List<String>> patternFamily = new TreeMap<>();
        for (String s : wordPool) {
            String thisPattern = getPatternOfLetter(s, letter);
            List<String> mapFamily = patternFamily.get(thisPattern);
            if (mapFamily != null) {
                mapFamily.add(s);
                patternFamily.put(thisPattern, mapFamily);
            }
            else {
                List<String> newFamily = new ArrayList<>();
                newFamily.add(s);
                patternFamily.put(thisPattern, newFamily);
            }
        }
        int maxSize = 0;
        for (String thisPattern : patternFamily.keySet()) {
            int size = patternFamily.get(thisPattern).size();
            if (size > maxSize) {
                maxSize = size;
                wordPool = patternFamily.get(thisPattern);
                pattern = thisPattern;
            }
        }
        int occurences = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == letter) {
                occurences++;
            }
        }
        return occurences;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return wordPool.get(0);
    }

    public String getPatternOfLetter(String s, char ch) {
        int len = s.length();
        String newPattern = pattern;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ch) {
                newPattern = newPattern.substring(0, i) + ch + newPattern.substring(i + 1);
            }
        }
        return newPattern;
    }
}
