package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrewList {

    private final String BACKEND_MD = "src/main/resources/backend-crew.md";
    private final String FRONTEND_MD = "src/main/resources/frontend-crew.md";

    //랜덤 페어 반환
    public List<List<String>> getPair(String course) {
        if (course.equals(Course.BACKEND.getCourseName())) {
            try {
                return getShuffledCrew(getCrew(BACKEND_MD));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return getShuffledCrew(getCrew(FRONTEND_MD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //파일 읽어오기
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

    //랜덤
    public List<List<String>> getShuffledCrew(List<String> crew) {
        List<String> shuffledCrew = Randoms.shuffle(crew);
        List<List<String>> splitShuffledCrew = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size() - 1; i += 2) {
            splitShuffledCrew.add(Arrays.asList(shuffledCrew.get(i), shuffledCrew.get(i + 1)));
        }
        if (shuffledCrew.size() % 2 != 0) {
            List<String> tmp = new ArrayList<String>(splitShuffledCrew.get(splitShuffledCrew.size() - 1));
            tmp.add(shuffledCrew.get(shuffledCrew.size() - 1));
            splitShuffledCrew.set(splitShuffledCrew.indexOf(splitShuffledCrew.get(splitShuffledCrew.size() - 1)), tmp);
        }
        return splitShuffledCrew;
    }
}
