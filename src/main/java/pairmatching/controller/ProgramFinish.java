package pairmatching.controller;

import pairmatching.service.PairMatchingService;

public class ProgramFinish implements Controller {

    private final PairMatchingService pairMatchingService;

    public ProgramFinish(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public void execute() {
    }


}
