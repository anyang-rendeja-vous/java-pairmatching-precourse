package pairmatching.domain;

public class Crew {

    private Course course;
    private String name;

    Crew(String name) {
        this.name = name;
    }

    public String getCrewName() {
        return name;
    }
}
