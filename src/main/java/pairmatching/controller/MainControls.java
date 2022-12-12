package pairmatching.controller;

import static pairmatching.ui.Messages.INVALID_MAIN_CHOICE;

import java.util.Arrays;
import java.util.function.Function;
import pairmatching.service.PairMatchingService;

public enum MainControls {
    PAIR_MATCHING("1", PairMatcher::new),
    PAIRS_VIEWER("2", PairsViewer::new),
    PAIRS_INIT("3", PairsInitializer::new),
    QUIT("Q", ProgramFinish::new);

    private final String choice;
    private final Function<PairMatchingService, Controller> controllerMaker;

    MainControls(String choice, Function<PairMatchingService, Controller> controllerMaker) {
        this.choice = choice;
        this.controllerMaker = controllerMaker;
    }

    public Controller generatedController(PairMatchingService pairMatchingService) {
        return controllerMaker.apply(pairMatchingService);
    }

    public static MainControls getMatchingControls(String choice) {
        return Arrays.stream(MainControls.values())
                .filter(controls -> controls.choiceMatches(choice))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MAIN_CHOICE.getMessage()));
    }

    public boolean choiceMatches(String choice) {
        return this.choice.equals(choice);
    }
}
