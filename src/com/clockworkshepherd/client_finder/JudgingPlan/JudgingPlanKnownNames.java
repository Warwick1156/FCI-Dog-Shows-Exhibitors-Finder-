package com.clockworkshepherd.client_finder.JudgingPlan;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JudgingPlanKnownNames {
    private static final String userDir = System.getProperty("user.dir");
    private static final Path breedsJSON = Paths.get(userDir, "data/breedsPL.json");
    private static final Path competitionsJSON = Paths.get(userDir, "data/competitionPL.json");

    List<String> breeds;
    List<String> competitions;

    public JudgingPlanKnownNames() throws IOException {
        KnownNames breedNames = (KnownNames) JsonMapper.map(breedsJSON, KnownNames.class);
        KnownNames competitionNames = (KnownNames) JsonMapper.map(competitionsJSON, KnownNames.class);

        this.breeds = breedNames.names;
        this.competitions = competitionNames.names;
    }
}
