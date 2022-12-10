package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.controller.FileController;

public class CrewRepository {

    FileController fileController = new FileController();

    public List<Crew> getShuffledCrews(String course) {
        List<Crew> crews = getCrews(course);
        return Randoms.shuffle(crews);
    }

    public List<Crew> getCrews(String course) {
        if (course.equals(Course.BACKEND.getCourseName())) {
            return fileController.readBackendCrews();
        }
        return fileController.readFrontendCrews();
    }

    public List<Pair> getPairs(List<Crew> shuffledCrew) {
        List<Pair> pairs = new ArrayList<Pair>();
        while (shuffledCrew.size() > 0){
            if (shuffledCrew.size() == 3) {
                pairs.add(new Pair(getOddParis(shuffledCrew)));
            }
            if (shuffledCrew.size() > 3) {
                pairs.add(new Pair(getEvenPairs(shuffledCrew)));
            }
        }
        return pairs;
    }

    public List<Crew> getOddParis(List<Crew> crew) {
        List<Crew> oddPairs = new ArrayList<Crew>();
        oddPairs.add(crew.remove(0));
        oddPairs.add(crew.remove(0));
        oddPairs.add(crew.remove(0));
        return oddPairs;
    }

    public List<Crew> getEvenPairs(List<Crew> crew) {
        List<Crew> evenPairs = new ArrayList<Crew>();
        evenPairs.add(crew.remove(0));
        evenPairs.add(crew.remove(0));
        return evenPairs;
    }
}
