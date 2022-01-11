package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class RingFromClassifiedText {
    private static final String EMPTY_STRING = "";
    private static final TextLine EMPTY_TEXT_LINE = new TextLine(EMPTY_STRING, textClasses.UNDEFINED);

    List<TextLine> textLines;
    RingBuilder ringBuilder = new RingBuilder();

    public RingFromClassifiedText(List<TextLine> textLines) {
        this.textLines = textLines;

        setJudge();
    }

    public Ring build() {
        return new RingBuilder().build();
    }

    protected void setJudge() {
        TextLine judge = getJudgeTextLine();
        ringBuilder.judge(new JudgeNameExtractor().extract(judge.text));
    }

    protected TextLine getJudgeTextLine() {
        return textLines.stream().filter(line -> line.textClass == textClasses.JUDGE).findAny().orElse(EMPTY_TEXT_LINE);
    }
}
