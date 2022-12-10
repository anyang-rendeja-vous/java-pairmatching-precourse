package pairmatching.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingChoice;
import pairmatching.domain.PairMatchingRepository;
import pairmatching.ui.InputView;
import pairmatching.ui.OutputView;

public class PairMatcher implements Controller {

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
        List<List<String>> matchedPairs = runPairMatcher(matchingChoice);
        // outputView.showMatchedResult(matchedPairs);
    }

    private MatchingChoice getMatchingChoice() {
        MatchingChoice matchingChoice = getPairMatchingInput();
        if (choiceExists(matchingChoice)) {
            String choice = getNextStep();
            if (choice.equals("네"))
                return matchingChoice;
            if (choice.equals("아니오"))
                getMatchingChoice();
        }
        return matchingChoice;
    }

    private boolean choiceExists(MatchingChoice matchingChoice) {
        return pairMatchingRepository.isExistingChoice(matchingChoice);
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
        while (runCount < 3){
            List<String> shuffledCrews = Randoms.shuffle(crews.getMatchingCrews(matchingChoice));
            List<List<String>> matchedPairs = matchPairs(shuffledCrews);
            if (!pairMatchingRepository.duplicateExists(matchingChoice, matchedPairs)) {
                return matchedPairs;
            }
            runCount++;
        }
        throw new IllegalStateException("페어 매칭 실패 !");
    }

    private List<List<String>> matchPairs(List<String> shuffledCrews) {
        List<List<String>> matchedResult = new ArrayList<>();
        int idx = 0;
        while (idx < shuffledCrews.size()-1) {
            int leftCrewsCount = countCrewsLeft(idx, shuffledCrews.size());
            List<String> pair = new ArrayList<>(shuffledCrews.subList(idx, idx + leftCrewsCount));
            matchedResult.add(pair);
            idx += leftCrewsCount;
        }
        return matchedResult;
    }

    private int countCrewsLeft(int idx, int size) {
        if (size - idx > 3) {
            return 2;
        }
        return size - idx; // 2 나 3 return
    }


}
