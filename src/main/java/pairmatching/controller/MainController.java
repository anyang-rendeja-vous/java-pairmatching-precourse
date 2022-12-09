package pairmatching.controller;

import static pairmatching.MainMenu.callLineFunction;

import java.io.IOException;
import java.util.Arrays;
import pairmatching.MainMenu;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MainController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() throws IOException {
        String selectNumber = getSelectMenu();
        try {
            validateSelectNumber(selectNumber);
            callLineFunction(selectNumber);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }


    //메뉴 선택
    public String getSelectMenu() {
        MainMenu.printMenu();
        return inputView.getInput();
    }

    //메뉴 유효성 확인
    public void validateSelectNumber(String selectNumber) {
        Arrays.stream(MainMenu.values())
                .filter(number -> number.getSelectNumber().equals(selectNumber))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
