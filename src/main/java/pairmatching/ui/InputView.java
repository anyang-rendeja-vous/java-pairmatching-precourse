package pairmatching.ui;

import static pairmatching.domain.Course.getAllCourses;
import static pairmatching.domain.Level.getAllLevels;
import static pairmatching.ui.InputReader.getUserInput;
import static pairmatching.ui.Messages.INVALID_RETRY_INPUT;

import java.util.StringJoiner;
import pairmatching.domain.Level;

public class InputView {
    private static final String CHOOSE_MENU_OPENING = "기능을 선택하세요.";
    private static final String MAIN_MENU = "1. 페어 매칭\n" + "2. 페어 조회\n"
            + "3. 페어 초기화\n" + "Q. 종료";
    private static final String BOARD_LINE = "#############################################";
    private static final String CHOOSE_INFOS_OPENING = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";

    private static final String ASK_REMATCH = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
            + "네 | 아니오";
    private static final String YES_NO = "네|아니오";

    public String inputMenuChoice() {
        System.out.println(CHOOSE_MENU_OPENING);
        System.out.println(MAIN_MENU);
        return getUserInput();
    }

    public void showMatchingMenu() {
        System.out.println(BOARD_LINE);
        printCourses();
        printLevels();
        System.out.println(BOARD_LINE);
    }

    private void printCourses() {
        StringBuilder builder = new StringBuilder();
        builder.append("과정: ");
        StringJoiner joiner = new StringJoiner(" | ");
        for (String course : getAllCourses()) {
            joiner.add(course);
        }
        System.out.println(builder.append(joiner).toString());
    }

    private void printLevels() {
        System.out.println("미션:");
        for (Level level : getAllLevels()) {
            printMissions(level);
        }
    }

    private void printMissions(Level level) {
        StringBuilder builder = new StringBuilder();
        builder.append("  - ").append(level.getName()).append(": ");
        StringJoiner joiner = new StringJoiner(" | ");
        level.getMissionsByLevel()
                .ifPresent(missions -> missions.forEach(joiner::add));
        System.out.println(builder.append(joiner).toString());
    }

    public String inputPairMatching() {
        System.out.println(CHOOSE_INFOS_OPENING);
        return getUserInput();
    }

    public String inputRematchTry() {
        System.out.println(ASK_REMATCH);
        String userInput = getUserInput();
        if (!userInput.matches(YES_NO)) {
            throw new IllegalArgumentException(INVALID_RETRY_INPUT.getMessage());
        }
        return userInput;
    }
}
