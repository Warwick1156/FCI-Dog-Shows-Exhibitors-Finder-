package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.ArrayList;
import java.util.List;

public class JudgeNameExtractor {
    private final static String WHITESPACE = " ";
    private final static int MINIMAL_LIST_SIZE = 3;

    public String extract(List<TextLine> input) {
        return getJudgeFullName(input);
    }

    protected String getJudgeFullName(List<TextLine> input) {
        TextLine judge = getJudgeTextLine(input);
        List<String> splitJudge = List.of(judge.text.split(WHITESPACE));
        List<String> name = getName(splitJudge);

        return String.join(WHITESPACE, name);
    }

    protected TextLine getJudgeTextLine(List<TextLine> input) {
        return TextLineListFilter.find(input, textClasses.JUDGE);
    }

    protected List<String> getName(List<String> input) {
        if (input.size() < MINIMAL_LIST_SIZE) {
            return new ArrayList<>();
        }
        return input.subList(1, input.size() - 1);
    }
}
