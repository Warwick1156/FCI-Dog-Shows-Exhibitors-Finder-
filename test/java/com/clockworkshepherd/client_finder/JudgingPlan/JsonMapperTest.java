package com.clockworkshepherd.client_finder.JudgingPlan;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    void map() throws IOException {
        Path source = Paths.get(System.getProperty("user.dir"), "data/breedsPL.json");
        KnownNames breedNamesPL = (KnownNames) JsonMapper.map(source, KnownNames.class);

        assertTrue(breedNamesPL.names.contains("Kangal"));
    }
}