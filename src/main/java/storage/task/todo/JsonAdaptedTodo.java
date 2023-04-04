package storage.task.todo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.NoteContent;

/**
 * Jackson-friendly version of {@link InternshipTodo}.
 */
public class JsonAdaptedTodo {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship application 's %s field is missing!";

    private final String title;
    private final String jobTitle;
    private final String deadline;
    private final String note;
    private final String date;
    private final String type;

    /**
     * Constructs a {@code JsonAdaptedInternshipTodo} with the given InternshipTodo details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("title") String title,
                           @JsonProperty("jobTitle") String jobTitle,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("note") String note,
                           @JsonProperty("date") String date,
                           @JsonProperty("type") String type) {
        this.title = title;
        this.jobTitle = jobTitle;
        this.deadline = deadline;
        this.note = note;
        this.date = date;
        this.type = type;
    }

    /**
     * Converts a given {@code InternshipTodo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(InternshipTodo source) {
        title = source.getInternshipTitle().fullName;
        jobTitle = source.getJobTitle().fullName;
        deadline = source.getJsonDeadline();
        if (source.getNote() != null) {
            note = source.getNote().content;
        } else {
            note = null;
        }
        date = source.getJsonDate();
        type = source.getType().toString();
    }

    /**
     * Converts this Jackson-friendly adapted InternshipTodo object
     * into the model's {@code InternshipTodo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted InternshipTodo.
     */
    public InternshipTodo toModelType() throws IllegalValueException {
        final CompanyName modelTitle = titleToModelType();

        final JobTitle modelJobTitle = jobToModelType();

        final LocalDate modelDate = dateToModelType();

        final TaskType modelType = typeToModelType();

        final ApplicationDeadline modelDeadline = deadlineToModelType();

        if (note != null) {
            final NoteContent modelContent = noteToModelType();

            return new InternshipTodo(
                    modelTitle, modelJobTitle, modelDeadline, modelContent, modelDate, modelType);
        }
        return new InternshipTodo(
                modelTitle, modelJobTitle, modelDeadline, modelDate, modelType);
    }

    private CompanyName titleToModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(title)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(title);
    }

    private JobTitle jobToModelType() throws IllegalValueException {
        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(jobTitle);
    }

    private LocalDate dateToModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date created"));
        }
        return LocalDate.parse(date);
    }

    private TaskType typeToModelType() throws IllegalValueException {
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskType.class.getSimpleName()));
        }
        if (!TaskType.isValidTodo(type)) {
            throw new IllegalValueException(String.format(TaskType.MESSAGE_CONSTRAINTS, type));
        }
        return TaskType.TODO;
    }

    private ApplicationDeadline deadlineToModelType() throws IllegalValueException {
        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationDeadline.class.getSimpleName()));
        }
        if (!ApplicationDeadline.isValidDate(LocalDate.parse(deadline))) {
            throw new IllegalValueException(ApplicationDeadline.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationDeadline(LocalDate.parse(deadline));
    }

    private NoteContent noteToModelType() throws IllegalValueException {
        if (!NoteContent.isValidContent(note)) {
            throw new IllegalValueException(NoteContent.MESSAGE_CONSTRAINTS);
        }
        return new NoteContent(note);
    }
}
