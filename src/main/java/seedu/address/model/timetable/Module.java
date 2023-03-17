package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a module in NUS.
 */
public class Module {
    private List<Lesson> enrolledLessons;

    private final String moduleCode;

    /**
     * Constructs a Module with a single module code.
     * @param moduleCode
     */
    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
        this.enrolledLessons = new ArrayList<Lesson>();
    }

    /**
     * Constructs a Module with enrolled lessons and module code.
     */
    public Module(List<Lesson> enrolledLessons, String moduleCode) {
        this.enrolledLessons = enrolledLessons;
        this.moduleCode = moduleCode;
    }

    public List<Lesson> getEnrolledLessons() {
        return enrolledLessons;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Module module = (Module) o;
        return moduleCode.equals(module.getModuleCode());
    }

    @Override
    public String toString() {
        return moduleCode;
    }
}
