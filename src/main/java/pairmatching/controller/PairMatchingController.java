package pairmatching.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.CrewList;
import pairmatching.domain.Level;
import pairmatching.domain.Missions;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    CrewList crewList = new CrewList();

    public void run() {

    }

    //과정, 레벨, 미션 입력 과정
    public void inputProcess() {
        outputView.printInformation();
        List<String> format = getFormat();
        validateFormat(format);
        List<List<String>> crew = crewList.getPair(format.get(0));
        outputView.printPairMatching(crew);
    }

    //과정, 레벨, 미션 입력 반환
    public List<String> getFormat() {
        String[] input = inputView.getInput().split(", ");
        return new ArrayList<>(Arrays.asList(input));
    }

    //포맷 유효성 확인
    public void validateFormat(List<String> format) {
        validateCourse(format.get(0));
        validateLevel(format.get(1));
        validateMission(format.get(2));
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
                .filter(missions -> missions.getMissionNames().contains(inputMission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }
}
