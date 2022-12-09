package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class CrewRepository {

    private static final String NOT_EXISTED_CREW = "존재하지 않는 크루입니다.";
    private static final List<Crew> crews = new ArrayList<>();

    public static void addCrew(Crew crew) {
        crews.add(crew);
    }

    public static Crew findCrew(String findCrew) {
        return crews.stream()
                .filter(crew -> crew.getName().equals(findCrew))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_CREW));
    }

    public static List<Crew> getCrewsByCourse(Course course) {
        List<Crew> resultCrews = new ArrayList<>();
        crews.stream()
                .filter(crew -> crew.getCourse().equals(course))
                .forEach(resultCrews::add);
        return resultCrews;
    }
}
