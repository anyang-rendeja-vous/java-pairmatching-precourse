//package pairmatching;
//
//import java.util.Arrays;
//import pairmatching.domain.Course;
//import pairmatching.domain.Level;
//import pairmatching.domain.Missions;
//
//public class PriorInformation {
//
//    private final String PREFIX = "- ";
//
//    public void printPriorInformation() {
//        printCourse();
//        printLevel();
//        printMissions();
//    }
//
//    private void printCourse() {
//        System.out.println("## 과정");
//        Arrays.stream(Course.values())
//                .map(Course::getCourseName)
//                .forEach(course -> System.out.println(PREFIX + course));
//        System.out.println();
//    }
//
//    private void printLevel() {
//        System.out.println("## 레벨");
//        Arrays.stream(Level.values())
//                .map(Level::getLevelName)
//                .forEach(level -> System.out.println(PREFIX + level));
//        System.out.println();
//    }
//
//    private void printMissions() {
//        System.out.println("## 미션");
//        Arrays.stream(Missions.values())
//                .forEach(set -> {
//                    System.out.println(set.getLevelName());
//                    System.out.println(PREFIX + set.getMissionNames() + System.lineSeparator());
//                });
//    }
//}
