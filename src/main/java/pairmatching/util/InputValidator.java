package pairmatching.util;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String INVALID_INPUT = "백엔드, 레벨1, 자동차경주 의 형태로 입력해야 합니다.";

    public void isFormatValid(String answer) {
        if (!Pattern.matches("^(.+, ){2}.+$", answer)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
