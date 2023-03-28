package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.UniquePersonList;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

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
        List<Person> lastShownList = model.getFilteredPersonList();
        for (int i = 0; i <= lastShownList.size(); i++) {
            if (!lastShownList.get(i).getMeetingsByDate(meetingStart).isEmpty()) {

            }


        }
        Predicate<Person> predicate =

        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindMeeting // instanceof handles nulls
            && predicate.equals(((FindMeeting) other).predicate)); // state check
    }
}

