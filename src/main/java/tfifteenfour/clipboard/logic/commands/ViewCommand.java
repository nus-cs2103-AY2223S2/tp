package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Views a student in the student list.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // refresh list before viewing
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        List<Student> studentList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToView = studentList.get(targetIndex.getZeroBased());
        //model.updateFilteredStudentList(isTargetStudent(studentToView));
        model.updateViewedStudent(studentToView);

        return new CommandResult(generateSuccessMessage(studentToView));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        // state check
        ViewCommand e = (ViewCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    private String generateSuccessMessage(Student student) {
        return String.format("Viewing: %s, (%s)", student.getName(), student.getStudentId());
    }

    private Predicate<Student> isTargetStudent(Student targetStudent) {
        return student -> student.isSameStudent(targetStudent);
    }
}
