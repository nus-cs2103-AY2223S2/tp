package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.student.Homework;
import seedu.address.model.student.Lesson;

/**
 * Jackson-friendly version of {@link Homework}.
 */
public class JsonAdaptedLesson {

    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a {@code JsonAdaptedHomework} with the given homework details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("title") String title,
                               @JsonProperty("startTime") LocalDateTime startTime,
                               @JsonProperty("endTime") LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        title = source.getTitle();
        startTime = source.getStartTime();
        endTime = source.getEndTime();
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("startTime")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @JsonProperty("endTime")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     */
    public Lesson toModelType() {
        Lesson lesson = new Lesson(title, startTime, endTime);
        return lesson;
    }
}
