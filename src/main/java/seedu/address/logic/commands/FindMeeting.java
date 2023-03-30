package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.*;


import java.time.LocalDateTime;

import java.util.function.Predicate;


public class FindMeeting extends Command{
    public static final String COMMAND_WORD = "meeting find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings with "
            + "the specified date (case-insensitive) or person index (optional) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 12/02/2023";

    private final LocalDateTime meetingStart;

    public FindMeeting(LocalDateTime meetingStart) {
        requireNonNull(meetingStart);
        this.meetingStart = meetingStart;
    }

    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.updateFilteredMeetingList((Predicate<MeetingWithPerson>) new MeetingStartDatePredicate(meetingStart));
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeeting // instanceof handles nulls
                && meetingStart.equals(((FindMeeting) other).meetingStart)); // state check
    }
}