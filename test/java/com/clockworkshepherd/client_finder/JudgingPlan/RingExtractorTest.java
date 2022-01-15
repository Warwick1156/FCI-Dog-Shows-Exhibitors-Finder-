package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RingExtractorTest {

    List<String> getListWithSingleRing() {
        return Arrays.asList(
                "Ring: 1 Hala: 3 Sobota / Saturday Razem: 79",
                "Sędzia: Jarosław Grunt (PL)",
                "10.00",
                "Cao Fila de Sao Miguel    (1)",
                "Saint Miguel Cattle Dog",
                "Psy - Males",
                "Klasa młodzieży - Junior Class 1 607)"
        );
    }

    @Test
    void extractReturnType() throws IOException {
        RingExtractor extractor = new RingExtractor();
        assertThat(extractor.extract(new ArrayList<>()), instanceOf(List.class));
    }

    @Test
    void extractReturnedRingHasRightJudge() throws IOException {
        List<String> rows = getListWithSingleRing();

        RingExtractor extractor = new RingExtractor();
        List<Ring> ringList = extractor.extract(rows);

        String expected = "Jarosław Grunt";
        String actual = ringList.get(0).judge;

        assertEquals(expected, actual);
    }

    @Test
    void extractReturnedRingHasRightRingNumber() throws IOException {
        List<String> rows = getListWithSingleRing();

        RingExtractor extractor = new RingExtractor();
        List<Ring> ringList = extractor.extract(rows);

        int expected = 1;
        int actual = ringList.get(0).ringNo;

        assertEquals(expected, actual);
    }

    @Test
    void classifyDocumentRows() throws IOException {
        List<String> rows = getListWithSingleRing();

        RingExtractor extractor = new RingExtractor();
        List<TextLine> actual = extractor.classify(rows);
        List<textClasses> actualClasses = actual.stream().map(TextLine::getTextClass).toList();

        List<textClasses> expectedClasses = Arrays.asList(
                textClasses.RING_HEADER,
                textClasses.JUDGE,
                textClasses.BREED_JUDGING_START_TIME,
                textClasses.BREED_NAME,
                textClasses.UNDEFINED,
                textClasses.SEX,
                textClasses.COMPETITION
                );

        assertEquals(expectedClasses, actualClasses);
    }

    @Test
    void removeUndefined() {
        RingExtractor extractor = new RingExtractor();
        List<TextLine> input = Arrays.asList(
                new TextLine("", textClasses.RING_HEADER),
                new TextLine("", textClasses.UNDEFINED),
                new TextLine("", textClasses.SEX)
        );

        List<textClasses> expectedClasses = Arrays.asList(textClasses.RING_HEADER, textClasses.SEX);
        List<textClasses> actualClasses = extractor.removeUndefined(input).stream().map(TextLine::getTextClass).toList();

        assertEquals(expectedClasses, actualClasses);
    }
}