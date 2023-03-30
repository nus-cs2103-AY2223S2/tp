package taa.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import taa.commons.util.SampleDataUtil;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * A utility class to help with building Student objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    public static final String DEFAULT_ATTENDANCE = "0;0;0;0;0;0;0;0;0;0;0;0";

    public static final String DEFAULT_PP = "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";

    private Name name;

    private Attendance attendance;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        attendance = new Attendance(DEFAULT_ATTENDANCE, DEFAULT_PP);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code studentToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        attendance = studentToCopy.getAtd();
        tags = new HashSet<>(studentToCopy.getClassTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public PersonBuilder withAttendance(Attendance atd) {
        this.attendance = atd;
        return this;
    }

    /**
     * @return built student
     */
    public Student build() {
        return new Student(name, attendance.atdStrorageStr(), attendance.partPointsStorageStr(),
                new ArrayList<>(), tags);
    }
}
