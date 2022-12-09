package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

// 특정 미션에 대한 결과
public class MatchingResults {

    private final Map<Mission, List<Pair>> results = new EnumMap<>(Mission.class);

    public MatchingResults(Mission mission, List<Crew> crews) {
        List<Pair> pairs = createPairs(crews);

        // TODO: mission 에 대한 매칭 결과가 이미 존재하는지 확인

        putResult(mission, pairs);
    }

    public List<Pair> createPairs(List<Crew> crews) {
        if (crews.size() % 2 == 0) {
            return createEvenPair(crews);
        }
        return createOddPair(crews);
    }

    private List<Pair> createEvenPair(List<Crew> crewNames) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crewNames.size() - 1; i += 2) {
            List<Crew> crews = new ArrayList<>();
            crews.add(crewNames.get(i));
            crews.add(crewNames.get(i + 1));
            pairs.add(new Pair(crews));
        }
        return pairs;
    }

    private List<Pair> createOddPair(List<Crew> crewNames) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crewNames.size() - 4; i += 2) { // TODO: ???
            List<Crew> crews = new ArrayList<>();
            crews.add(crewNames.get(i));
            crews.add(crewNames.get(i + 1));
            pairs.add(new Pair(crews));
        }
        if (crewNames.size() >= 3) {
            List<Crew> crews = new ArrayList<>();
            crews.add(crewNames.get(crewNames.size() - 1));
            crews.add(crewNames.get(crewNames.size() - 2));
            crews.add(crewNames.get(crewNames.size() - 3));
            pairs.add(new Pair(crews));
        }
        return pairs;
    }

    public void putResult(Mission mission, List<Pair> pairs) {
        results.put(mission, pairs);
    }

    public List<Pair> getResultsByMission(Mission mission) {
        return results.get(mission);
    }
}
