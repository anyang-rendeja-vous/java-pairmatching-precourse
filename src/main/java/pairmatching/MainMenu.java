package pairmatching;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.controller.PairMatchingController;

public enum MainMenu {

    PAIR_MATCHING_MANAGEMENT("1", "페어 매칭", new PairMatchingController()::run),
    PAIR_LIST_PRINT_MANAGEMENT("2", "페어 조회", new PairMatchingController()::pairsLookUp),
    PAIR_INITIALIZATION_MANAGEMENT("3", "페어 초기화", new PairMatchingController()::resetPairs),
    QUIT("Q", "종료", null);

    private final String selectNumber;
    private final String title;
    private final Runnable function;

    MainMenu(String selectNumber, String title, Runnable function) {
        this.selectNumber = selectNumber;
        this.title = title;
        this.function = function;
    }

    public String getSelectNumber() {
        return selectNumber;
    }

    public String getTitle() {
        return title;
    }

    public Runnable getFunction() {
        return function;
    }

    public static void printMenu() {
        System.out.println(System.lineSeparator() + "기능을 선택하세요.");
        Arrays.stream(MainMenu.values())
                .map(MainMenu::toString)
                .forEach(System.out::println);
    }

    public static void callLineFunction(String input) {
        MainMenu selectMenu = Arrays.stream(MainMenu.values())
                .filter(function -> Objects.equals(function.getSelectNumber(), input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
        selectMenu.getFunction()
                .run();
    }

    @Override
    public String toString() {
        return getSelectNumber() + ". " + getTitle();
    }
}
