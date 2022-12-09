package pairmatching.view;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class OutputView {

    private static final String SEPARATOR = "#############################################";
    private static final String COURSE = "과정: ";
    private static final String MISSION = "미션:";

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
}
