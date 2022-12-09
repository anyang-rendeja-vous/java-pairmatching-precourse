package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrewReader {

    public List<String> getCrewFromFile(String location) {
        List<Character> crewNames = readCrewNames(location); // 파일에서 로드한 크루 이름 목록
        return crewNameToList(crewNames); // 파일에서 로드한 크루 이름 목록
    }

//    public List<String> getCrewFromFile(String location) {
//        List<String> crewNames = getCrewNames(location);
//        return Randoms.shuffle(crewNames); // 섞인 크루 이름 목록
//    }

//    private List<String> getCrewNames(String location) {
//        List<Character> crewNames = readCrewNames(location); // 파일에서 로드한 크루 이름 목록
//        return crewNameToList(crewNames); // 파일에서 로드한 크루 이름 목록
//    }

    private List<Character> readCrewNames(String location) {
        List<Character> chars = new ArrayList<>(); // 파일에서 로드한 크루 이름 목록
        File file = new File(location);
        try (FileReader fr = new FileReader(file))
        {
            int content;
            while ((content = fr.read()) != -1) {
                chars.add((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chars;
    }

    private List<String> crewNameToList(List<Character> chars) {
        List<String> crewNames;
        StringBuilder sb = new StringBuilder();
        for (Character item : chars) {
            sb.append(item);
        }
        crewNames = Arrays.asList(sb.toString().split("\n"));
        return crewNames;
    }
}
