package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class RingFromClassifiedText {
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
        ringBuilder.judge(new JudgeNameExtractor().extract(textLines));
    }
}
