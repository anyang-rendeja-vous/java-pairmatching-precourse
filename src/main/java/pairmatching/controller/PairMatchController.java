package pairmatching.controller;

import static pairmatching.domain.repository.PairRepository.addPair;
import static pairmatching.domain.repository.PairRepository.getPairs;
import static pairmatching.domain.repository.PairRepository.hasPairs;
import static pairmatching.domain.repository.PairRepository.makePair;
import static pairmatching.domain.repository.PairRepository.resetPairRepository;
import static pairmatching.domain.repository.PairRepository.validateMatchingHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Level;
import pairmatching.domain.model.Mission;
import pairmatching.domain.model.Pair;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public static Course course;
    public static Level level;
    public static Mission mission;

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
        course = Course.validateCourse(format.get(0));
        level = Level.validateLevel(format.get(1));
        mission = Mission.validateMission(level, format.get(2));
    }

    //과정, 레벨, 미션 입력 반환
    public List<String> getFormat() {
        String[] input = inputView.getInput().split(", ");
        if (input.length != 3) {
            throw new IllegalArgumentException("입력 포맷이 맞지 않습니다.");
        }
        return new ArrayList<>(Arrays.asList(input));
    }

    public boolean matchingProcess() {
        boolean flag = true;
        while (flag) {
            List<Pair> pair = makePair(course, level);
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
                validateRematchCount(++count);
                flag = true;
            }
        }
        return flag;
    }

    public boolean rematchingProcess() {
        outputView.printNotificationMessage("매칭 정보가 있습니다. 다시 매칭하겠습니까?");
        outputView.printNotificationMessage("네 | 아니오");
        String input = inputView.getInput();
        validateSelectRematching(input);
        return input.equals("네");
    }

    public void validateSelectRematching(String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw new IllegalArgumentException("네, 아니오만 입력 가능합니다.");
        }
    }

    public void validateRematchCount(int count) {
        if (count >= 3) {
            throw new IllegalArgumentException("3회 시도까지 매칭되지 않았습니다.");
        }
    }

    public void pairsInquire() {
        outputView.printInformation();
        inputProcess();
        List<Pair> pairs = getPairs();
        validatePairsInquire(pairs);
        outputView.printPairMatching(pairs);
    }

    public void validatePairsInquire(List<Pair> pairs) {
        if (pairs == null) {
            throw new IllegalArgumentException("페어가 존재하지 않습니다.");
        }
    }

    public void resetProcess() {
        resetPairs(count);
        outputView.printResetPairs();
    }

    public void resetPairs(int count) {
        if (!hasPairs()) {
            throw new IllegalArgumentException("페어가 존재하지 않습니다.");
        }
        resetPairRepository();
        count = 0;
    }
}
