package pairmatching;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.controller.PairMatchController;

public enum MainFeature {

    PAIR_MATCHING_MANAGEMENT("1", "페어 매칭", new PairMatchController()::run),
    PAIR_LIST_PRINT_MANAGEMENT("2", "페어 조회", new PairMatchController()::pairsInquire),
    PAIR_INITIALIZATION_MANAGEMENT("3", "페어 초기화", new PairMatchController()::resetProcess),
    QUIT("Q", "종료", null);

    private final String selectNumber;
    private final String title;
    private final Runnable function;

    MainFeature(String selectNumber, String title, Runnable function) {
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
        Arrays.stream(MainFeature.values())
                .map(MainFeature::toString)
                .forEach(System.out::println);
    }

    public static void callMainFeature(String input) {
        MainFeature selectMenu = Arrays.stream(MainFeature.values())
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
