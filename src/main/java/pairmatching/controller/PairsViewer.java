package pairmatching.controller;

import pairmatching.service.PairMatchingService;

public class PairsViewer implements Controller {

    private final PairMatchingService pairMatchingService;

    public PairsViewer(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void execute() {
    }

}
