package com.clockworkshepherd.client_finder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ExhibitorInfoExtractorTest {

    @Test
    void getInfo_case_1() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Aaltovirta Maria Seija Helenius- Finlandia 1942";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Helenius");
        expectedOutput.put("surname", "Maria Seija");
        expectedOutput.put("lastName", "Aaltovirta");
        expectedOutput.put("country", "Finlandia");
        expectedOutput.put("startNumber", "1942");
        expectedOutput.put("key", "Helenius Aaltovirta");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_2() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Adamczyk Andrzej - Polska 1715";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Andrzej");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Adamczyk");
        expectedOutput.put("country", "Polska");
        expectedOutput.put("startNumber", "1715");
        expectedOutput.put("key", "Andrzej Adamczyk");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_3() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Andrzejczak-Wagner Małgorzata - Polska 71";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Małgorzata");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Andrzejczak-Wagner");
        expectedOutput.put("country", "Polska");
        expectedOutput.put("startNumber", "71");
        expectedOutput.put("key", "Małgorzata Andrzejczak-Wagner");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_4() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Vulic Dusko - Bośnia i Hercegowina 1840";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Dusko");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Vulic");
        expectedOutput.put("country", "Bośnia i Hercegowina");
        expectedOutput.put("startNumber", "1840");
        expectedOutput.put("key", "Dusko Vulic");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_5() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Andrzejczak-Wagner Dusko - Bośnia i Hercegowina 1840";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Dusko");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Andrzejczak-Wagner");
        expectedOutput.put("country", "Bośnia i Hercegowina");
        expectedOutput.put("startNumber", "1840");
        expectedOutput.put("key", "Dusko Andrzejczak-Wagner");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_6() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Adler Lise-Lotte - Dania 1639";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Lise-Lotte");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Adler");
        expectedOutput.put("country", "Dania");
        expectedOutput.put("startNumber", "1639");
        expectedOutput.put("key", "Lise-Lotte Adler");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }

    @Test
    void getInfo_case_7() {
        ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();

        String input = "Andrzejewski Andrzej - Polska 288";
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("firstName", "Andrzej");
        expectedOutput.put("surname", "");
        expectedOutput.put("lastName", "Andrzejewski");
        expectedOutput.put("country", "Polska");
        expectedOutput.put("startNumber", "288");
        expectedOutput.put("key", "Andrzej Andrzejewski");

        assertEquals(expectedOutput, extractor.getInfo(input));
    }
}