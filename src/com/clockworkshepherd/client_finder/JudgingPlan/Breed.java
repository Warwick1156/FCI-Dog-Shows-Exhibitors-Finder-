package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.List;

public class Breed {
    private String name;
    private String engName;
    private int totalDogsInBreed;
    private List<CompetitionClass> males;
    private List<CompetitionClass> females;

    Breed(String name, String engName, int totalDogsInBreed) {
        this.name = name;
        this.engName = engName;
        this.totalDogsInBreed = totalDogsInBreed;
    }

    public void addMaleCompetition(CompetitionClass competitionClass) {
        males.add(competitionClass);
    }

    public void addFemaleCompetition(CompetitionClass competitionClass) {
        females.add(competitionClass);
    }

    public String getName() {
        return name;
    }

    public String getEngName() {
        return engName;
    }

    public int getTotalDogsInBreed() {
        return totalDogsInBreed;
    }

    public List<CompetitionClass> getMales() {
        return males;
    }

    public List<CompetitionClass> getFemales() {
        return females;
    }
}
