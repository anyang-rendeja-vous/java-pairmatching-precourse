package pairmatching.controller;

import pairmatching.domain.Crews;
import pairmatching.domain.PairMatchingRepository;

public class PairsViewer implements Controller {

    private final Crews crews;
    private final PairMatchingRepository pairMatchingRepository;

    public PairsViewer(Crews crews, PairMatchingRepository pairMatchingRepository) {
        this.crews = crews;
        this.pairMatchingRepository = pairMatchingRepository;
    }

    @Override
    public void execute() {
    }

}
