package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class RingNumberExtractor {
    private final static String WHITESPACE = " ";
    private final static String RING_INDICATOR = "Ring:";

    public int extract(List<TextLine> textLines) {
        TextLine ringHeader = TextLineListFilter.find(textLines, textClasses.RING_HEADER);
        return getRingNumber(ringHeader);
    }


    protected int getRingNumber(TextLine ringHeader) {
        int ringNumber = 0;

        List<String> splitText = List.of(ringHeader.text.split(WHITESPACE));
        int indexOfRingIndicator = splitText.indexOf(RING_INDICATOR);

        if (indexOfRingIndicator < splitText.size() - 1) {
            String stringRingNumber = splitText.get(indexOfRingIndicator + 1);
            if (isParsable(stringRingNumber)) {
                ringNumber = Integer.parseInt(stringRingNumber);
            }
        }

        return ringNumber;
    }

    private boolean isParsable(String string) {
        return string.matches("\\d+");
    }
}
