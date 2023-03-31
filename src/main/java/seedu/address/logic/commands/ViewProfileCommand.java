package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Prints student profiles with or without name-matching
 */
public class ViewProfileCommand extends Command {
    public static final String COMMAND_WORD = "view-profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Prints the profile of the student that matches "
            + "the specified name.\n"
            + "Parameters: \n"
            + PREFIX_NAME + "STUDENT_NAME \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe";
    private static final Predicate<Student> SHOW_ALL_STUDENTS = student -> true;
    private static final String SEPERATOR = "--------------------------------------------------\n";

    private final List<String> names;
    private final Predicate<Student> namePredicate;

    /**
     * public constructor for a ViewProfileCommand
     * @param names
     * @param namePredicate
     */
    public ViewProfileCommand(List<String> names, Predicate<Student> namePredicate) {
        this.names = names;
        this.namePredicate = namePredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        StringBuilder nonExistNames = new StringBuilder();
        for (String name : names) {
            if (model.noSuchStudent(name)) {
                nonExistNames.append(name).append(", ");
            }
        }
        if (nonExistNames.length() != 0) {
            nonExistNames = new StringBuilder(nonExistNames.substring(0, nonExistNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, nonExistNames));
        }
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
        }
        if (dupNames.length() != 0) {
            dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
        }
        model.updateFilteredStudentList(namePredicate);

        List<Student> studentList = model.getFilteredStudentList();

        int numberOfStudents = studentList.size();
        StringBuilder sb = new StringBuilder();
        sb.append(SEPERATOR);

        // Loop through each student and add their lesson to the string builder
        for (Student student : studentList) {
            sb.append(student.getName().fullName).append(":\n");
            sb.append("Phone: ").append(student.getPhone()).append("\n");
            sb.append("Address: ").append(student.getAddress().toString()).append("\n");
            sb.append("Email: ").append(student.getEmail()).append("\n");
            sb.append("Tags: ");
            for (Tag tag : student.getTags()) {
                sb.append(tag);
            }
            sb.append("\n");

            sb.append(SEPERATOR);
        }

        return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, numberOfStudents, sb));
    }
}
