package pairmatching.controller;

import static pairmatching.domain.PairRepository.addPair;
import static pairmatching.domain.PairRepository.hasPairs;
import static pairmatching.domain.PairRepository.resetPairRepository;
import static pairmatching.domain.PairRepository.validateMatchingHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.Level;
import pairmatching.domain.Missions;
import pairmatching.domain.Pair;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    CrewRepository crewRepository = new CrewRepository();
    private int count = 0;

    public void run() {
        outputView.printInformation();
        try {
            List<String> format = inputProcess();
            makePair(format.get(0), format.get(1));
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            inputProcess();
        }
    }

    //과정, 레벨, 미션 입력 과정
    public List<String> inputProcess() {
        outputView.printMatchingProcess();
        List<String> format = getFormat();
        validateFormat(format);
        return format;
    }

    //과정, 레벨, 미션 입력 반환
    public List<String> getFormat() {
        String[] input = inputView.getInput().split(", ");
        return new ArrayList<>(Arrays.asList(input));
    }

    //포맷 유효성 확인
    public void validateFormat(List<String> format) {
        String course = format.get(0);
        String level = format.get(1);
        String mission = format.get(2);
        validateCourse(course);
        validateLevel(level);
        validateMission(mission);
        validateLevelMissionMatch(level, mission);
    }

    //과정 유효성 확인
    public void validateCourse(String inputCourse) {
        Arrays.stream(Course.values())
                .filter(course -> course.getCourseName().equals(inputCourse))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정입니다."));
    }

    //레벨 유효성 확인
    public void validateLevel(String inputLevel) {
        Arrays.stream(Level.values())
                .filter(level -> level.getLevelName().equals(inputLevel))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨입니다."));
    }

    //미션 유효성 확인
    public void validateMission(String inputMission) {
        Arrays.stream(Missions.values())
                .filter(missions -> missions.getMission().contains(inputMission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }

    //레벨, 미션 매칭 유효성 확인
    public void validateLevelMissionMatch(String level, String mission) {
        Arrays.stream(Missions.values())
                .filter(levelElement -> levelElement.getLevel().equals(level))
                .filter(missionElement -> missionElement.getMission().equals(mission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("레벨과 미션이 일치하지 않습니다."));
    }

    public void makePair(String course, String level) {
        List<Crew> crews = crewRepository.getShuffledCrews(course);
        List<Pair> pair = crewRepository.getPairs(crews);
        if (hasPairs() && !validatePair(course, level, pair)) {
            count++;
            selectRematching(course, level);
        }
        outputView.printPairMatching(pair);
        addPair(course, level, pair);
    }

    public void resetPairs() {
        if (!hasPairs()) {
            throw new IllegalArgumentException("페어가 존재하지 않습니다.");
        }
        resetPairRepository();
        outputView.printResetPairs();
    }

    public boolean validatePair(String course, String level, List<Pair> pairs) {
        return validateMatchingHistory(course, level, pairs);
    }

    public void selectRematching(String course, String level) {
        outputView.printNotificationMessage("매칭 정보가 있습니다. 다시 매칭하겠습니까?");
        outputView.printNotificationMessage("네 | 아니오");
        String input = inputView.getInput();
        try {
            validateSelectRematching(input);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            selectRematching(course, level);
        }
        if (input.equals("네")) {
            makePair(course, level);
        }
        if (input.equals("아니오")) {
            inputProcess();
        }
    }

    public void validateSelectRematching(String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw new IllegalArgumentException("네, 아니오만 입력 가능합니다.");
        }
    }
}
