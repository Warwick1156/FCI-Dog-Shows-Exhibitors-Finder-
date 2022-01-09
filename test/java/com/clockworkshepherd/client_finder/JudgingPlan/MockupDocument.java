package com.clockworkshepherd.client_finder.JudgingPlan;

import com.clockworkshepherd.client_finder.Document;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockupDocument{
    List<String> lines = Arrays.asList(
            "Ring: 1 Hala: 3 Sobota / Saturday Razem: 79",
            "Sędzia: Jarosław Grunt (PL)",
            "10.00",
            "Cao Fila de Sao Miguel    (1)",
            "Saint Miguel Cattle Dog",
            "Psy - Males",
            "Klasa młodzieży - Junior Class 1 607)"
    );

    public MockupDocument() {}

    public List<String> getLines() {
        return lines;
    }
}
