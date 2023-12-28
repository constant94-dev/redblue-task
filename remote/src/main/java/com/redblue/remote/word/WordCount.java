package com.redblue.remote.word;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCount {
    private final Logger LOGGER = LoggerFactory.getLogger(WordCount.class);

    public Map<String, Integer> wordCount(String inputStr) {
        LOGGER.info("wordCount 호출!!");
        Map<String, Integer> words = new HashMap<>();

        for (char c : inputStr.toCharArray()) {
            String convert = String.valueOf(c);
            words.put(convert, words.getOrDefault(convert, 0) + 1);
        }

        return words;
    }
}
