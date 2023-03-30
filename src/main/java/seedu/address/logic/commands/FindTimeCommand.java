package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Finds free time slot within a group of people within the week from the indicated date.
 */
public class FindTimeCommand extends Command {

    public static final String COMMAND_WORD = "free";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all free time slots within a week shared by all members in the group "
            + "(identified by the index number used in the displayed group list)"
            + " and displays them in a timetable format."
            + " The start date can also be specified and time slots generated would be within a week from that date.\n"
            + "Parameters: " + "INDEX (must be a positive integer)" + " [" + PREFIX_STARTDATETIME + "START DATE]\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + COMMAND_WORD + " 2 " + PREFIX_STARTDATETIME + "09/03/2023 00:00\n";

    // TODO: Consider using another prefix for date instead of dateTime
    public static final String MESSAGE_SUCCESS = "Timetable generated! It may take some time for the table to display.";

    private final Index index;

    private final LocalDate startDate;

    /**
     * Creates a FindTimeCommand to find free time slots within a group
     */
    public FindTimeCommand(Index index, LocalDate date) {
        requireNonNull(index);
        this.index = index;
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
        List<Group> lastShownGroups = model.getFilteredGroupList();

        if (index.getZeroBased() >= lastShownGroups.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group targetGroup = lastShownGroups.get(index.getZeroBased());
        model.updateFilteredTimeSlotList(targetGroup, startDate);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
