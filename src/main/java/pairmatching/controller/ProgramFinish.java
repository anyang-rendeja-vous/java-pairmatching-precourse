package pairmatching.controller;

import pairmatching.domain.Crews;
import pairmatching.domain.PairMatchingRepository;

public class ProgramFinish implements Controller {

    // TODO: refactor redundant fields (프로그램 종료와 상관없음)
    private final Crews crews;
    private final PairMatchingRepository pairMatchingRepository;

    public ProgramFinish(Crews crews, PairMatchingRepository pairMatchingRepository) {
        this.crews = crews;
        this.pairMatchingRepository = pairMatchingRepository;
    }

    @Override
    public void execute() {
    }


}
