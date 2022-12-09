package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;

public class OutputView {

    private static final String SEPARATOR = "#############################################";
    private static final String COURSE = "과정: ";
    private static final String MISSION = "미션:";
    private static final String DELIMITER = " : ";

    public void printMenu() {
        System.out.println(SEPARATOR);
        printCourses();
        printMissions();
        System.out.println(SEPARATOR);
    }

    private void printCourses() {
        System.out.print(COURSE);
        System.out.println(Course.reformat());
    }

    private void printMissions() {
        System.out.println(MISSION);
        System.out.println(Level.reformat());
    }

    public void printResult(List<Pair> pairs) {
        for (Pair pair : pairs) {
            List<Crew> crews = pair.getCrews();
            String result = crews.stream()
                    .map(Crew::getName)
                    .collect(Collectors.joining(DELIMITER));
            System.out.println(result);
        }
    }
}
