package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Finds free time slot within a group of people within the week from the indicated date.
 */
public class FindTimeCommand extends Command {

    public static final String COMMAND_WORD = "free";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all free time slots within a week shared by all members in the indicated group "
            + " and displays them in a timetable format."
            + " The start date can also be specified and time slots generated would be within a week from that date.\n"
            + "Parameters: " + PREFIX_GROUP + "GROUP NAME" + "[" + PREFIX_STARTDATETIME + "START DATE]\n"
            + "Example: " + COMMAND_WORD  + PREFIX_GROUP + "CS2103\n"
            + COMMAND_WORD  + PREFIX_GROUP + "CS2103 " + PREFIX_STARTDATETIME + "09/03/2023\n";

    // TODO: Consider using another prefix for date instead of dateTime
    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_MISSING_GROUP = "This group does not exist";

    private final Group targetGroup;

    private final LocalDate startDate;

    /**
     * Creates a FindTimeCommand to find free time slots within a group
     */
    public FindTimeCommand(Group group, LocalDate date) {
        requireNonNull(group);
        targetGroup = group;
        startDate = date;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasGroup(targetGroup)) {
            throw new CommandException(MESSAGE_MISSING_GROUP);
        }

        String result = model.get

        // TODO: Show timetable UI instead of text message
        return new CommandResult(result);
    }
}
