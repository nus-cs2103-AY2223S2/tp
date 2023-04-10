package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.application.CompanyName;
import seedu.address.model.application.JobTitle;
import seedu.address.model.tag.TaskType;

/**
 * Represents an interested Internship that has not applied in the {@code TodoList}.
 * Guarantees: details are present, field values are validated, immutable.
 */
public class InternshipTodo {
    // Identity fields
    private final CompanyName title;
    private final JobTitle jobTitle;
    private final LocalDate date;
    private final TaskType type;
    private ApplicationDeadline deadline;

    //Optional field
    private NoteContent note;

    /**
     * An InternshipTodo constructor to create an instance of todo internship application with company name
     * {@code title}, {@code jobTitle} and {@code deadline}.
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline) {
        requireAllNonNull(title, jobTitle, deadline);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = null;
        this.date = LocalDate.now();
        this.type = TaskType.TODO;
    }

    /**
     * An InternshipTodo constructor to create an instance of todo internship application with company name
     * {@code title}, {@code jobTitle}, {@code deadline}, date created {@code date} and {@code type}.
     * Every field must be present and not null.
     * This constructor is used to create {@code InternshipTodo} from the Json data file when {@code note} is null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, LocalDate date,
                          TaskType type) {
        requireAllNonNull(title, jobTitle, deadline, date, type);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = null;
        this.date = date;
        this.type = type;
    }

    /**
     * An InternshipTodo constructor to create an instance of todo internship application with company name
     * {@code title}, {@code jobTitle}, {@code deadline} and {@code note}.
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, NoteContent note) {
        requireAllNonNull(title, jobTitle, deadline);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = note;
        this.date = LocalDate.now();
        this.type = TaskType.TODO;
    }

    /**
     * An InternshipTodo constructor to create an instance of todo internship application with company name
     * {@code title}, {@code jobTitle}, {@code deadline}, {@code note}, date created {@code date} and {@code type}.
     * Every field must be present and not null.
     * This constructor is used to create {@code InternshipTodo} from the Json data file, when the {@code note} is
     * not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, NoteContent note,
                          LocalDate date, TaskType type) {
        requireAllNonNull(title, jobTitle, deadline, date, type);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = note;
        this.date = date;
        this.type = type;
    }

    /**
     * Getter for a todo application's title.
     */
    public CompanyName getInternshipTitle() {
        return title;
    }

    /**
     * Getter for a todo application's job title.
     */
    public JobTitle getJobTitle() {
        return jobTitle;
    }

    /**
     * Getter for a todo application's deadline.
     */
    public ApplicationDeadline getDeadline() {
        return deadline;
    }

    /**
     * Getter for a todo application's deadline, it produces string of date in an acceptable format for the date parser
     * before being stored in the Json file.
     */
    public String getJsonDeadline() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(deadline.getDeadline());
    }

    /**
     * Getter for a todo application's note content.
     */
    public NoteContent getNote() {
        return note;
    }

    /**
     * Getter for a todo application's created date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for a todo application's created date, it produces string of date in an acceptable format for the date
     * parser before being stored in the Json file.
     */
    public String getJsonDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
    }

    /**
     * Getter for a todo application's created date string, it produces string of date in format for the displayed date.
     */
    public String getDateString() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(date);
    }

    /**
     * Getter for a todo application's type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Set the deadline for the todo application.
     */
    public void setDeadline(ApplicationDeadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Set the note content for the todo application.
     */
    public void setNote(NoteContent note) {
        this.note = note;
    }

    /**
     * Returns true if both interested internship have the same company name and job title.
     * This defines a weaker notion of equality between two todo applications.
     */
    public boolean isSameTodo(InternshipTodo otherInternship) {
        if (otherInternship == this) {
            return true;
        }
        return otherInternship != null
                && otherInternship.getInternshipTitle().equals(getInternshipTitle())
                && otherInternship.getJobTitle().equals(getJobTitle());
    }

    /**
     * Returns true if both interested internships have the same fields.
     * This defines a stronger notion of equality between two todo applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipTodo)) {
            return false;
        }

        InternshipTodo otherCompany = (InternshipTodo) other;
        boolean areEqual = otherCompany.getInternshipTitle().equals(getInternshipTitle())
                && otherCompany.getJobTitle().equals(getJobTitle())
                && otherCompany.getDeadline().equals(getDeadline())
                && otherCompany.getDate().equals(getDate());

        if (otherCompany.getNote() != null ^ getNote() != null) {
            return false;
        } else if (otherCompany.getNote() == null && getNote() == null) {
            return areEqual;
        } else {
            return areEqual && otherCompany.getNote().equals(getNote());
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, jobTitle, deadline, note, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getType())
                .append("; Create Date: ")
                .append(getDate());

        if (title.fullName != null) {
            builder.append("; CompanyName: ")
                    .append(getInternshipTitle())
                    .append("; Job Title: ")
                    .append(getJobTitle());
        }

        if (!deadline.fullName.equals(DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(LocalDate.MAX))) {
            builder.append("; Deadline: ")
                    .append(getDeadline());
        }

        if (note != null) {
            builder.append("; NoteList: ")
                    .append(getNote());
        }

        return builder.toString();
    }
}
