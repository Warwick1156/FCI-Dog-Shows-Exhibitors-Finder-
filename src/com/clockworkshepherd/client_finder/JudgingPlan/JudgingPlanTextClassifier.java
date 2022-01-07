package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JudgingPlanTextClassifier {
    final static Pattern judgingStartTmePattern = Pattern.compile("\\d{2}.\\d{2}");
    final static String RING = "Ring";
    final static String JUDGE = "Sędzia";
    final static String SEX_MALE = "Psy";
    final static String SEX_FEMALE = "Suki";

    HashMap<Object, Boolean> textClassificationMap;
    List<String> competitions;
    List<String> breeds;

    public JudgingPlanTextClassifier(List<String> competitions, List<String> breeds) {
        this.competitions = competitions;
        this.breeds = breeds;
    }

    public textClasses classify(String input) {
        mapToClasses(input);
        return getMappingResult();
    }

    private void mapToClasses(String input) {
        restClassificationMap();
        textClassificationMap.put(textClasses.RING_HEADER, isRingHeader(input));
        textClassificationMap.put(textClasses.JUDGE, isJudge(input));
        textClassificationMap.put(textClasses.BREED_JUDGING_START_TIME, isStartTime(input));
        textClassificationMap.put(textClasses.SEX, isSex(input));
        textClassificationMap.put(textClasses.BREED_NAME_PL, isBreed(input));
        textClassificationMap.put(textClasses.COMPETITION, isCompetition(input));
    }

    private void restClassificationMap() {
        this.textClassificationMap = new HashMap<>();
    }

    private textClasses getMappingResult() {
        int trueValuesCount = countTrueValuesInClassificationMap();

        textClasses result = textClasses.UNDEFINED;
        if (trueValuesCount > 1) {
            result = textClasses.AMBIGUOUS;
        } else if (trueValuesCount == 1) {
            result = (textClasses) getKeyOfClassMapWhereValueIsTrue();
        }

        return result;
    }

    private int countTrueValuesInClassificationMap() {
        int sum = 0;
        for (boolean value : textClassificationMap.values()) {
            sum += value ? 1 : 0;
        }
        return sum;
    }

    private Object getKeyOfClassMapWhereValueIsTrue() {
        return textClassificationMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(true))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    private boolean isRingHeader(String input) {
        return input.contains(RING);
    }

    private boolean isJudge(String input) {
        return input.contains(JUDGE);
    }

    private boolean isStartTime(String input) {
        return judgingStartTmePattern.matcher(input).matches();
    }

    private boolean isSex(String input) {
        return input.contains(SEX_MALE) || input.contains(SEX_FEMALE);
    }

    private boolean isBreed(String input) {
        return breeds.stream().anyMatch(input::contains);
    }

    private boolean isCompetition(String input) {
        return competitions.stream().anyMatch(input::contains);
    }
}
