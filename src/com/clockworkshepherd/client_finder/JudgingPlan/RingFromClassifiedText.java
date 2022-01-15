package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class RingFromClassifiedText {
    List<TextLine> textLines;
    RingBuilder ringBuilder = new RingBuilder();

    public RingFromClassifiedText(List<TextLine> textLines) {
        this.textLines = textLines;
        setJudge();
        setRingNumber();
    }

    public Ring build() {
        return ringBuilder.build();
    }

    protected void setJudge() {
        ringBuilder.judge(new JudgeNameExtractor().extract(textLines));
    }

    protected void setRingNumber() {
        ringBuilder.ringNumber(new RingNumberExtractor().extract(textLines));
    }
}
