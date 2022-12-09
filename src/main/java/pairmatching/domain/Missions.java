package pairmatching.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Missions {

    MISSION_LEVEL_1("### 레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    MISSION_LEVEL_2("### 레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    MISSION_LEVEL_3("### 레벨3(없음)", Collections.singletonList("")),
    MISSION_LEVEL_4("### 레벨4", Arrays.asList("성능개선", "배포")),
    MISSION_LEVEL_5("### 레벨5(없음)", Collections.singletonList(""));

    private final String levelName;
    private final List<String> missionNames;

    Missions(String levelName, List<String> missionNames) {
        this.levelName = levelName;
        this.missionNames = missionNames;
    }

    public String getLevelName() {
        return levelName;
    }

    public List<String> getMissionNames() {
        return missionNames;
    }

    @Override
    public String toString() {
        return missionNames.toString();
    }
}
