package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.handleDuplicateName;
import static seedu.address.logic.commands.CommandUtil.handleNonExistName;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Student;

/**
 * Finds and lists all homework in the homework tracker that match the given name and status keywords.
 * Displays a list of homework with the ability to filter by student name and homework status.
 * Keyword matching is case-insensitive.
 */
public class ViewHomeworkCommand extends Command {
    public static final String COMMAND_WORD = "view-homework";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all homework that match the specified "
            + "name and status keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STATUS + "pending";
    private static final String SEPERATOR = "--------------------------------------------------\n";
    private static final String DOT = ". ";
    private static final String LINE_BREAK = "\n";
    private static final String NAME_LABEL = "%s:\n";
    private static final Predicate<Homework> SHOW_ALL_HOMEWORK = homework -> true;
    private final Predicate<Student> namePredicate;
    private final Predicate<Homework> homeworkStatusPredicate;
    private final boolean defaultPredicateFlag;
    private final List<String> names;


    /**
     * Overloaded constructor for ViewHomeworkCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     */
    public ViewHomeworkCommand(List<String> names, Predicate<Student> namePredicate, boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.homeworkStatusPredicate = SHOW_ALL_HOMEWORK;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.names = names;
    }

    /**
     * Overloaded constructor for ViewHomeworkCommand.
     *
     * @param namePredicate Predicate to filter students by name.
     * @param homeworkStatusPredicate Predicate to filter homework by status.
     */
    public ViewHomeworkCommand(List<String> names, Predicate<Student> namePredicate,
                               Predicate<Homework> homeworkStatusPredicate, boolean defaultPredicateFlag) {
        this.namePredicate = namePredicate;
        this.homeworkStatusPredicate = homeworkStatusPredicate;
        this.defaultPredicateFlag = defaultPredicateFlag;
        this.names = names;
    }

    /**
     * Executes the view-homework command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        handleNonExistName(model, names);
        handleDuplicateName(model, names);
        model.updateFilteredStudentList(namePredicate);

        List<Student> studentList = model.getFilteredStudentList();

        int numberOfHomework = 0;
        StringBuilder sb = new StringBuilder();

        for (Student student : studentList) {
            List<Homework> homeworkList = student.getFilteredHomeworkList(homeworkStatusPredicate);
            if (!homeworkList.isEmpty()) {
                sb.append(SEPERATOR);
                sb.append(String.format(NAME_LABEL, student.getName()));
                numberOfHomework += homeworkList.size();
                formatHomeworkList(homeworkList, sb);
            }
        }

        handleNoHomework(numberOfHomework);
        return new CommandResult(
                    String.format(Messages.MESSAGE_ALL_HOMEWORK_LISTED_OVERVIEW, numberOfHomework, sb.toString()));
    }

    /**
     * Formats the homework list into a string.
     *
     * @param homeworkList List of homework to be formatted.
     * @param sb StringBuilder to append the formatted homework list to.
     */
    public void formatHomeworkList(List<Homework> homeworkList, StringBuilder sb) {
        for (int i = 0; i < homeworkList.size(); i++) {
            sb.append(i + 1);
            sb.append(DOT);
            sb.append(homeworkList.get(i).toString());
            sb.append(LINE_BREAK);
        }
    }

    /**
     * Handles the case where no homework is found.
     *
     * @param numberOfHomework Number of homework found.
     * @throws CommandException If no homework is found.
     */
    public void handleNoHomework(int numberOfHomework) throws CommandException {
        // If no homework is found, throw an exception
        if (numberOfHomework == 0) {
            throw new CommandException(Messages.MESSAGE_NO_HOMEWORK_FOUND);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewHomeworkCommand // instanceof handles nulls
                && namePredicate.equals(((ViewHomeworkCommand) other).namePredicate)
                && homeworkStatusPredicate.equals(((ViewHomeworkCommand) other).homeworkStatusPredicate)
                && defaultPredicateFlag == ((ViewHomeworkCommand) other).defaultPredicateFlag); // state check
    }
}
