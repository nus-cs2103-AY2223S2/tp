package seedu.address.storage.task.todo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;
import seedu.address.model.tag.TodoType;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.NoteContent;

/**
 * Jackson-friendly version of {@link InternshipApplication}.
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
     * Constructs a {@code JsonAdaptedInternshipApplication} with the given InternshipApplication details.
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
     * Converts a given {@code InternshipApplication} into this class for Jackson use.
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
     * Converts this Jackson-friendly adapted InternshipApplication object
     * into the model's {@code InternshipApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted InternshipApplication.
     */
    public InternshipTodo toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(title)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelTitle = new CompanyName(title);

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date created"));
        }
        final LocalDate modelDate = LocalDate.parse(date);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TodoType.class.getSimpleName()));
        }
        if (!TodoType.isValidTodo(type)) {
            throw new IllegalValueException(String.format(TodoType.MESSAGE_CONSTRAINTS, type));
        }
        final TodoType modelType = TodoType.TODO;

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationDeadline.class.getSimpleName()));
        }
        if (!ApplicationDeadline.isValidDate(LocalDate.parse(deadline))) {
            throw new IllegalValueException(String.format(ApplicationDeadline.MESSAGE_CONSTRAINTS, deadline));
        }
        final ApplicationDeadline modelDeadline = new ApplicationDeadline(LocalDate.parse(deadline));

        if (note != null) {
            if (!NoteContent.isValidContent(note)) {
                throw new IllegalValueException(NoteContent.MESSAGE_CONSTRAINTS);
            }
            final NoteContent modelContent = new NoteContent(note);

            return new InternshipTodo(
                    modelTitle, modelJobTitle, modelDeadline, modelContent, modelDate, modelType);
        }
        return new InternshipTodo(
                modelTitle, modelJobTitle, modelDeadline, modelDate, modelType);
    }
}
