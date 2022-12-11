package pairmatching.service;

import static pairmatching.ui.Messages.PAIR_MATCHING_FAILED;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingChoice;
import pairmatching.domain.PairMatchingRepository;

public class PairMatchingService {
    private boolean isRunning = true;
    private static final int LIMIT = 3;

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    private final Crews crews;

    public PairMatchingService(Crews crews) {
        this.crews = crews;
    }

    public boolean checkDuplicatedChoice(MatchingChoice matchingChoice) {
        return pairMatchingRepository.isExistingChoice(matchingChoice);
    }

    public void deleteRecord(MatchingChoice matchingChoice) {
        pairMatchingRepository.deleteRecord(matchingChoice);
    }

    public void storeMatchedPairs(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        pairMatchingRepository.storeMatchedPairs(matchingChoice, matchedPairs);
    }

    private boolean checkMatchedResultDuplicate(MatchingChoice matchingChoice, List<List<String>> matchedPairs) {
        return pairMatchingRepository.duplicateExists(matchingChoice, matchedPairs);
    }

    public List<List<String>> runPairMatcher(MatchingChoice matchingChoice) {
        int runCount = 0;
        while (runCount < LIMIT) {
            List<String> shuffledCrews = Randoms.shuffle(crews.getMatchingCrews(matchingChoice));
            List<List<String>> matchedPairs = matchPairs(shuffledCrews);
            if (!checkMatchedResultDuplicate(matchingChoice, matchedPairs)) {
                return matchedPairs;
            }
            runCount++;
        }
        throw new IllegalStateException(PAIR_MATCHING_FAILED.getMessage());
    }

    public List<List<String>> matchPairs(List<String> shuffledCrews) {
        List<List<String>> matchedResult = new ArrayList<>();
        int idx = 0;
        while (idx < shuffledCrews.size() - 1) {
            int leftCrewsCount = countCrewsLeft(idx, shuffledCrews.size());
            List<String> pair = new ArrayList<>(shuffledCrews.subList(idx, idx + leftCrewsCount));
            matchedResult.add(pair);
            idx += leftCrewsCount;
        }
        return matchedResult;
    }

    private int countCrewsLeft(int idx, int size) {
        if (size - idx > LIMIT) {
            return 2;
        }
        return size - idx; // 2 나 3 return
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopRunning() {
        isRunning = false;
    }
}
