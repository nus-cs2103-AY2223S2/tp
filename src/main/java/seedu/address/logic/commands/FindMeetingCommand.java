package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MeetingStartDatePredicate;
import seedu.address.model.person.MeetingWithPersonPredicate;
import seedu.address.model.person.Person;

/**
 * Finds meetings with matching start date
 */
public class FindMeetingCommand extends Command {
    public static final String COMMAND_WORD = "meetingFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings with "

            + "the specified date (case-insensitive) or person index and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "23-03-2023"
            + "Example: " + COMMAND_WORD + "5";


    private LocalDate meetingStart;
    private Index personIndex;
    /**
     * Finds meeting with specified start date
     * @param meetingStart start date
     */
    public FindMeetingCommand(LocalDate meetingStart) {
        requireNonNull(meetingStart);
        this.meetingStart = meetingStart;
    }

    /**
     * Finds all meeting with the given person
     * @param personIndex index of person in the last shown list
     */
    public FindMeetingCommand(Index personIndex) {
        requireNonNull(personIndex);
        this.personIndex = personIndex;
    }

    /**
     * Executes meetingFind command
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult that stores the success message upon successful execution of meetingFind
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (meetingStart != null) {
            model.updateFilteredMeetingList(new MeetingStartDatePredicate(meetingStart));
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
        }
        List<Person> lastShownList = model.getFilteredPersonList();
        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(personIndex.getZeroBased());
        model.updateFilteredMeetingList(new MeetingWithPersonPredicate(person));
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindMeetingCommand // instanceof handles nulls
            && meetingStart.isEqual(((FindMeetingCommand) other).meetingStart)); // state check
    }
}


