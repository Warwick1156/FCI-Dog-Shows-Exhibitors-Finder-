package com.clockworkshepherd.client_finder.JudgingPlan;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JudgeExtractorTest {

    @Test
    void extractReturnsString() {
        assertThat(new JudgeNameExtractor().extract(new ArrayList<>()), CoreMatchers.instanceOf(String.class));
    }

    @Test
    void extractWithStandardRow() {
        List<TextLine> input = List.of(new TextLine("Sędzia: Beata Badura (PL)", textClasses.JUDGE));
        String expected = "Beata Badura";
        String actual = new JudgeNameExtractor().extract(input);

        assertEquals(expected, actual);
    }

    @Test
    void extractWithShortRow() {
        List<TextLine> input = List.of(new TextLine("Sędzia: Badura (PL)", textClasses.JUDGE));
        String expected = "Badura";
        String actual = new JudgeNameExtractor().extract(input);

        assertEquals(expected, actual);
    }
}
