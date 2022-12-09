package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.InputValidator;

public class InputView {

    private static final String ENTER_ANSWER = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private final InputValidator inputValidator;

    public InputView() {
        inputValidator = new InputValidator();
    }

    private String input() {
        return Console.readLine();
    }

    public String readAnswer() {
        System.out.println(ENTER_ANSWER);
        String answer = input();
        inputValidator.isFormatValid(answer);
        return answer;
    }
}
