package com.clockworkshepherd.client_finder.JudgingPlan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ring {
    String judge;
    int ringNo;
    int hallNo;
    int dogsTotal;
    Calendar judgingStartTime = Calendar.getInstance();
    List<Breed> breedList = new ArrayList<>();

    Ring(String judge, int ringNo, int hallNo, int dogsTotal, int judgingStartHour, int judgingStartMinute) {
        this.judge = judge;
        this.ringNo = ringNo;
        this.hallNo = hallNo;
        this.dogsTotal = dogsTotal;
        this.judgingStartTime.set(Calendar.HOUR, judgingStartHour);
        this.judgingStartTime.set(Calendar.MINUTE, judgingStartMinute);
    }

    public void addBreed(Breed breed) {
        breedList.add(breed);
    }

    public String getJudge() {
        return judge;
    }

    public int getRingNo() {
        return ringNo;
    }

    public int getHallNo() {
        return hallNo;
    }

    public int getDogsTotal() {
        return dogsTotal;
    }

    public Calendar getJudgingStartTime() {
        return judgingStartTime;
    }

    public List<Breed> getBreedList() {
        return breedList;
    }

}
