package pairmatching.domain.repository;

import static pairmatching.controller.PairMatchController.course;
import static pairmatching.controller.PairMatchController.level;
import static pairmatching.controller.PairMatchController.mission;

import java.util.LinkedHashMap;
import java.util.List;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Crew;
import pairmatching.domain.model.Level;
import pairmatching.domain.model.Mission;
import pairmatching.domain.model.Pair;

public class PairRepository {

    public static final LinkedHashMap<Level, List<Pair>> backendLevelPairs = new LinkedHashMap<>();
    public static final LinkedHashMap<Level, List<Pair>> frontendLevelPairs = new LinkedHashMap<>();
    public static final LinkedHashMap<Mission, List<Pair>> backendMissionPairs = new LinkedHashMap<>();
    public static final LinkedHashMap<Mission, List<Pair>> frontendMissionPairs = new LinkedHashMap<>();

    public static void resetPairRepository() {
        backendLevelPairs.clear();
        frontendLevelPairs.clear();
        backendMissionPairs.clear();
        frontendMissionPairs.clear();
    }

    public static List<Pair> makePair(Course course, Level level) {
        CrewRepository crewRepository = new CrewRepository();
        List<Crew> shuffledCrew = crewRepository.getShuffledCrews(course);
        return crewRepository.getPairs(shuffledCrew);
    }

    //페어 리스트 추가
    public static void addPair(List<Pair> pairs) {
        if (course.equals(Course.BACKEND)) {
            addBackendPair(pairs);
        }
        if (course.equals(Course.FRONTEND)) {
            addFrontendPair(pairs);
        }
    }

    //백엔드 페어 리스트 추가
    private static void addBackendPair(List<Pair> pairs) {
        if (backendLevelPairs.get(level) != null) {
            backendLevelPairs.get(level).addAll(pairs);
        }
        backendLevelPairs.putIfAbsent(level, pairs);
        backendMissionPairs.put(mission, pairs);
    }

    //프론트엔드 페어 리스트 추가
    private static void addFrontendPair(List<Pair> pairs) {
        if (frontendLevelPairs.get(level) != null) {
            frontendLevelPairs.get(level).addAll(pairs);
        }
        frontendLevelPairs.putIfAbsent(level, pairs);
        frontendMissionPairs.put(mission, pairs);
    }

    public static boolean hasPairs() {
        return backendLevelPairs.containsKey(level) || frontendLevelPairs.containsKey(level);
    }

    public static boolean validateMatchingHistory(List<Pair> pairs) {
        if (course.equals(Course.BACKEND)) {
            return validateBackendPairs(pairs);
        }
        return validateFrontendPairs(pairs);
    }

    private static boolean validateBackendPairs(List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (backendLevelPairs.get(level)
                    .stream()
                    .anyMatch(name -> name.toString().equals(pair.toString()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateFrontendPairs(List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (frontendLevelPairs.get(level)
                    .stream()
                    .anyMatch(name -> name.toString().equals(pair.toString()))) {
                return false;
            }
        }
        return true;
    }

    public static List<Pair> getPairs() {
        if (course.equals(Course.BACKEND)) {
            return backendMissionPairs.get(mission);
        }
        return frontendMissionPairs.get(mission);
    }
}
