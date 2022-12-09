package pairmatching.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private static final String DELIMITER = " | ";
    private String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public String getName() {
        return name;
    }

    public static String reformat() {
        return Arrays.stream(Course.values())
                .map(Course::getName)
                .collect(Collectors.joining(DELIMITER));
    }
}
