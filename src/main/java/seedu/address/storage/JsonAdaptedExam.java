package seedu.address.storage;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.student.Exam;
import seedu.address.model.student.Grade;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;
    private Exam.ExamStatus status;
    private Grade grade;

    /**
     * Constructs a {@code Exam} with the given exam details.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("description") String description,
                           @JsonProperty("startTime") LocalDateTime startTime,
                           @JsonProperty("endTime") LocalDateTime endTime,
                           @JsonProperty("weightage") double weightage,
                           @JsonProperty("status") Exam.ExamStatus status,
                           @JsonProperty("grade") Grade grade) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
        this.status = status;
        this.grade = grade;
    }
    /**
     * Constructs a {@code Exam} with the given exam object.
     */
    public JsonAdaptedExam(Exam source) {
        description = source.getDescription();
        startTime = source.getStartTime();
        endTime = source.getEndTime();
        weightage = source.getWeightage();
        status = source.getStatus();
        grade = source.getGrade();
    }

    /**
     * Converts this Jackson-friendly adapted homework object into the model's {@code exam} object.
     */
    public Exam toModelType() {
        Exam exam = new Exam(description, startTime, endTime, weightage,
                status, grade);
        return exam;
    }
}
