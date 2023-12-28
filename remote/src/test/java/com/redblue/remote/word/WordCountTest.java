package com.redblue.remote.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WordCountTest {

    @DisplayName("입력받은 문자열의 각 글자수를 카운트하고 가장 높은 카운트 확인")
    @ParameterizedTest
    @CsvSource(value = {"son,1", "aaa,3", "awsazuregcpcloud,2"})
    void wordCount(String inputStr, int expected) {
        WordCount wordCount = new WordCount();

        Map<String, Integer> result = wordCount.wordCount(inputStr);

        Set<Entry<String, Integer>> actuals = result.entrySet();

        int max = 0;
        for (Entry<String, Integer> actual : actuals) {
            if (actual.getValue() > max) {
                max = actual.getValue();
            }
        }

        assertThat(max).isEqualTo(expected);
    }
}