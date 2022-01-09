package com.clockworkshepherd.client_finder.JudgingPlan;

import com.clockworkshepherd.client_finder.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RingExtractorTest {

    @Test
    void extractReturnType() throws IOException {
        RingExtractor extractor = new RingExtractor();
        assertThat(extractor.extract(new ArrayList<>()), instanceOf(List.class));
    }

    @Test
    void classifyDocumentRows() throws IOException {
        List<String> rows = Arrays.asList(
                "Ring: 1 Hala: 3 Sobota / Saturday Razem: 79",
                "Sędzia: Jarosław Grunt (PL)",
                "10.00",
                "Cao Fila de Sao Miguel    (1)",
                "Saint Miguel Cattle Dog",
                "Psy - Males",
                "Klasa młodzieży - Junior Class 1 607)"
        );

        RingExtractor extractor = new RingExtractor();
        List<TextLine> actual = extractor.classify(rows);
        List<textClasses> actualClasses = actual.stream().map(TextLine::getTextClass).toList();

        List<textClasses> expectedClasses = Arrays.asList(
                textClasses.RING_HEADER,
                textClasses.JUDGE,
                textClasses.BREED_JUDGING_START_TIME,
                textClasses.BREED_NAME_PL,
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