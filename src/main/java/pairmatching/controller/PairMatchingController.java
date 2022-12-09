package pairmatching.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PairMatchingController {

    public void run() throws IOException {
        List<String> backendCrew = getCrew("src/main/resources/backend-crew.md");
        List<String> frontendCrew = getCrew("src/main/resources/frontend-crew.md");
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
}
