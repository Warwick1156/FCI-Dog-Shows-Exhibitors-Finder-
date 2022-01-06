package com.clockworkshepherd.client_finder;

import java.util.ArrayList;
import java.util.List;

public class JudgingPlanTextClassifierBuilder {
    List<String> competitions = new ArrayList<>();
    List<String> breeds = new ArrayList<>();

    public JudgingPlanTextClassifierBuilder competitions(List<String> _competitions) {
        this.competitions = _competitions;
        return this;
    }

    public JudgingPlanTextClassifierBuilder breeds(List<String> _breeds) {
        this.breeds = _breeds;
        return this;
    }

    public JudgingPlanTextClassifier build() {
        return new JudgingPlanTextClassifier(competitions, breeds);
    }
}
