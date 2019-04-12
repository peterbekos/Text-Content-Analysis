package com.peterbekos;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap<String, Integer> wordCountMap = new HashMap<>();

    public static void main(String[] args) {
        String text = readFileToString(getFilePathFromName("test.txt"));
        breakTextIntoWords(text);

        // build and sort list
        List<Pair<String, Integer>> wordsByFrequency = new ArrayList<>();
        for (String key : wordCountMap.keySet()) {
            int count = wordCountMap.get(key);
            wordsByFrequency.add(new Pair<>(key, count));
        }
        wordsByFrequency.sort((o1, o2) -> o2.getValue() - o1.getValue());

        // print list
        for (Pair<String, Integer> item : wordsByFrequency) {
            System.out.println(String.format("%s (%d)", item.getKey(), item.getValue()) );
        }
    }

    private static String getFilePathFromName(String fileName) {
        Path path = Paths.get(fileName);
        String uriPath = path.toUri().getRawPath();
        return uriPath;
    } //C:\Users\PB\Develop\Text-Content-Analysis\JavaSolution\src\com\peterbekos\test.txt

    private static String readFileToString(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void breakTextIntoWords(String input) {
        wordCountMap.clear();
        StringBuilder wordBuilder = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (isCharacterPartOfWord(c)) {
                wordBuilder.append(c);
            } else {
                countWord(wordBuilder.toString());
                wordBuilder.setLength(0);
            }
        }
    }

    private static boolean isCharacterPartOfWord(char c) {
        return Character.isAlphabetic(c);
    }

    private static void countWord(String word) {
        if (null == word || word.isEmpty()) {
            return;
        }
        word = word.toLowerCase();
        if (wordCountMap.containsKey(word)) {
            int count = wordCountMap.get(word);
            wordCountMap.put(word, count + 1);
        } else {
            wordCountMap.put(word, 1);
        }
    }

}
