package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.tag.TaskType;
import seedu.address.model.task.Note;
import seedu.address.model.task.NoteContent;

/**
 * A utility class to help with building Contact objects.
 */
public class NoteBuilder {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final TaskType TASK_TYPE = TaskType.NOTE;
    private static final NoteContent DEFAULT_NOTE = new NoteContent("This is a note!");

    //Optional field
    private LocalDate date;
    private TaskType type;
    private NoteContent note;

    /**
     * Creates a {@code InternshipTodoBuilder} with the default details.
     */
    public NoteBuilder() {
        date = CURRENT_DATE;
        type = TASK_TYPE;
        note = DEFAULT_NOTE;
    }

    /**
     * Initializes the InternshipTodoBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        date = noteToCopy.getDate();
        type = noteToCopy.getType();
        note = noteToCopy.getNote();
    }

    /**
     * Sets the {@code LocalDate} of the {@code InternshipTodo} that we are building.
     */
    public NoteBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     * Sets the {@code NoteContent} of the {@code InternshipTodo} that we are building.
     */
    public NoteBuilder withNote(String note) {
        this.note = new NoteContent(note);
        return this;
    }

    public Note basicBuild() {
        return new Note(DEFAULT_NOTE, CURRENT_DATE, TASK_TYPE);
    }

    public Note build() {
        return new Note(note, date, type);
    }
}
