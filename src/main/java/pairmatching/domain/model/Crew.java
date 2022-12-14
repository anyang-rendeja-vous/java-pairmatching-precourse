package pairmatching.domain.model;

public class Crew {

    private Course course;
    private String crew;

    public Crew(Course course, String crew) {
        this.course = course;
        this.crew = crew;
    }

    public Course getCourse() {
        return course;
    }

    public String getCrew() {
        return crew;
    }

    @Override
    public String toString() {
        return crew;
    }
}
