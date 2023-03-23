package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.model.meeting.DateTime.DATE_FORMAT;
import static seedu.address.model.meeting.DateTime.TIME_FORMAT;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class SortMeetingCommand extends Command {

    public static Prefix sortByPrefix;
    public static String Prefix;
    public static boolean isReverse;
    public static final String COMMAND_WORD = "sortm";
    public static final String MESSAGE_USAGE = "sort the meeting list by tags. sort by \n" 
            + " datetime, location, description, title \n"
            +"like: \n"
            + "sortm dt/ \n"
            + "if you want to sort in reverse add an r after the tag"
            + "\n like this: sortm dt/r";
    public static final String MESSAGE_SUCCESS = "Sorted by %1$s" ;
    public static Comparator<Meeting> titleComparator = Comparator.comparing(m -> m.getTitle().toString());
    public static Comparator<Meeting> descriptorComparator = Comparator.comparing(m -> m.getDescription().toString());
    public static Comparator<Meeting> locationComparator = Comparator.comparing(m -> m.getLocation().toString());
    public static Comparator<Meeting> dateTimeComparator = Comparator.comparing(m -> m.getDateTime().get());

    /**
     * Creates an SortMeetingCommand to get {@code ModelManage} class to sort with a specified attributes {@code Title},
     * {@code DateTime}, {@code Location}, {@code Description}
     */
    public SortMeetingCommand(Prefix sortByPrefix, boolean isReverse) {
        this.sortByPrefix = sortByPrefix;
        this.isReverse = isReverse;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Prefix = sortByPrefix.toString();
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
        }              
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortByPrefix.toString()));
    }
}
