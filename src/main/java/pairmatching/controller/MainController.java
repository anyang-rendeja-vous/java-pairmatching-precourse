package pairmatching.controller;

import static pairmatching.MainFeature.callMainFeature;

import java.util.Arrays;
import pairmatching.MainFeature;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MainController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        while (true) {
            String selectNumber = getSelectMenu();
            if (selectNumber.equals("Q")) {
                break;
            }
            try {
                validateSelectNumber(selectNumber);
                callMainFeature(selectNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    //메뉴 선택
    public String getSelectMenu() {
        MainFeature.printMenu();
        return inputView.getInput();
    }

    //메뉴 유효성 확인
    public void validateSelectNumber(String selectNumber) {
        Arrays.stream(MainFeature.values())
                .filter(number -> number.getSelectNumber().equals(selectNumber))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
