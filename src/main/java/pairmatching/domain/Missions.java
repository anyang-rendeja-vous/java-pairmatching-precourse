package pairmatching.domain;

public enum Missions {

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

    Missions(String level, String mission) {
        this.level = level;
        this.mission = mission;
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }

    @Override
    public String toString() {
        return mission.toString();
    }
}
