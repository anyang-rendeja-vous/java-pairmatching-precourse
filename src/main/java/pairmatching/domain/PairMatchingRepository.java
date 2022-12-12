package pairmatching.domain;

import static pairmatching.ui.Messages.NON_EXISTENT_MATCH;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingRepository {
    private final List<PairMatchingRecord> records = new ArrayList<>();

    public boolean isExistingChoice(MatchingChoice matchingChoice) {
        return records.stream()
                .anyMatch(pairMatchingRecord -> pairMatchingRecord.choiceDuplicates(matchingChoice));
    }

    public boolean duplicateExists(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        return records.stream()
                .filter(pairMatchingRecord -> pairMatchingRecord.checkCourseAndLevel(matchingChoice))
                .anyMatch(pairMatchingRecord -> pairMatchingRecord.matchingResultDuplicates(matchedPairs));
    }

    public void storeMatchedPairs(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        records.add(new PairMatchingRecord(matchingChoice, matchedPairs));
    }

    public void deleteRecord(MatchingChoice matchingChoice) {
        records.removeIf(pairMatchingRecord -> pairMatchingRecord.choiceDuplicates(matchingChoice));
    }

    public List<List<String>> readMatchingResult(MatchingChoice matchingChoice) {
        return records.stream()
                .filter(record -> record.choiceDuplicates(matchingChoice))
                .map(PairMatchingRecord::getRawPairsData)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(NON_EXISTENT_MATCH.getMessage()));
    }

    public void clear() {
        records.clear();
    }
}
