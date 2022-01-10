package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.ArrayList;
import java.util.List;

public class JudgeExtractor {
    private final static String WHITESPACE = " ";
    private final static int MINIMAL_LIST_SIZE = 3;

    public String extract(String input) {
        return getJudgeFullName(input);
    }


    protected String getJudgeFullName(String input) {
        List<String> splitInput = List.of(input.split(WHITESPACE));
        List<String> name = getName(splitInput);

        return String.join(WHITESPACE, name);
    }

    protected List<String> getName(List<String> input) {
        if (input.size() < MINIMAL_LIST_SIZE) {
            return new ArrayList<>();
        }
        return input.subList(1, input.size() - 1);
    }
}
