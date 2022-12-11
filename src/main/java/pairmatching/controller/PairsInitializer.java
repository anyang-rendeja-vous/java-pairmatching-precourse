package pairmatching.controller;

import pairmatching.service.PairMatchingService;

public class PairsInitializer implements Controller {

    private final PairMatchingService pairMatchingService;

    public PairsInitializer(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void execute() {
    }
}
