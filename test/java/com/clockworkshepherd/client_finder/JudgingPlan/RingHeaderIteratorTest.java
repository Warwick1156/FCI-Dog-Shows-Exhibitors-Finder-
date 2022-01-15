package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RingHeaderIteratorTest {
    RingHeaderIterator iterator;
    List<TextLine> input;

    public void setup() {
        List<TextLine> input = Arrays.asList(
               new TextLine("Ring: 1 Hala: 3 Sobota / Saturday Razem: 79", textClasses.RING_HEADER),
               new TextLine("Sędzia: Jarosław Grunt (PL)", textClasses.JUDGE),
               new TextLine("Cao Fila de Sao Miguel    (1)", textClasses.BREED_NAME),
               new TextLine("Ring: 2 Hala: 3 Sobota / Saturday Razem: 49", textClasses.RING_HEADER),
               new TextLine("10.00", textClasses.BREED_JUDGING_START_TIME)
        );

        iterator = new RingHeaderIterator(input);
    }

    @Test
    void hasNextWhenTrue() {
        setup();

        assertTrue(iterator.hasNext());
    }

    @Test
    void hasNextWhenFalse() {
        setup();
        iterator.next();
        iterator.next();

        assertFalse(iterator.hasNext());
    }

    @Test
    void nextReturnsFirstSublistOfRightSize() {
        setup();

        assertEquals(3, iterator.next().size());
    }

    @Test
    void getIndexesOfRingHeaders() {
        setup();

        assertEquals(iterator.ringHeadersIndexes, Arrays.asList(0, 3));
    }

    @Test
    void getIndexesOfRingHeadersWhenEmpty() {
        RingHeaderIterator iterator = new RingHeaderIterator(new ArrayList<>());

        assertEquals(iterator.ringHeadersIndexes, List.of());
    }

    @Test
    void isLastWhenTrue() {
        setup();
        iterator.next();

        assertTrue(iterator.isLast());
    }

    @Test
    void isLastWhenFalse() {
        setup();

        assertFalse(iterator.isLast());
    }

    @Test
    void finishIfEmptyWhenTrue() {
        RingHeaderIterator iterator = new RingHeaderIterator(new ArrayList<>());
        assertTrue(iterator.finished);
    }

    @Test
    void finishIfEmptyWhenFalse() {
        setup();
        assertFalse(iterator.finished);
    }

    @Test
    void getLast() {
        setup();

        assertEquals(2, iterator.getLast().size());
    }

    @Test
    void getNext() {
        setup();

        assertEquals(3, iterator.next().size());
        assertEquals(2, iterator.next().size());
        assertEquals(0, iterator.next().size());
    }
}