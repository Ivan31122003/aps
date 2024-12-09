package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sources {
    private List<Source> sources;

    public Sources(Integer numberOfSources, Integer delayTime, Integer delayForStart) {
        sources = new ArrayList<>();
        for (int i = 0; i < numberOfSources; ++i) {
            sources.add(new Source(delayForStart, delayTime));
        }
    }
}
