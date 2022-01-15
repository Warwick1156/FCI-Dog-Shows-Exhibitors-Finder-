package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RingNumberExtractorTest {

    @Test
    void extractReturnInteger() {
        assertThat(new RingNumberExtractor().extract(new ArrayList<>()), instanceOf(Integer.class));
    }

    @Test
    void extractReturnsRightNumberFromStandardRingHeader() {
        List<TextLine> input = List.of(new TextLine("Ring: 2 Hala: 3 Sobota / Saturday Razem: 49", textClasses.RING_HEADER));

        assertEquals(2, new RingNumberExtractor().extract(input));
    }

    @Test
    void extractMinimumTextLineSize() {
        List<TextLine> input = List.of(new TextLine("Ring: 2", textClasses.RING_HEADER));

        assertEquals(2, new RingNumberExtractor().extract(input));
    }
}