package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {

    private final List<Crew> pair;

    public Pair(List<Crew> pairs) {
        this.pair = pairs;
    }

    public List<Crew> getPairs() {
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
