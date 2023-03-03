package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.student.Homework;
import seedu.address.model.student.Homework.Status;

/**
 * Jackson-friendly version of {@link Homework}.
 */
public class JsonAdaptedHomework {

    private final String description;
    private final LocalDateTime deadline;
    private final Status status;

    /**
     * Constructs a {@code JsonAdaptedHomework} with the given homework details.
     */
    @JsonCreator
    public JsonAdaptedHomework(@JsonProperty("description") String description,
                               @JsonProperty("deadline") LocalDateTime deadline,
                               @JsonProperty("status") Status status) {
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    /**
     * Converts a given {@code Homework} into this class for Jackson use.
     */
    public JsonAdaptedHomework(Homework source) {
        description = source.getDescription();
        deadline = source.getDeadline();
        status = source.getStatus();
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("deadline")
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    /**
     * Converts this Jackson-friendly adapted homework object into the model's {@code Homework} object.
     */
    public Homework toModelType() {
        Homework homework = new Homework(description, deadline);
        if (status == Status.COMPLETED) {
            homework.markAsDone();
        } else {
            homework.markAsNotDone();
        }
        return homework;
    }
}
