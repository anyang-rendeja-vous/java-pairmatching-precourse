package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean checkCourseAndLevel(MatchingChoice matchingChoice){
        return this.matchingChoice.isDuplicated(matchingChoice.getCourse(), matchingChoice.getLevel());
    }

    public boolean choiceDuplicates(MatchingChoice matchingChoice) {
        return this.matchingChoice.isDuplicated(matchingChoice);
    }

    public boolean matchingResultDuplicates(List<List<String>> matchedPairs) {
        return matchingResult.stream()
                .anyMatch(pair -> pair.duplicates(matchedPairs));
    }

    public List<List<String>> getRawPairsData(){
        return matchingResult.stream()
                .map(Pair::getRawPairs)
                .collect(Collectors.toList());
    }
}
