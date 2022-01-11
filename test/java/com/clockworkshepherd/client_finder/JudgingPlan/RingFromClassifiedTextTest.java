package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RingFromClassifiedTextTest {

    @Test
    void buildReturnsRightJudge() {
        List<TextLine> input = List.of(
                new TextLine("Undefined", textClasses.UNDEFINED),
                new TextLine("Artur Bąk", textClasses.JUDGE),
                new TextLine("Sex", textClasses.SEX)
        );

        String expected = "Artur Bąk";
        String actual = new RingFromClassifiedText(input).build().judge;

        assertEquals(expected, actual);
    }
}