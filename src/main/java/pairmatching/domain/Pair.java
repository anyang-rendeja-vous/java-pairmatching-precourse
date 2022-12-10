package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pair {
    private final List<Crew> pairedCrews = new ArrayList<>();

    public Pair(Crew...crews) {
        pairedCrews.addAll(Arrays.asList(crews));
    }

    // 페어가 순서 상관없이 일치하는지 check -> set 활용..?!
    public boolean duplicates(List<List<String>> crews){
        Set<String> pairs = pairedCrews.stream()
                .map(Crew::getName)
                .collect(Collectors.toSet());
        return crews.stream()
                .anyMatch(crew -> pairs.equals(new HashSet<>(crew)));
    }
}
