package pairmatching.controller;

import static pairmatching.domain.PairRepository.addPair;
import static pairmatching.domain.PairRepository.getPairs;
import static pairmatching.domain.PairRepository.hasPairs;
import static pairmatching.domain.PairRepository.resetPairRepository;
import static pairmatching.domain.PairRepository.validateMatchingHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Missions;
import pairmatching.domain.Pair;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    PairMatchingService pairMatchingService = new PairMatchingService();
    public static String course;
    public static String level;
    public static String mission;

    private int count = 0;

    public void run() {
        outputView.printInformation();
        try {
            do {
                inputProcess();
            } while (!matchingProcess());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    //과정, 레벨, 미션 입력 과정
    public void inputProcess() {
        outputView.printMatchingProcess();
        List<String> format = getFormat();
        course = format.get(0);
        level = format.get(1);
        mission = format.get(2);
        validateFormat();
    }

    //과정, 레벨, 미션 입력 반환
    public List<String> getFormat() {
        String[] input = inputView.getInput().split(", ");
        return new ArrayList<>(Arrays.asList(input));
    }

    //포맷 유효성 확인
    public void validateFormat() {
        validateCourse();
        validateLevel();
        validateMission();
        validateLevelMissionMatch();
    }

    //과정 유효성 확인
    public void validateCourse() {
        Arrays.stream(Course.values())
                .filter(courseElement -> courseElement.getCourseName().equals(course))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정입니다."));
    }

    //레벨 유효성 확인
    public void validateLevel() {
        Arrays.stream(Level.values())
                .filter(levelElement -> levelElement.getLevelName().equals(level))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨입니다."));
    }

    //미션 유효성 확인
    public void validateMission() {
        Arrays.stream(Missions.values())
                .filter(missionElement -> missionElement.getMission().contains(mission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }

    //레벨, 미션 매칭 유효성 확인
    public void validateLevelMissionMatch() {
        Arrays.stream(Missions.values())
                .filter(levelElement -> levelElement.getLevel().equals(level))
                .filter(missionElement -> missionElement.getMission().equals(mission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("레벨과 미션이 일치하지 않습니다."));
    }

    public boolean matchingProcess() {
        boolean flag = true;
        while (flag) {
            List<Pair> pair = pairMatchingService.makePair(course, level);
            if (!hasPairs() || validateMatchingHistory(pair)) {
                addPair(pair);
                outputView.printPairMatching(pair);
                flag = true;
                break;
            }
            if (hasPairs() && !validateMatchingHistory(pair)) {
                if (!rematchingProcess()) {
                    flag = false;
                    break;
                }
                flag = true;
            }
        }
        return flag;
    }

    public void resetPairs() {
        if (!hasPairs()) {
            throw new IllegalArgumentException("페어가 존재하지 않습니다.");
        }
        resetPairRepository();
        count = 0;
        outputView.printResetPairs();
    }

    public boolean rematchingProcess() {
        outputView.printNotificationMessage("매칭 정보가 있습니다. 다시 매칭하겠습니까?");
        outputView.printNotificationMessage("네 | 아니오");
        String input = inputView.getInput();
        validateSelectRematching(input);
        if (input.equals("네")) {
            count++;
            pairMatchingService.validatePairs(count);
            return true;
        }
        return false;
    }

    public void validateSelectRematching(String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw new IllegalArgumentException("네, 아니오만 입력 가능합니다.");
        }
    }

    public void pairsLookUp() {
        outputView.printInformation();
        inputProcess();
        List<Pair> pairs = getPairs();
        validatePairsLoopUp(pairs);
        outputView.printPairMatching(pairs);
    }

    public void validatePairsLoopUp(List<Pair> pairs) {
        if (pairs == null) {
            throw new IllegalArgumentException("페어가 존재하지 않습니다.");
        }
    }
}
