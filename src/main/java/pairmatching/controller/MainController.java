package pairmatching.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import pairmatching.MainMenu;

public class PairMatchingController {


    public void run() throws IOException {
        MainMenu.printMenu();
        List<String> backendCrew = getCrew("src/main/resources/backend-crew.md");
        List<String> frontendCrew = getCrew("src/main/resources/frontend-crew.md");
        List<List<String>> shuffledBackendCrew = getShuffledCrew(backendCrew);
        List<List<String>> shuffledFrontendCrew = getShuffledCrew(frontendCrew);

    }

    public List<String> getCrew(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        List<String> crew = new ArrayList<>();
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

    public List<List<String>> getShuffledCrew(List<String> crew) {
        List<String> shuffledCrew = Randoms.shuffle(crew);
        final AtomicInteger counter = new AtomicInteger();
        Collection<List<String>> splitShuffledCrew = shuffledCrew.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 2))
                .values();
        return new ArrayList<>(splitShuffledCrew);
    }
}
