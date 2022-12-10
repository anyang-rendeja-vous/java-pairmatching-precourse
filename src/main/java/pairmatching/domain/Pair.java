package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair {
    private final List<Crew> pairedCrews = new ArrayList<>();

    public Pair(Crew...crews) {
        pairedCrews.addAll(Arrays.asList(crews));
    }

    // 페어가 순서 상관없이 일치하는지 check -> set 활용..?!
    public boolean isEqualTo(){
        // TODO
    }
}
