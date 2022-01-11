package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;
import java.util.stream.Stream;

public class TextLineListFilter {
    private static final String EMPTY_STRING = "";
    private static final TextLine EMPTY_TEXT_LINE = new TextLine(EMPTY_STRING, textClasses.UNDEFINED);

    public static TextLine find(List<TextLine> textLines, textClasses textClass) {
        return getFilteredStream(textLines, textClass).findAny().orElse(EMPTY_TEXT_LINE);
    }

    public static List<TextLine> filter(List<TextLine> textLines, textClasses textClass) {
        return getFilteredStream(textLines, textClass).toList();
    }

    private static Stream<TextLine> getFilteredStream(List<TextLine> textLines, textClasses textClass) {
        return textLines.stream().filter(line -> line.textClass == textClass);
    }
}
