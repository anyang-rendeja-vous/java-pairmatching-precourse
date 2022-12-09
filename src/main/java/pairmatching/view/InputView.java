package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.InputValidator;

public class InputView {

    private static final String CHOICE_OPTION = "기능을 선택하세요.";
    private static final String ENTER_ANSWER = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private final InputValidator inputValidator;

    public InputView() {
        inputValidator = new InputValidator();
    }

    private String input() {
        return Console.readLine();
    }

    public String readMainMenu() {
        System.out.println(CHOICE_OPTION);
        String answer = input();
        inputValidator.isRangeValid(answer);
        return answer;
    }

    public String readAnswer() {
        System.out.println(ENTER_ANSWER);
        String answer = input();
        inputValidator.isFormatValid(answer);
        return answer;
    }

    public String readReMatching() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        return input();
    }
}
