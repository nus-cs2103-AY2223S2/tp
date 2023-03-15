package seedu.address.model.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an Exam in the TutorPro.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Note: This class has a natural ordering that is inconsistent with equals.
 * This is because the natural ordering is based on the start time of the exam,
 * while equals is based on the description, start time, end time, weightage, status, and grade.
 */
public class Exam {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final double weightage;
    private ExamStatus status;
    private Grade grade;

    /**
     * The status of the exam.
     * Finished: The exam has ended.
     * Upcoming: The exam has not started yet.
     * Absent: The student was absent for the exam.
     */
    public enum ExamStatus {
        Finished, Upcoming, Absent;
    };

    /**
     * Creates a new Exam with the given description, start time, end time, weightage, status, and grade.
     *
     * @param description The description of the exam.
     * @param startTime   The start time of the exam.
     * @param endTime     The end time of the exam.
     * @param weightage   The weightage of the exam.
     * @param status      The status of the exam.
     * @param grade       The grade of the exam.
     */
    public Exam(String description, LocalDateTime startTime, LocalDateTime endTime, double weightage,
                ExamStatus status, Grade grade) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time.");
        }

        if (weightage < 0 || weightage > 1) {
            throw new IllegalArgumentException("Weightage must be between 0 and 1.");
        }

        if (status.equals(ExamStatus.Finished)) {
            if (grade == null) {
                throw new IllegalArgumentException("Grade cannot be null if the exam has ended.");
            }
        }

        if (status.equals(ExamStatus.Absent)) {
            if (grade != null) {
                throw new IllegalArgumentException("Grade must be null if the student is absent.");
            }
        }

        if (status.equals(ExamStatus.Upcoming)) {
            if (grade != null) {
                throw new IllegalArgumentException("Grade must be null if the exam has not started yet.");
            }
        }

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
        this.status = status;
        this.grade = grade;
    }

    /**
     * Returns the description of the exam.
     *
     * @return The description of the exam.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the start time of the exam.
     * Note: This method is used for sorting exams by start time.
     *
     * @return The start time of the exam.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the exam.
     *
     * @return The end time of the exam.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the weightage of the exam.
     *
     * @return The weightage of the exam.
     */
    public double getWeightage() {
        return weightage;
    }

    /**
     * Returns the duration of the exam in minutes.
     *
     * @return The duration of the exam in minutes.
     */
    public int getDurationInMinutes() {
        return (int) java.time.Duration.between(startTime, endTime).toMinutes();
    }

    /**
     * Returns the status of the exam.
     * Note: This method is used for sorting exams by status.
     *
     * @return The status of the exam.
     */
    public ExamStatus getStatus() {
        return status;
    }

    /**
     * Returns the grade of the exam.
     *
     * @return The grade of the exam.
     * @throws UnsupportedOperationException If the exam is not finished.
     */
    public Grade getGrade() {
        //        if (!status.equals(ExamStatus.Finished)) {
        //            throw new UnsupportedOperationException("Exam is not finished and does not have a grade.");
        //        }
        return grade;
    }

    /**
     * Updates the grade of the exam, while also updating the completion status of the exam
     *
     * @param grade The grade attained in the exam
     */
    public void updateGrade(Grade grade) {
        this.grade = grade;
        this.status = ExamStatus.Finished;
    }

    /**
     * Updates the status of the exam to Absent
     *
     * @throws UnsupportedOperationException when marking a finished exam as absent
     */
    public void markAsAbsent() {
        if (this.status.equals(ExamStatus.Finished)) {
            throw new UnsupportedOperationException("Exam is already completed!");
        }
        this.status = ExamStatus.Absent;
    }

    /**
     * Returns true if both exams have the same description and start time.
     * This defines a weaker notion of equality between two exams.
     *
     * @param otherExam The other exam to compare with.
     * @return True if both exams have the same description and start time.
     */
    public boolean isSameExam(Exam otherExam) {
        if (otherExam == this) {
            return true;
        }

        return otherExam != null
                && otherExam.getDescription().equals(getDescription())
                && otherExam.getStartTime().equals(getStartTime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exam)) {
            return false;
        }

        Exam otherExam = (Exam) other;

        return otherExam.getDescription().equals(getDescription())
                && otherExam.getStartTime().equals(getStartTime())
                && otherExam.getEndTime().equals(getEndTime())
                && otherExam.getWeightage() == getWeightage()
                && otherExam.getStatus().equals(getStatus())
                && otherExam.getGrade().equals(getGrade());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Start Time: ")
                .append(getStartTime().format(formatter))
                .append(" End Time: ")
                .append(getEndTime().format(formatter))
                .append(" Weightage: ")
                .append(getWeightage())
                .append(" Status: ")
                .append(getStatus());
        if (status.equals(ExamStatus.Finished)) {
            builder.append(" Grade: ").append(getGrade());
        }
        return builder.toString();
    }
}
