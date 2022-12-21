package pairmatching.domain.model;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return name;
    }

    //과정 유효성 확인
    public static Course validateCourse(String course) {
        return Arrays.stream(Course.values())
                .filter(courseElement -> courseElement.getCourseName().equals(course))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정입니다."));
    }
}
