package com.clockworkshepherd.client_finder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JudgingPlanTextClassifierTest {

    @Test
    void classify_undefined() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.UNDEFINED, inputClass);
    }

    @Test
    void classify_ring_header() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "Ring: 1 Hala: 3 Sobota / Saturday Razem: 79";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.RING_HEADER, inputClass);
    }

    @Test
    void classify_judge() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "Sędzia: Jarosław Grunt (PL)";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.JUDGE, inputClass);
    }

    @Test
    void classify_start_time() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "10.00";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.BREED_JUDGING_START_TIME, inputClass);
    }

    @Test
    void classify_ambiguous() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "Sędzia: Jarosław Grunt (PL) Ring: 1";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.AMBIGUOUS, inputClass);
    }

    @Test
    void classify_reusing_same_classifier() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input1 = "Sędzia: Jarosław Grunt (PL)";
        String input2 = "Ring: 1";
        textClasses inputClass1 = classifier.classify(input1);
        textClasses inputClass2 = classifier.classify(input2);

        assertEquals(textClasses.JUDGE, inputClass1);
        assertEquals(textClasses.RING_HEADER, inputClass2);
    }

    @Test
    void classify_sex() {
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().build();
        String input = "Suki - Females";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.SEX, inputClass);
    }

    @Test
    void classify_breed() {
        List<String> breeds = Arrays.asList(
                "Cao Fila de Sao Miguel",
                "Ciobanesc Romanesc de Bucovina",
                "Dansk-Svensk Gardshund");

        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder().breeds(breeds).build();
        String input = "Ciobanesc Romanesc de Bucovina (1)";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.BREED_NAME_PL, inputClass);
    }

    @Test
    void classify_competition() {
        List<String> breeds = Arrays.asList(
                "Cao Fila de Sao Miguel",
                "Ciobanesc Romanesc de Bucovina",
                "Dansk-Svensk Gardshund");

        List<String> competitions = Arrays.asList(
                "Klasa młodszych szczeniąt",
                "Klasa szczeniąt",
                "Klasa młodzieży"
        );

        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder()
                .competitions(competitions)
                .breeds(breeds)
                .build();

        String input = "Klasa młodzieży - Junior Class 2 662 - 663";

        textClasses inputClass = classifier.classify(input);
        assertEquals(textClasses.COMPETITION, inputClass);
    }
}