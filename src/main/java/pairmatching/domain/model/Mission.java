package pairmatching.domain.model;

import java.util.Arrays;

public enum Mission {

    CAR_RACING("레벨1", "자동차경주"),
    LOTTO("레벨1", "로또"),
    BASEBALL_GAME("레벨1", "숫자야구게임"),
    SHOPPING_BASKET("레벨2", "장바구니"),
    PAYMENT("레벨2", "결제"),
    SUBWAY_MAP("레벨2", "지하철노선도"),
    PERFORMANCE_IMPROVEMENT("레벨4", "성능개선"),
    DISTRIBUTE("레벨4", "배포");

    private final String mission;
    private final String level;

    Mission(String level, String mission) {
        this.level = level;
        this.mission = mission;
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }

    //미션 유효성 확인
    public static Mission validateMission(Level level, String mission) {
        validateLevelMissionMatch(level, mission);
        return Arrays.stream(Mission.values())
                .filter(missionElement -> missionElement.getMission().contains(mission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }

    //레벨, 미션 매칭 유효성 확인
    public static void validateLevelMissionMatch(Level level, String mission) {
        Arrays.stream(Mission.values())
                .filter(levelElement -> levelElement.getLevel().equals(level.getLevelName()))
                .filter(missionElement -> missionElement.getMission().equals(mission))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("레벨과 미션이 일치하지 않습니다."));
    }

//    @Override
//    public String toString() {
//        return mission.toString();
//    }
}
