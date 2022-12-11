package pairmatching.domain;

import static pairmatching.ui.Messages.NON_EXISTENT_LEVEL;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", null),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", null);

    private String name;
    private List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    // 추가 기능 구현
    public static Level findMatchingLevel(String levelInput) {
        return Arrays.stream(Level.values())
                .filter(level -> level.hasLevel(levelInput))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTENT_LEVEL.getMessage()));
    }

    private boolean hasLevel(String levelInput) {
        return this.name.equals(levelInput);
    }

    public static boolean isValidatedMission(Level level, String mission) {
        return level.missionMatches(mission);
    }

    private boolean missionMatches(String mission) {
        return missions.stream()
                .anyMatch(m -> m.equals(mission));
    }

    public static List<Level> getAllLevels(){
        return Arrays.asList(Level.values());
    }

    public String getName() {
        return name;
    }

    public Optional<List<String>> getMissionsByLevel() {
        return Optional.ofNullable(missions);
    }
}
