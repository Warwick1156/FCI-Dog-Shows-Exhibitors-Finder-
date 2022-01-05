package com.clockworkshepherd.client_finder;


public class RingBuilder {
    String judge = "";
    int ringNo = -1;
    int hallNo = -1;
    int dogsTotal = -1;
    int judgingStartHour = 0;
    int judgingStartMinute = 0;

    public Ring build() {
        return new Ring(judge, ringNo, hallNo, dogsTotal, judgingStartHour, judgingStartMinute);
    }

    public RingBuilder judge(String _judge) {
        this.judge = _judge;
        return this;
    }

    public RingBuilder ringNumber(int _ringNo) {
        this.ringNo = _ringNo;
        return this;
    }

    public RingBuilder hallNumber(int _hallNo) {
        this.hallNo = _hallNo;
        return this;
    }

    public RingBuilder dogsTotal(int _dogsTotal) {
        this.dogsTotal = _dogsTotal;
        return this;
    }

    public RingBuilder judgingStartHour(int _judgingStartHour) {
        this.judgingStartHour = _judgingStartHour;
        return this;
    }

    public RingBuilder judgingStartMinute(int _judgingStartMinute) {
        this.judgingStartMinute = _judgingStartMinute;
        return this;
    }
}
