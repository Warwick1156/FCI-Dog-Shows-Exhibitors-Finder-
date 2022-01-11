package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextClassListFilterTest {

    @Test
    void findEmptyInput() {
        List<TextLine> input = new ArrayList<>();
        TextLine expected = new TextLine("", textClasses.UNDEFINED);
        TextLine actual = TextLineListFilter.find(input, textClasses.UNDEFINED);

        assertEquals(expected.text, actual.text);
        assertEquals(expected.textClass, actual.textClass);
    }

    @Test
    void findNoneInInput() {
        List<TextLine> input = List.of(new TextLine("", textClasses.SEX));
        TextLine expected = new TextLine("", textClasses.UNDEFINED);
        TextLine actual = TextLineListFilter.find(input, textClasses.UNDEFINED);

        assertEquals(expected.text, actual.text);
        assertEquals(expected.textClass, actual.textClass);
    }

    @Test
    void findSingleFind() {
        List<TextLine> input = List.of(
                new TextLine("", textClasses.SEX),
                new TextLine("", textClasses.JUDGE),
                new TextLine("", textClasses.COMPETITION)
        );
        TextLine expected = new TextLine("", textClasses.JUDGE);
        TextLine actual = TextLineListFilter.find(input, textClasses.JUDGE);

        assertEquals(expected.text, actual.text);
        assertEquals(expected.textClass, actual.textClass);
    }

    @Test
    void filterReturnListOfElementsOfRightSize() {
        List<TextLine> input = List.of(
                new TextLine("", textClasses.SEX),
                new TextLine("", textClasses.JUDGE),
                new TextLine("", textClasses.JUDGE)
        );
        int expectedListSize = 2;
        int actualListSize = TextLineListFilter.filter(input, textClasses.JUDGE).size();

        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    void filterEmptyInputReturnEmptyList() {
        List<TextLine> input = new ArrayList<>();
        List<TextLine> actual = TextLineListFilter.filter(input, textClasses.JUDGE);

        assertTrue(actual.isEmpty());
    }

    @Test
    void filterNoMatchingElementsReturnsEmptyList() {
        List<TextLine> input = List.of(
                new TextLine("", textClasses.SEX),
                new TextLine("", textClasses.JUDGE),
                new TextLine("", textClasses.JUDGE)
        );
        List<TextLine> actual = TextLineListFilter.filter(input, textClasses.BREED_JUDGING_START_TIME);

        assertTrue(actual.isEmpty());
    }
}