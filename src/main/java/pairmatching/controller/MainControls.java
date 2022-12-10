package pairmatching.controller;

import java.util.Arrays;
import java.util.function.BiFunction;
import pairmatching.domain.Crews;
import pairmatching.domain.PairMatchingRepository;

public enum MainControls {
    PAIR_MATCHING("1", PairMatcher::new),
    PAIRS_VIEWER("2", PairsViewer::new),
    PAIRS_INIT("3", PairsInitializer::new),
    QUIT("Q", ProgramFinish::new);

    private final String choice;
    private final BiFunction<Crews, PairMatchingRepository, Controller> controllerMaker;

    MainControls(String choice, BiFunction<Crews, PairMatchingRepository, Controller> controllerMaker) {
        this.choice = choice;
        this.controllerMaker = controllerMaker;
    }

    public Controller generatedController(Crews crews, PairMatchingRepository pairMatchingRepository) {
        return controllerMaker.apply(crews, pairMatchingRepository);
    }

    public static MainControls getMatchingControls(String choice) {
        return Arrays.stream(MainControls.values())
                .filter(controls -> controls.choiceMatches(choice))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("error!"));
    }

    public boolean choiceMatches(String choice) {
        return this.choice.equals(choice);
    }
}
