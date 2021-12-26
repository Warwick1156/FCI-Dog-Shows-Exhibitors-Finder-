package com.clockworkshepherd.client_finder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExhibitorInfoExtractor {
    public static final String WHITESPACE = " ";
    public static final String DASH = "-";

    ExhibitorInfoExtractor() {

    }

    public HashMap<String, String> getInfo(String rawInput) {
        HashMap<String, String> info = new HashMap<>();
        List<String> splitByWhitespace = Arrays.asList(rawInput.split(WHITESPACE));
        List<String> splitByDash = splitByDash(rawInput);

        try {
            info.put("startNumber", getStartNumber(splitByWhitespace));
            info.put("lastName", getLastName(splitByWhitespace));
            info.put("country", getCountry(splitByDash));
            info.put("firstName", getFirstName(rawInput));
            info.put("key", info.get("firstName") + WHITESPACE + info.get("lastName"));
            info.put("surname", getSurname(rawInput));
        } catch (Exception e) {
            System.out.printf("Error processing %s: %s%n", rawInput, e);
            info = getEmptyHashMap();
        }
        return info;
    }

    private String getStartNumber(List<String> input) {
        return input.get(input.size() - 1);
    }

    private String getLastName(List<String> input) {
        return input.get(0);
    }

    private String getCountry(List<String> input) {
        String x = input.get(input.size() - 1);
        List<String> infoList = Arrays.stream(x.split(WHITESPACE)).filter(element -> !element.isEmpty()).toList();
        return infoList.stream().limit(infoList.size() - 1).collect(Collectors.joining(" "));
    }

    private String getFirstName(String input) {
        Long dashCount = input.chars().filter(ch -> ch == '-').count();
        String substring = splitByDash(input).get(dashCount.intValue());
        String fullName = input.substring(0, input.indexOf(substring)).replace(" -", "").replace("- ", "");

        List<String> nameList = splitByWhitespace(fullName);
        String firstName = nameList.get(nameList.size() - 1);
        if (firstName.charAt(firstName.length() - 1) == '-') {
            StringBuilder sb = new StringBuilder(firstName);
            sb.deleteCharAt(firstName.length() - 1);
            firstName = sb.toString();
        }

        return firstName;
    }

    private String getSurname(String input) {
        String fullName = getFullName(input);
        List<String> nameList = splitByWhitespace(fullName);

        return String.join(" ", nameList.subList(1, nameList.size() - 1));
    }

    private List<String> splitByDash(String input) {
        List<String> split = Arrays.asList(input.split(DASH));
        return split.stream().filter(element -> !element.isEmpty()).toList();
    }

    private List<String> splitByWhitespace(String input) {
        List<String> split = Arrays.asList(input.split(WHITESPACE));
        return split.stream().filter(element -> !element.isEmpty()).toList();
    }

    private HashMap<String, String> getEmptyHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("startNumber", "-1");
        result.put("lastName", "");
        result.put("country", "");
        result.put("firstName", "");
        result.put("key", "");
        result.put("surname", "");

        return result;
    }

    private String getFullName(String input) {
        Long dashCount = input.chars().filter(ch -> ch == '-').count();
        String substring = splitByDash(input).get(dashCount.intValue());

        return input.substring(0, input.indexOf(substring)).replace(" -", "").replace("- ", "");
    }
}
