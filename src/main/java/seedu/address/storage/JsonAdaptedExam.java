package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Homework;

import java.time.LocalDateTime;

public class JsonAdaptedExam {
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;
    private Exam.ExamStatus status;
    private Grade grade;

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
     * Converts this Jackson-friendly adapted homework object into the model's {@code exam} object.
     */
    public Exam toModelType() {
        Exam exam = new Exam(description, startTime, endTime, weightage,
        status, grade);
        return exam;
    }
}
