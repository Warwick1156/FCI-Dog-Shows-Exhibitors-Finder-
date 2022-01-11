package com.clockworkshepherd.client_finder.JudgingPlan;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JudgeExtractorTest {

    @Test
    void extractReturnsString() {
        assertThat(new JudgeNameExtractor().extract(""), CoreMatchers.instanceOf(String.class));
    }

    @Test
    void extractWithStandardRow() {
        String input = "Sędzia: Beata Badura (PL)";
        String expected = "Beata Badura";
        String actual = new JudgeNameExtractor().extract(input);

        assertEquals(expected, actual);
    }

    @Test
    void extractWithShortRow() {
        String input = "Sędzia: Badura (PL)";
        String expected = "Badura";
        String actual = new JudgeNameExtractor().extract(input);

        assertEquals(expected, actual);
    }
}
