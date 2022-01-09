package com.clockworkshepherd.client_finder.JudgingPlan;

import com.clockworkshepherd.client_finder.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RingExtractor {
    public RingExtractor() {

    }

    public List<Ring> extract(Document document) {

        return new ArrayList<>();
    }

    public List<TextLine> classifyRows(List<String> rows) throws IOException {
        JudgingPlanKnownNames knownNames = new JudgingPlanKnownNames();
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder()
                .breeds(knownNames.breeds)
                .competitions(knownNames.competitions)
                .build();

        List<TextLine> classifiedLines = new ArrayList<>();
        rows.forEach(line -> classifiedLines.add(new TextLine(line, classifier.classify(line))));
        return classifiedLines;
    }



}
