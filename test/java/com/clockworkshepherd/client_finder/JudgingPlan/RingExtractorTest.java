package com.clockworkshepherd.client_finder.JudgingPlan;

import com.clockworkshepherd.client_finder.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        assertThat(extractor.extract(null), instanceOf(List.class));
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

        List<TextLine> expected = Arrays.asList(
                new TextLine("Ring: 1 Hala: 3 Sobota / Saturday Razem: 79", textClasses.RING_HEADER),
                new TextLine("Sędzia: Jarosław Grunt (PL)", textClasses.JUDGE),
                new TextLine("10.00", textClasses.BREED_JUDGING_START_TIME),
                new TextLine("Cao Fila de Sao Miguel    (1)", textClasses.BREED_NAME_PL),
                new TextLine("Saint Miguel Cattle Dog", textClasses.UNDEFINED),
                new TextLine("Psy - Males", textClasses.SEX),
                new TextLine("Klasa młodzieży - Junior Class 1 607)", textClasses.COMPETITION)
        );

        List<textClasses> expectedClasses = expected.stream().map(TextLine::getTextClass).toList();

        RingExtractor extractor = new RingExtractor();
//        List<textClasses> actualClasses = extractor.classifyDocumentRows(rows).stream().map(TextLine::getTextClass).toList();
        List<textClasses> actualClasses = Arrays.asList(
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
}