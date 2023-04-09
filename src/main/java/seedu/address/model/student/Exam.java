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
    private final Double weightage;
    private final Grade grade;


    /**
     * Creates a new Exam with the given description, start time, end time, weightage, and grade.
     *
     * @param description The description of the exam.
     * @param startTime   The start time of the exam.
     * @param endTime     The end time of the exam.
     * @param weightage   The weightage of the exam.
     * @param grade       The grade of the exam.
     */
    public Exam(String description, LocalDateTime startTime, LocalDateTime endTime,
                Double weightage, Grade grade) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time.");
        }

        if (weightage != null) {
            if (weightage < 0 || weightage > 100) {
                throw new IllegalArgumentException("Weightage must be a percentage between 0 and 100.");
            }
        }

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weightage = weightage;
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
    public Double getWeightage() {
        return weightage;
    }

    public String getStringWeightage() {
        if (weightage == null) {
            return "Undefined";
        }
        return String.format("%.2f", weightage) + "%";
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
     * Returns the grade of the exam.
     *
     * @return The grade of the exam.
     * @throws UnsupportedOperationException If the exam is not finished.
     */
    public Grade getGrade() {
        return grade;
    }

    public String getStringGrade() {
        if (grade == null) {
            return "Undefined";
        }
        return grade.toString();
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
                && otherExam.getStartTime().equals(getStartTime())
                && otherExam.getEndTime().equals(getEndTime())
                && compareWeightages(otherExam.getWeightage(), getWeightage())
                && compareGrades(otherExam.getGrade(), getGrade());
    }

    private boolean compareWeightages(Double first, Double second) {
        if (first == null && second == null) {
            return true;
        } else {
            if (first == null || second == null) {
                return false;
            } else {
                return Double.compare(first, second) == 0;
            }
        }
    }

    private boolean compareGrades(Grade first, Grade second) {
        if (first == null && second == null) {
            return true;
        } else {
            if (first == null || second == null) {
                return false;
            } else {
                return first.equals(second);
            }
        }
    }

    /**
     * Returns true if both exams have the same description, start time, end time, weightage, and grade.
     * @param otherExam The other exam to compare with.
     * @return True if both exams have the same description, start time, end time, weightage, and grade.
     */
    public boolean isSameTimeExam(Exam otherExam) {
        if (otherExam == this) {
            return true;
        }

        return otherExam != null
            && otherExam.getStartTime().isBefore(getEndTime()) && otherExam.getEndTime().isAfter(getStartTime());
    }

    /**
     * Creates a new Exam with the given description, start time, end time, weightage, and status.
     *
     * @return The new Exam.
     */
    public boolean isPastExam() {
        return LocalDateTime.now().isAfter(endTime);
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
                .append(getStringWeightage())
                .append(" Grade: ")
                .append(getStringGrade());
        return builder.toString();
    }

    /**
     * Returns true if the given lesson is the same as this one.
     * @param lesson The other lesson to compare with.
     * @return True if the given lesson has the same time slot as this one.
     */
    public boolean isSameTimeLesson(Lesson lesson) {
        return lesson != null
            && lesson.getStartTime().isBefore(getEndTime()) && lesson.getEndTime().isAfter(getStartTime());
    }
}
