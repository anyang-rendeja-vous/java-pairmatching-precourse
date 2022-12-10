package pairmatching.domain;

import java.util.List;

public class PairMatchingRecord {
    private MatchingChoice matchingChoice;
    private List<Pair> matchingResult;

    public MatchingChoice getMatchingChoice() {
        return matchingChoice;
    }

    public boolean choiceDuplicates(MatchingChoice matchingChoice) {
        return this.matchingChoice.choiceDuplicates(matchingChoice);
    }

    public boolean matchingResultDuplicates(List<List<String>> matchedPairs) {
        return matchingResult.stream()
                .anyMatch(pair -> pair.duplicates(matchedPairs));
    }
}
