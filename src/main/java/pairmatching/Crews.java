package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class Crews {
    private final List<Crew> crews;

    public Crews() {
        crews = new ArrayList<>();
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }
}
