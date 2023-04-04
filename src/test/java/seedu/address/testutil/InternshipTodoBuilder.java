package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.NoteContent;

/**
 * A utility class to help with building {@code InternshipTodo} objects.
 */
public class InternshipTodoBuilder {

    private static final CompanyName DEFAULT_TITLE = new CompanyName("Aloha Nice");
    private static final JobTitle DEFAULT_JOB = new JobTitle("Software Engineer");
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final TaskType TASK_TYPE = TaskType.TODO;
    private static final ApplicationDeadline DEFAULT_DEADLINE = new ApplicationDeadline(LocalDate.parse("2025-03-04"));
    private static final NoteContent DEFAULT_NOTE = new NoteContent("No content");

    private CompanyName title;
    private JobTitle jobTitle;
    private LocalDate date;
    private TaskType type;
    private ApplicationDeadline deadline;
    private NoteContent note;

    /**
     * Creates a {@code InternshipTodoBuilder} with the default details.
     */
    public InternshipTodoBuilder() {
        title = DEFAULT_TITLE;
        jobTitle = DEFAULT_JOB;
        date = CURRENT_DATE;
        type = TASK_TYPE;
        deadline = DEFAULT_DEADLINE;
        note = DEFAULT_NOTE;
    }

    /**
     * Initializes the {@code InternshipTodoBuilder} with the data of {@code todoToCopy}.
     */
    public InternshipTodoBuilder(InternshipTodo todoToCopy) {
        title = todoToCopy.getInternshipTitle();
        jobTitle = todoToCopy.getJobTitle();
        date = todoToCopy.getDate();
        type = todoToCopy.getType();
        deadline = todoToCopy.getDeadline();
        note = todoToCopy.getNote();
    }

    /**
     * Sets the {@code Title} of the {@code InternshipTodo} that we are building.
     */
    public InternshipTodoBuilder withTitle(String title) {
        this.title = new CompanyName(title);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code InternshipTodo} that we are building.
     */
    public InternshipTodoBuilder withJobTitle(String job) {
        this.jobTitle = new JobTitle(job);
        return this;
    }

    /**
     * Sets the {@code LocalDate} of the {@code InternshipTodo} that we are building.
     */
    public InternshipTodoBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     * Sets the {@code ApplicationDeadline} of the {@code InternshipTodo} that we are building.
     */
    public InternshipTodoBuilder withDeadline(String deadline) {
        this.deadline = new ApplicationDeadline(LocalDate.parse(deadline));
        return this;
    }

    /**
     * Sets the {@code NoteContent} of the {@code InternshipTodo} that we are building.
     */
    public InternshipTodoBuilder withNote(String note) {
        this.note = new NoteContent(note);
        return this;
    }

    public InternshipTodo basicBuild() {
        return new InternshipTodo(DEFAULT_TITLE, DEFAULT_JOB, DEFAULT_DEADLINE);
    }

    public InternshipTodo build() {
        return new InternshipTodo(title, jobTitle, deadline, note, date, type);
    }
}
