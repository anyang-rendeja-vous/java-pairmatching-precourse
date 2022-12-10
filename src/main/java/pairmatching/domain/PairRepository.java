package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;

public class PairRepository {

    private final LinkedHashMap<String, List<Pair>> backendPairs = new LinkedHashMap<String, List<Pair>>();
    private final LinkedHashMap<String, List<Pair>> frontendPairs = new LinkedHashMap<String, List<Pair>>();

    public void resetPairRepository() {
        backendPairs.clear();
        frontendPairs.clear();
    }

    //페어 리스트 추가
    public void addPair(List<String> format, List<Pair> pairs) {
        if (format.get(0).equals(Course.BACKEND.getCourseName())) {
            addBackendPair(format.get(2), pairs);
        }
        addFrontendPair(format.get(2), pairs);
    }

    //백엔드 페어 리스트 추가
    private void addBackendPair(String mission, List<Pair> pairs) {
        backendPairs.put(mission, pairs);
    }

    //프론트엔드 페어 리스트 추가
    private void addFrontendPair(String mission, List<Pair> pairs) {
        frontendPairs.put(mission, pairs);
    }
}
