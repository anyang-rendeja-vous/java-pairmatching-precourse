package pairmatching.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private static final String NOT_EXISTED_COURSE_ERROR = "존재하지 않는 과정입니다.";
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

    public static Course findCourse(String courseName) {
        return Arrays.stream(Course.values())
                .filter(course -> course.name.equals(courseName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_COURSE_ERROR));
    }
}
