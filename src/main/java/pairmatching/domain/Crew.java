package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crew {

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

    public List<String> getShuffledCrew(List<String> crew) {
        List<String> shuffledCrew = Randoms.shuffle(crew);
        return shuffledCrew;
    }
}
