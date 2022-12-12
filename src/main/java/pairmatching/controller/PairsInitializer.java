package pairmatching.controller;

import pairmatching.service.PairMatchingService;
import pairmatching.ui.OutputView;

public class PairsInitializer implements Controller {

    private final OutputView outputView = new OutputView();

    private final PairMatchingService pairMatchingService;

    public PairsInitializer(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void execute() {
        pairMatchingService.initialize();
        outputView.printCleared();
    }
}
