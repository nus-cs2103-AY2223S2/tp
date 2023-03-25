package tfifteenfour.clipboard.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_TAG;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Adds a student to the roster.
 */
public class AddStudentCommand extends AddCommand {
    public static final String COMMAND_TYPE_WORD = "student";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_STUDENTID + "STUDENTID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_STUDENTID + "A1234567X ";

    public static final String MESSAGE_SUCCESS = "New student added in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in this group";

    private final Student studentToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        studentToAdd = student;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to add student");
        }

        Group targetGroup = currentSelection.getSelectedGroup();
        if (targetGroup.hasStudent(studentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        targetGroup.addStudent(studentToAdd);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, targetGroup, studentToAdd), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && studentToAdd.equals(((AddStudentCommand) other).studentToAdd));
    }
}
