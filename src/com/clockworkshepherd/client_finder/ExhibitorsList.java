package com.clockworkshepherd.client_finder;

import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;


public class ExhibitorsList {
    PDDocument document;
    ExhibitorInfoExtractor extractor = new ExhibitorInfoExtractor();
    List<Exhibitor> exhibitors = new ArrayList<>();

    ExhibitorsList(Document document) {
        document.getLines().forEach(this::processRawInput);
    }

    public Exhibitor getExhibitor(String key) {
        return exhibitors.stream().filter(exhibitor -> key.equals(exhibitor.getKey())).findAny().orElse(null);
    }

    private Integer processRawInput(String input) {
        HashMap<String, String> exhibitorInfo;
        try {
            exhibitorInfo = extractor.getInfo(input);
        } catch (Exception e) {
            String massage = String.format("Exception %s, on line %s. Skipping.", e, input);
            System.out.println(massage);
            return 1;
        }

        String exhibitorKey = exhibitorInfo.get("key");
        if (exhibitorExists(exhibitorKey)) {
            addStartNumberToExhibitor(exhibitorKey, exhibitorInfo.get("startNumber"));
        } else {
            addExhibitor(exhibitorInfo);
        }
        return 0;
    }

    private void addExhibitor(HashMap<String, String> exhibitorInfo) {
        exhibitors.add(new Exhibitor(exhibitorInfo));
    }

    private void addStartNumberToExhibitor(String exhibitorKey, String stringStartNumber) {
        exhibitors.stream()
                .filter(exhibitor -> Objects.equals(exhibitor.getKey(), exhibitorKey))
                .forEach(exhibitor -> exhibitor.addStartNumber(stringStartNumber));
    }


    private Boolean exhibitorExists(String key) {
        return exhibitors
                .stream().anyMatch(exhibitor -> Objects.equals(exhibitor.key, key));
    }
}

