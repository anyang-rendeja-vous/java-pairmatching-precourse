package pairmatching.domain;

import java.util.Arrays;

public enum Mission {

    CAR_RACING("자동차경주"),
    LOTTO("로또"),
    BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY_MAP("지하철노선도"),
    PERFORMANCE_IMPROVEMENT("성능개선"),
    DEPLOY("배포");

    private static final String NOT_EXISTED_MISSION_ERROR = "존재하지 않는 미션입니다.";
    private String name;

    Mission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Mission findMissionByName(String missionName) {
        return Arrays.stream(Mission.values())
                .filter(mission -> mission.name.equals(missionName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_MISSION_ERROR));
    }
}
