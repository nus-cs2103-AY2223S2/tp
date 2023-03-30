package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Copies the email of the student in the index to clipboard
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies the email of the student in the index "
            + "to the user's clipboard";

    public static final String MESSAGE_SUCCESS = "Successfully copied selected student's email to clipboard";

    private final Index index;

    /**
     * Constructor for the copy command
     * @param index of the student to copy the email from
     */
    public CopyCommand(Index index) {
        super(false);
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to copy selected student email");
        }

        Group group = currentSelection.getSelectedGroup();
        ObservableList<Student> studentList = group.getUnmodifiableFilteredStudentList();

        if (index.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else {
            Student selectedStudent = studentList.get(index.getZeroBased());
            copyToClipboard(selectedStudent);
        }

        return new CommandResult(this, MESSAGE_SUCCESS, willModifyState);
    }

    /**
     * Takes in student and copies the email of the student to clipboard
     * @param student of the selected index
     */
    public void copyToClipboard(Student student) {
        String email = student.getEmail().toString();
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(email), null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && index.equals(((CopyCommand) other).index)); // state check
    }
}
