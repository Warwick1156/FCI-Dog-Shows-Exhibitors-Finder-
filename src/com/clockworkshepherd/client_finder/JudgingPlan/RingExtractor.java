package com.clockworkshepherd.client_finder.JudgingPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RingExtractor {
    public RingExtractor() {

    }

    public List<Ring> extract(List<String> rows) throws IOException {
        List<TextLine> classifiedRows = classify(rows);
        classifiedRows = removeUndefined(classifiedRows);
//        TODO: log ambiguous rows

        return new ArrayList<>();
    }

    protected List<TextLine> classify(List<String> rows) throws IOException {
        JudgingPlanKnownNames knownNames = new JudgingPlanKnownNames();
        JudgingPlanTextClassifier classifier = new JudgingPlanTextClassifierBuilder()
                .breeds(knownNames.breeds)
                .competitions(knownNames.competitions)
                .build();

        List<TextLine> classifiedLines = new ArrayList<>();
        rows.forEach(line -> classifiedLines.add(new TextLine(line, classifier.classify(line))));

        return classifiedLines;
    }

    protected List<TextLine> removeUndefined(List<TextLine> textLines) {
        return textLines.stream().filter(line -> line.textClass != textClasses.UNDEFINED).toList();
    }


    protected List<Ring> constructRingsFromClassifiedText(List<TextLine> classifiedText) {
        List<Ring> ringList = new ArrayList<>();

        RingHeaderIterator iterator = new RingHeaderIterator(classifiedText);
        while (iterator.hasNext()) {
            ringList.add(new RingFromClassifiedText(iterator.next()).build());
        }

        return ringList;
    }
}
