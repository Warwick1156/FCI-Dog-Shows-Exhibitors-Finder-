package com.clockworkshepherd.client_finder;

import java.util.ArrayList;
import java.util.List;

public class RingExtractor {
    Document document;
    List<Ring> ringList = new ArrayList<>();

    RingExtractor(Document document) {
        this.document = document;
    }

    private void add(Ring ring) {
        ringList.add(ring);
    }

    public void extract() {

    }

//    private Ring getRing() {
//        return new Ring();
//    }

    public boolean isRingHeader(String line) {
        return line.contains("Ring");
    }

//    private void extractHeaderInfo(String line) {
//        List<String> splitLine = List.of(line.split(" "));
//
//        line.indexOf("Ring")
//    }

    private boolean isJudge(String line) {
        return line.contains("Sędzia");
    }

}
