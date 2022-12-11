package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;

public class PairRepository {

    private static final LinkedHashMap<String, List<Pair>> backendPairs = new LinkedHashMap<>();
    private static final LinkedHashMap<String, List<Pair>> frontendPairs = new LinkedHashMap<>();

    public static void resetPairRepository() {
        backendPairs.clear();
        frontendPairs.clear();
    }

    //페어 리스트 추가
    public static void addPair(List<String> format, List<Pair> pairs) {
        if (format.get(0).equals(Course.BACKEND.getCourseName())) {
            addBackendPair(format.get(2), pairs);
        }
        if (format.get(0).equals(Course.FRONTEND.getCourseName())) {
            addFrontendPair(format.get(2), pairs);
        }
    }

    //백엔드 페어 리스트 추가
    private static void addBackendPair(String mission, List<Pair> pairs) {
        backendPairs.put(mission, pairs);
    }

    //프론트엔드 페어 리스트 추가
    private static void addFrontendPair(String mission, List<Pair> pairs) {
        frontendPairs.put(mission, pairs);
    }

    public static boolean hasPairs() {
        return !backendPairs.isEmpty() || !frontendPairs.isEmpty();
    }
}
