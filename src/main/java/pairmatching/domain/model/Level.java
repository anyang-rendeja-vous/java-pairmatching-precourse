package pairmatching.domain.model;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getLevelName() {
        return name;
    }

    //레벨 유효성 확인
    public static Level validateLevel(String level) {
        return Arrays.stream(Level.values())
                .filter(levelElement -> levelElement.getLevelName().equals(level))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨입니다."));
    }
}
