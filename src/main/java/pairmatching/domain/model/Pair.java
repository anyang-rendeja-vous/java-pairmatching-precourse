package pairmatching.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.model.Crew;

public class Pair {

    private final List<Crew> pair;

    public Pair(List<Crew> pair) {
        this.pair = pair;
    }

    public List<Crew> getPair() {
        return Collections.unmodifiableList(pair);
    }

    @Override
    public String toString() {
        List<String> crews = new ArrayList<String>();
        for (Crew crew : pair) {
            crews.add(crew.toString());
        }
        return String.join(" : ", crews) + System.lineSeparator();
    }
}
