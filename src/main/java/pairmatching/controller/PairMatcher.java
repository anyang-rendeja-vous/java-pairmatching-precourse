package pairmatching.controller;

import java.util.List;
import pairmatching.domain.MatchingChoice;
import pairmatching.service.PairMatchingService;
import pairmatching.ui.InputView;
import pairmatching.ui.OutputView;

public class PairMatcher implements Controller {
    private static final String YES = "네";

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final PairMatchingService pairMatchingService;

    public PairMatcher(PairMatchingService service) {
        this.pairMatchingService = service;
    }

    @Override
    public void execute() {
        inputView.showMatchingMenu();
        MatchingChoice matchingChoice = getMatchingChoice();
        try {
            List<List<String>> matchedPairs = pairMatchingService.runPairMatcher(matchingChoice);
            outputView.showMatchedResult(matchedPairs);
            pairMatchingService.storeMatchedPairs(matchingChoice, matchedPairs);
        }
        catch (IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private MatchingChoice getMatchingChoice() {
        MatchingChoice matchingChoice = getPairMatchingInput();
        if (pairMatchingService.checkDuplicatedChoice(matchingChoice)) {
            String choice = getNextStep();
            if (choice.equals(YES)) {
                pairMatchingService.deleteRecord(matchingChoice);
                return matchingChoice;
            }
            return getMatchingChoice();
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
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getPairMatchingInput();
        }
    }


}
