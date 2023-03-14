package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Teacher;
import seedu.address.model.person.Person;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;


/**
 * Adds the name of a teacher into the module tracker
 */
public class TeacherCommand extends Command {

    public static final String COMMAND_WORD = "teacher";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the teacher of the module identified "
            + "by the index number. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "s/ [TEACHER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "s/ Prof Damyth \n"
            + "NOTE: s represents the Japanese term 'sensei'.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Deadline: %2$s";
    public static final String MESSAGE_ADD_TEACHER_SUCCESS = "Added teacher to Module: %1$s";
    public static final String MESSAGE_DELETE_TEACHER_SUCCESS = "Removed teacher from Module: %1$s";

    private final Index index;
    private final Teacher teacher;

    /**
     * Constructor for the teacher command.
     * @param index The index of the person to edit the teacher.
     * @param teacher The name of the teacher to be added.
     */
    public TeacherCommand(Index index, Teacher teacher) {
        requireAllNonNull(index, teacher);
        this.index = index;
        this.teacher = teacher;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getType(), personToEdit.getTimeSlot(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getRemark(),
                personToEdit.getDeadline(), teacher);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the teacher is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !teacher.value.isEmpty() ? MESSAGE_ADD_TEACHER_SUCCESS : MESSAGE_DELETE_TEACHER_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        // state check
        TeacherCommand e = (TeacherCommand) other;
        return index.equals(e.index)
                && teacher.equals(e.teacher);
    }
}
