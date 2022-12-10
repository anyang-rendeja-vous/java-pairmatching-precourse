package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pairs {

    private List<Pair> pairs;

    public Pairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    @Override
    public String toString() {
        List<String> pairList = new ArrayList<>();
        for (Pair pair : pairs) {
            pairList.add(pair.toString());
        }
        return pairList.toString();
    }
}
