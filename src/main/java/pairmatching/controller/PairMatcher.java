package pairmatching.controller;

import static pairmatching.ui.Messages.PAIR_MATCHING_FAILED;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingChoice;
import pairmatching.domain.PairMatchingRepository;
import pairmatching.ui.InputView;
import pairmatching.ui.OutputView;

public class PairMatcher implements Controller {
    private static final String YES = "네";
    private static final int LIMIT = 3;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();
    private final Crews crews;

    public PairMatcher(Crews crews) {
        this.crews = crews;
    }

    @Override
    public void execute() {
        inputView.showMatchingMenu();
        MatchingChoice matchingChoice = getMatchingChoice();
        try {
            List<List<String>> matchedPairs = runPairMatcher(matchingChoice);
            outputView.showMatchedResult(matchedPairs);
            pairMatchingRepository.storeMatchedPairs(matchingChoice, matchedPairs);
        }
        catch (IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private MatchingChoice getMatchingChoice() {
        MatchingChoice matchingChoice = getPairMatchingInput();
        if (pairMatchingRepository.isExistingChoice(matchingChoice)) {
            String choice = getNextStep();
            if (choice.equals(YES)) {
                return matchingChoice;
            }
            getMatchingChoice();
        }
        return matchingChoice;
    }

    private String getNextStep() {
        try {
            return inputView.inputRematchTry();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getNextStep();
        }
    }

    private MatchingChoice getPairMatchingInput() {
        try {
            String input = inputView.inputPairMatching();
            return new MatchingChoice(input);
        } catch (Exception exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getPairMatchingInput();
        }
    }

    private List<List<String>> runPairMatcher(MatchingChoice matchingChoice) {
        int runCount = 0;
        while (runCount < LIMIT) {
            List<String> shuffledCrews = Randoms.shuffle(crews.getMatchingCrews(matchingChoice));
            List<List<String>> matchedPairs = matchPairs(shuffledCrews);
            if (!pairMatchingRepository.duplicateExists(matchingChoice, matchedPairs)) {
                return matchedPairs;
            }
            runCount++;
        }
        throw new IllegalStateException(PAIR_MATCHING_FAILED.getMessage());
    }

    private List<List<String>> matchPairs(List<String> shuffledCrews) {
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


}
