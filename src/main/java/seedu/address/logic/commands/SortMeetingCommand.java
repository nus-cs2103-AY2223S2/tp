package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Creates an SortMeetingCommand to get {@code ModelManage} class to sort with a specified attributes {@code Title},
 * {@code DateTime}, {@code Location}, {@code Description}
 */
public class SortMeetingCommand extends Command {

    public static final String COMMAND_WORD = "sortm";
    public static final String MESSAGE_USAGE = "sort the meeting list by tags. sort by \n"
            + " datetime, location, description, title \n"
            + "like: \n"
            + "sortm dt/ \n"
            + "if you want to sort in reverse add an r after the tag"
            + "\n like this: sortm dt/r";
    private static Prefix sortByPrefix;
    private static String prefix;
    private static boolean isReverse;
    private static Comparator<Meeting> titleComparator = Comparator.comparing(m -> m.getTitle().toString());
    private static Comparator<Meeting> descriptorComparator = Comparator.comparing(m -> m.getDescription().toString());
    private static Comparator<Meeting> locationComparator = Comparator.comparing(m -> m.getLocation().toString());
    private static Comparator<Meeting> dateTimeComparator = Comparator.comparing(m -> m.getDateTime().get());
    private static final String MESSAGE_SUCCESS = "Sorted by %1$s";
    /**
     * Creates an SortMeetingCommand to get {@code ModelManage} class to sort with a specified attributes {@code Title},
     * {@code DateTime}, {@code Location}, {@code Description}
     */
    public SortMeetingCommand(Prefix sortByPrefix, boolean isReverse) {
        this.sortByPrefix = sortByPrefix;
        this.isReverse = isReverse;
    }
    /**
     * executes the sort command, by the prefix given pass the correct comparator
     * {@code DateTime}, {@code Location}, {@code Description}, {@code Title}
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        prefix = sortByPrefix.toString();
        switch (sortByPrefix.toString()) {
        case "m/":
            if (isReverse) {
                model.sortFilteredMeetingList(titleComparator.reversed());
            } else {
                model.sortFilteredMeetingList(titleComparator);
            }
            break;
        case "des/":
            if (isReverse) {
                model.sortFilteredMeetingList(descriptorComparator.reversed());
            } else {
                model.sortFilteredMeetingList(descriptorComparator);
            }
            break;
        case "l/":
            if (isReverse) {
                model.sortFilteredMeetingList(locationComparator.reversed());
            } else {
                model.sortFilteredMeetingList(locationComparator);
            }
            break;
        case "dt/":
            if (isReverse) {
                model.sortFilteredMeetingList(dateTimeComparator.reversed());
            } else {
                model.sortFilteredMeetingList(dateTimeComparator);
            }
            break;
        default:
            throw new CommandException("Something went wrong");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortByPrefix.toString()));
    }
}
