package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class RingFromClassifiedText {
    public RingFromClassifiedText(List<TextLine> next) {

    }

    public Ring build() {
        return new RingBuilder().build();
    }
}
