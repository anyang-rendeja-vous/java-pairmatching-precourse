package pairmatching.controller;

import java.util.List;
import pairmatching.domain.MatchingChoice;
import pairmatching.service.PairMatchingService;
import pairmatching.ui.InputView;
import pairmatching.ui.OutputView;

public class PairsViewer implements Controller {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final PairMatchingService pairMatchingService;

    public PairsViewer(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void execute() {
        inputView.showMatchingMenu();
        MatchingChoice matchingChoice = getMatchingChoice();
        printPairMatchingResult(matchingChoice);
    }

    private MatchingChoice getMatchingChoice() {
        try {
            String input = inputView.inputPairMatching();
            return new MatchingChoice(input);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getMatchingChoice();
        }
    }

    private void printPairMatchingResult(MatchingChoice matchingChoice) {
        try {
            List<List<String>> matchResult = pairMatchingService.readMatchingResult(matchingChoice);
            outputView.showMatchedResult(matchResult);
        } catch (IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

}
