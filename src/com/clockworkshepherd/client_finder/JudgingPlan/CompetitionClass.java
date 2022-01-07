package com.clockworkshepherd.client_finder.JudgingPlan;

public class CompetitionClass {
    String name;
    String engName;
    int dogsInCompetition;
    int fromStartNumber;
    int toStartNumber;

    public CompetitionClass(String name, String engName, int dogsInCompetition, int fromStartNumber, int toStartNumber) {
        this.name = name;
        this.engName = engName;
        this.dogsInCompetition = dogsInCompetition;
        this.fromStartNumber = fromStartNumber;
        this.toStartNumber = toStartNumber;
    }

    public String getName() {
        return name;
    }

    public String getEngName() {
        return engName;
    }

    public int getDogsInCompetition() {
        return dogsInCompetition;
    }

    public int getFromStartNumber() {
        return fromStartNumber;
    }

    public int getToStartNumber() {
        return toStartNumber;
    }
}
