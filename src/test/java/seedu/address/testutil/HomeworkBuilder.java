package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.student.Homework;

/**
 * A utility class to help with building Homework objects.
 * Example usage: {@code Homework homework = new HomeworkBuilder() }
 */
public class HomeworkBuilder {
    public static final String DEFAULT_DESCRIPTION = "Complete math worksheet";
    public static final LocalDateTime DEFAULT_DEADLINE = LocalDateTime.of(2023, 3, 10, 23, 59);
    public static final Homework.Status DEFAULT_STATUS = Homework.Status.PENDING;

    private String description;
    private LocalDateTime deadline;
    private Homework.Status status;

    /**
     * Creates a {@code HomeworkBuilder} with the default details.
     */
    public HomeworkBuilder() {
        description = DEFAULT_DESCRIPTION;
        deadline = DEFAULT_DEADLINE;
        status = DEFAULT_STATUS;
    }

    /**
     * Initializes the HomeworkBuilder with the data of {@code homeworkToCopy}.
     */
    public HomeworkBuilder(Homework homeworkToCopy) {
        description = homeworkToCopy.getDescription();
        deadline = homeworkToCopy.getDeadline();
        status = homeworkToCopy.getStatus();
    }

    /**
     * Sets the {@code Description} of the {@code Homework} that we are building.
     *
     * @param description The description of the homework.
     */
    public HomeworkBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Homework} that we are building.
     *
     * @param deadline The deadline of the homework.
     */
    public HomeworkBuilder withDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Homework} that we are building.
     *
     * @param status The status of the homework.
     */

    public HomeworkBuilder withStatus(Homework.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Builds a {@code Homework} object.
     *
     * @return The {@code Homework} object.
     */
    public Homework build() {
        Homework newHomework = new Homework(description, deadline);

        // If the status is completed, mark the homework as done.
        if (status == Homework.Status.COMPLETED) {
            newHomework.markAsDone();
        }
        return newHomework;
    }
}
