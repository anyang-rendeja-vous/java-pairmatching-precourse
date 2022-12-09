package pairmatching.domain;

import static pairmatching.domain.Mission.BASEBALL;
import static pairmatching.domain.Mission.CAR_RACING;
import static pairmatching.domain.Mission.DEPLOY;
import static pairmatching.domain.Mission.LOTTO;
import static pairmatching.domain.Mission.PAYMENT;
import static pairmatching.domain.Mission.PERFORMANCE_IMPROVEMENT;
import static pairmatching.domain.Mission.SHOPPING_CART;
import static pairmatching.domain.Mission.SUBWAY_MAP;

import java.util.List;

public enum Level {
    LEVEL1("레벨1", List.of(CAR_RACING, LOTTO, BASEBALL)),
    LEVEL2("레벨2", List.of(SHOPPING_CART, PAYMENT, SUBWAY_MAP)),
    LEVEL3("레벨3", null),
    LEVEL4("레벨4", List.of(PERFORMANCE_IMPROVEMENT, DEPLOY)),
    LEVEL5("레벨5", null);

    private String name;
    private List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    // 추가 기능 구현
}
