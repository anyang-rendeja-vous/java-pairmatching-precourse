package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingRecord {
    private final MatchingChoice matchingChoice;
    private final List<Pair> matchingResult = new ArrayList<>();

    public PairMatchingRecord(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        this.matchingChoice = matchingChoice;
        matchedPairs
                .forEach(pair -> matchingResult.add(
                        new Pair(matchingChoice.getCourse(), pair)
                ));
    }

    public boolean choiceDuplicates(MatchingChoice matchingChoice) {
        return this.matchingChoice.choiceDuplicates(matchingChoice);
    }

    public boolean matchingResultDuplicates(List<List<String>> matchedPairs) {
        return matchingResult.stream()
                .anyMatch(pair -> pair.duplicates(matchedPairs));
    }
}
