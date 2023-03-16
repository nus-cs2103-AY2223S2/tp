package seedu.address.model.timetable;

import java.util.List;

/**
 * Represents a module in NUS.
 */
public class Module {
    private List<Lesson> enrolledLessons;

    public Module(List<Lesson> enrolledLessons) {
        this.enrolledLessons = enrolledLessons;
    }

    public List<Lesson> getEnrolledLessons() {
        return enrolledLessons;
    }
}
