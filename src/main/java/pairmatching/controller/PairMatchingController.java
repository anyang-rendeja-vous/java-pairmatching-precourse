package pairmatching.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PairMatchingController {

    public void run() throws IOException {
        List<String> backendCrew = getCrew("src/main/resources/backend-crew.md");
        List<String> frontendCrew = getCrew("src/main/resources/frontend-crew.md");
        List<String> shuffledBackendCrew = getShuffledCrew(backendCrew);
        List<String> shuffledFrontendCrew = getShuffledCrew(frontendCrew);
    }

    public List<String> getCrew(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        List<String> crew = new ArrayList<String>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            crew.add(line);
        }
        bufferedReader.close();
        return crew;
    }

    public List<String> getShuffledCrew(List<String> crew) {
        List<String> shuffledCrew = Randoms.shuffle(crew);
        return shuffledCrew;
    }
}
