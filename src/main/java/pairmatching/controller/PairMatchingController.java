package pairmatching.controller;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.Level;
import pairmatching.domain.MatchingResults;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.menu.Menu;
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
        initialCrews();

        while (true) {
            outputView.printInitialMenu();
            String mainChoice = inputView.readMainMenu();

            if (Menu.PAIR_MATCHING.isSamePrefix(mainChoice)) {
                outputView.printMenu();
                String answer = inputView.readAnswer();
                String[] split = answer.split(", "); // 과정 레벨 미션

                Course course = Course.findCourse(split[0]);
                Level level = Level.findLevel(split[1]); // TODO: 레벨이랑 미션이 올바른지 검증
                Mission mission = Mission.findMissionByName(split[2]);

                boolean wantReMatch = true;
                if (MatchingResults.isAlreadyExist(mission)) {
                    String reMatchingAnswer = inputView.readReMatching();
                    if (reMatchingAnswer.equals("아니오")) {
                        wantReMatch = false;
                    }
                }

                if (wantReMatch) {
                    // 1. 페어 매칭
                    MatchingResults matchingResults = new MatchingResults(mission,
                            CrewRepository.getCrewsByCourse(course));
                    List<Pair> resultsByMission = matchingResults.getResultsByMission(mission);
                    outputView.printResult(resultsByMission);
                }
            }

            if (Menu.QUIT.isSamePrefix(mainChoice)) {
                break;
            }
        }
    }

    private void initialCrews() {
        CrewReader crewReader = new CrewReader();
        List<String> backendCrews = crewReader.getShuffledCrew(BACKEND_CREW_NAME_LOCATION);
        backendCrews.forEach(backendCrew -> CrewRepository.addCrew(new Crew(Course.BACKEND, backendCrew)));
        List<String> frontendCrews = crewReader.getShuffledCrew(FRONTEND_CREW_NAME_LOCATION);
        frontendCrews.forEach(frontendCrew -> CrewRepository.addCrew(new Crew(Course.FRONTEND, frontendCrew)));
    }
}
