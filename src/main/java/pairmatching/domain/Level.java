package pairmatching.domain;

import static pairmatching.domain.Mission.BASEBALL;
import static pairmatching.domain.Mission.CAR_RACING;
import static pairmatching.domain.Mission.DEPLOY;
import static pairmatching.domain.Mission.LOTTO;
import static pairmatching.domain.Mission.PAYMENT;
import static pairmatching.domain.Mission.PERFORMANCE_IMPROVEMENT;
import static pairmatching.domain.Mission.SHOPPING_CART;
import static pairmatching.domain.Mission.SUBWAY_MAP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Level {
    LEVEL1("레벨1", Arrays.asList(CAR_RACING, LOTTO, BASEBALL)),
    LEVEL2("레벨2", Arrays.asList(SHOPPING_CART, PAYMENT, SUBWAY_MAP)),
    LEVEL3("레벨3", null),
    LEVEL4("레벨4", Arrays.asList(PERFORMANCE_IMPROVEMENT, DEPLOY)),
    LEVEL5("레벨5", null);

    private static final String PREFIX = "  - ";
    private static final String SEPARATOR = ": ";
    private static final String DELIMITER = " | ";
    private String name;
    private List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    // 추가 기능 구현
    public static String reformat() {
        return Arrays.stream(Level.values())
                .map(level -> PREFIX + level.name + SEPARATOR + level.getFormattedMissions() + "\n")
                .collect(Collectors.joining());
    }

    public String getFormattedMissions() {
        if (missions == null) {
            return "";
        }
        return missions.stream()
                .map(Mission::getName)
                .collect(Collectors.joining(DELIMITER));
    }
}
