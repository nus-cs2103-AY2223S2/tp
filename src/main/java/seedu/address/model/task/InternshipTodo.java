package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.tag.TodoType;

/**
 * Represents an Interested Internship Programme in the planner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipTodo {
    // Identity fields
    private final CompanyName title;
    private final JobTitle jobTitle;
    private final LocalDate date;
    private final TodoType type;

    //Optional field
    private ApplicationDeadline deadline;
    private NoteContent note;

    /**
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline) {
        requireAllNonNull(title, jobTitle, deadline);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = null;
        this.date = LocalDate.now();
        this.type = TodoType.TODO;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, LocalDate date,
                          TodoType type) {
        requireAllNonNull(title, jobTitle, deadline, date, type);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = null;
        this.date = date;
        this.type = type;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, NoteContent note) {
        requireAllNonNull(title, jobTitle, deadline);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = note;
        this.date = LocalDate.now();
        this.type = TodoType.TODO;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipTodo(CompanyName title, JobTitle jobTitle, ApplicationDeadline deadline, NoteContent note,
                          LocalDate date, TodoType type) {
        requireAllNonNull(title, jobTitle, deadline, date, type);
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = note;
        this.date = date;
        this.type = type;
    }

    public CompanyName getInternshipTitle() {
        return title;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public ApplicationDeadline getDeadline() {
        return deadline;
    }

    public String getJsonDeadline() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(deadline.getDeadline());
    }

    public NoteContent getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getJsonDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
    }

    public String getDateString() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(date);
    }

    public TodoType getType() {
        return type;
    }

    public void setDeadline(ApplicationDeadline deadline) {
        this.deadline = deadline;
    }

    public void setNote(NoteContent note) {
        this.note = note;
    }

    /**
     * Returns true if both interested internship have the same title.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameTodo(InternshipTodo otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getInternshipTitle().equals(getInternshipTitle());
    }

    /**
     * Returns true if both interested internships have the same fields.
     * This defines a stronger notion of equality between two internships.
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
        return otherCompany.getInternshipTitle().equals(getInternshipTitle())
                && otherCompany.getJobTitle().equals(getJobTitle())
                && otherCompany.getDeadline().equals(getDeadline())
                && otherCompany.getNote().equals(getNote())
                && otherCompany.getDate().equals(getDate());
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
