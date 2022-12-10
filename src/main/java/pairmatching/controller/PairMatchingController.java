package pairmatching.controller;

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

    public void run() {
        try {
            inputProcess();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            inputProcess();
        }
    }

    //과정, 레벨, 미션 입력 과정
    public void inputProcess() {
        outputView.printInformation();
        List<String> format = getFormat();
        validateFormat(format);
        List<Crew> crews = crewRepository.getShuffledCrews(format.get(0));
        List<Pair> pair = crewRepository.getPairs(crews);
        outputView.printPairMatching(pair);
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
        validateMission(format.get(2));
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
}
