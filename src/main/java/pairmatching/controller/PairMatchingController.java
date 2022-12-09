package pairmatching.controller;

import java.io.IOException;
import java.util.List;
import pairmatching.domain.Crew;

public class PairMatchingController {

    Crew crew = new Crew();

    public void run() throws IOException {
        List<String> backendCrew = crew.getCrew("src/main/resources/backend-crew.md");
        List<String> frontendCrew = crew.getCrew("src/main/resources/frontend-crew.md");
        List<String> shuffledBackendCrew = crew.getShuffledCrew(backendCrew);
        List<String> shuffledFrontendCrew = crew.getShuffledCrew(frontendCrew);
    }

}
