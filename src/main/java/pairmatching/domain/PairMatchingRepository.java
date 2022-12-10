package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingRepository {
    private final List<PairMatchingRecord> records = new ArrayList<>();

    public boolean isExistingChoice(MatchingChoice matchingChoice){
        return records.stream()
                .map(PairMatchingRecord::getMatchingChoice)
                .anyMatch(matchingChoice::equals);
    }

    public boolean duplicateExists(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        return records.stream()
                .filter(pairMatchingRecord -> pairMatchingRecord.choiceDuplicates(matchingChoice))
                .anyMatch(pairMatchingRecord -> pairMatchingRecord.matchingResultDuplicates(matchedPairs));
    }
}
