package pairmatching.controller;

import java.util.List;
import pairmatching.util.CrewReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private static final String BACKEND_CREW_NAME_LOCATION = "./src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_NAME_LOCATION = "./src/main/resources/frontend-crew.md";
    private final InputView inputView;
    private final OutputView outputView;

    public PairMatchingController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printMenu();
        inputView.readAnswer();

        CrewReader crewReader = new CrewReader();
        List<String> shuffledCrew = crewReader.getShuffledCrew(BACKEND_CREW_NAME_LOCATION);
    }
}
