package com.clockworkshepherd.client_finder.JudgingPlan;

public class TextLine {
    String text;
    textClasses textClass;

    public TextLine(String text, textClasses textClass) {
        this.text = text;
        this.textClass = textClass;
    }

    public textClasses getTextClass() {
        return textClass;
    }
}
