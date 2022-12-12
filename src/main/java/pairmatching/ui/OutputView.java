package pairmatching.ui;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    private static final String INITIALIZED = "초기화 되었습니다.";
    private static final String RESULT_OPENING = "\n페어 매칭 결과입니다.";

    public void printErrorMessage(String message) {
        System.out.println("[ERROR]: " + message + '\n');
    }

    public void showMatchedResult(List<List<String>> matchedPairs) {
        System.out.println(RESULT_OPENING);
        for (List<String> matchedPair : matchedPairs) {
            StringJoiner joiner = new StringJoiner(" : ");
            matchedPair.forEach(joiner::add);
            System.out.println(joiner.toString());
        }
        System.out.println();
    }

    public void printCleared() {
        System.out.println('\n' + INITIALIZED + '\n');
    }
}
