package com.clockworkshepherd.client_finder.JudgingPlan;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class JsonMapper {
    public static Object map(Path source, Class target) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(source.toFile(), target);
    }
}
