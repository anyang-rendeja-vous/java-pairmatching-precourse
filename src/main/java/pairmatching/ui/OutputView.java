package pairmatching.ui;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR]: " + message + '\n');
    }

    public void showMatchedResult(List<List<String>> matchedPairs) {
        System.out.println();
        for (List<String> matchedPair : matchedPairs) {
            StringJoiner joiner = new StringJoiner(" : ");
            matchedPair.forEach(joiner::add);
            System.out.println(joiner.toString());
        }
        System.out.println();
    }
}
