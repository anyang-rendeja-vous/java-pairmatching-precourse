package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PairRepository {

    public static final LinkedHashMap<String, List<Pair>> backendPairs = new LinkedHashMap<>();
    public static final LinkedHashMap<String, List<Pair>> frontendPairs = new LinkedHashMap<>();

    public static void resetPairRepository() {
        backendPairs.clear();
        frontendPairs.clear();
    }

    public static boolean isEmptyPairs(String course) {
        if (course.equals(Course.BACKEND.getCourseName())) {
            return backendPairs.size() == 0;
        }
        return frontendPairs.size() == 0;
    }

    //페어 리스트 추가
    public static void addPair(String course, String level, List<Pair> pairs) {
        if (course.equals(Course.BACKEND.getCourseName())) {
            addBackendPair(level, pairs);
        }
        if (course.equals(Course.FRONTEND.getCourseName())) {
            addFrontendPair(level, pairs);
        }
    }

    //백엔드 페어 리스트 추가
    private static void addBackendPair(String level, List<Pair> pairs) {
        if (backendPairs.get(level) != null) {
            backendPairs.get(level).addAll(pairs);
        }
        backendPairs.putIfAbsent(level, pairs);
    }

    //프론트엔드 페어 리스트 추가
    private static void addFrontendPair(String level, List<Pair> pairs) {
        frontendPairs.put(level, pairs);
    }

    public static boolean hasPairs() {
        return !backendPairs.isEmpty() || !frontendPairs.isEmpty();
    }

    public static boolean validateMatchingHistory(String course, String level, List<Pair> pairs) {
        if (course.equals(Course.BACKEND.getCourseName())) {
            return validateBackendPairs(level, pairs);
        }
        return validateFrontendPairs(level, pairs);
    }

    private static boolean validateBackendPairs(String level, List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (backendPairs.get(level).stream().anyMatch(name -> name.toString().equals(pair.toString()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateFrontendPairs(String level, List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (frontendPairs.get(level).stream().anyMatch(name -> name.toString().equals(pair.toString()))) {
                return false;
            }
        }
        return true;
    }
}
