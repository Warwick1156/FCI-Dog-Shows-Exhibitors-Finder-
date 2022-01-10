package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.ArrayList;
import java.util.List;

public class RingHeaderIterator {
    List<TextLine> classifiedText;
    List<Integer> ringHeadersIndexes;

    Boolean finished = false;
    int position = 0;

    public RingHeaderIterator(List<TextLine> classifiedText) {
        this.classifiedText = classifiedText;
        this.ringHeadersIndexes = getIndexesOfRingHeaders(classifiedText);
        finishIfEmpty();
    }

    public Boolean hasNext() {
        return !finished;
    }

    public List<TextLine> next() {
        List<TextLine> output;
        if (finished) {
            output = new ArrayList<>();
        } else {
            output = getNextSublist();
            position++;
        }

        return output;
    }

    protected List<Integer> getIndexesOfRingHeaders(List<TextLine> textLines) {
        List<Integer> indexesOfRingHeaders = new ArrayList<>();
        for (int i = 0; i < textLines.size(); i++) {
            TextLine textLine = textLines.get(i);
            if (textLine.textClass == textClasses.RING_HEADER) {
                indexesOfRingHeaders.add(i);
            }
        }

        return indexesOfRingHeaders;
    }

    protected Boolean isLast() {
        return position == ringHeadersIndexes.size() - 1;
    }

    protected void finishIfEmpty() {
        if (ringHeadersIndexes.isEmpty()) {
            finished = true;
        }
    }

    private List<TextLine> getNextSublist() {
        if (isLast()) {
            finished = true;
            return getLast();
        }
        return getNext();
    }

    protected List<TextLine> getLast() {
        return classifiedText.subList(ringHeadersIndexes.get(ringHeadersIndexes.size() - 1), classifiedText.size());
    }

    protected List<TextLine> getNext() {
        return classifiedText.subList(ringHeadersIndexes.get(position), ringHeadersIndexes.get(position + 1));
    }
}

