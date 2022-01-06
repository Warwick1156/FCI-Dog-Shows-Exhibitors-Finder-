package com.clockworkshepherd.client_finder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JudgingPlanTextClassifier {
    HashMap<Object, Boolean> textClassificationMap;
    Pattern judgingStartTmePattern = Pattern.compile("\\d{2}.\\d{2}");
    List<String> competitions;
    List<String> breeds;

    public JudgingPlanTextClassifier(List<String> competitions, List<String> breeds) {
        this.competitions = competitions;
        this.breeds = breeds;
    }

    private void initializeClassificationMap() {
        Arrays.asList(textClasses.values()).forEach(textClass -> textClassificationMap.put(textClass, false));
    }

    private void restClassificationMap() {
        this.textClassificationMap = new HashMap<>();
        initializeClassificationMap();
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

    private textClasses getMappingResult() {
        textClasses result = textClasses.UNDEFINED;
        int trueValuesCount = countTrueValuesInClassificationMap();

        if (trueValuesCount > 1) {
            result = textClasses.AMBIGUOUS;
        } else if (trueValuesCount == 1) {
            result = (textClasses) getKeyOfClassMapWhereValueIsTrue();
        }

        return result;
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

    private int countTrueValuesInClassificationMap() {
        int sum = 0;
        for (boolean value : textClassificationMap.values()) {
            sum += value ? 1 : 0;
        }
        return sum;
    }

    private boolean isRingHeader(String input) {
        return input.contains("Ring");
    }

    private boolean isJudge(String input) {
        return input.contains("Sędzia");
    }

    private boolean isStartTime(String input) {
        return judgingStartTmePattern.matcher(input).matches();
    }

    private boolean isSex(String input) {
        return input.contains("Psy") || input.contains("Suki");
    }

    private boolean isBreed(String input) {
        return breeds.stream().anyMatch(input::contains);
    }

    private boolean isCompetition(String input) {
        return competitions.stream().anyMatch(input::contains);
    }
}
