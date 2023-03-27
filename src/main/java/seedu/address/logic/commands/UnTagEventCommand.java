package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TO_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TO_TAG;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Name;

/**
 * Untags a Person to an Event
 */
public class UnTagEventCommand extends Command {
    public static final String COMMAND_WORD = "untagpersonevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Untags a Person to an Event. "
            + "Parameters: "
            + PREFIX_EVENT_TO_TAG + "INDEX OF EVENT TO TAG "
            + PREFIX_PERSON_TO_TAG + "INDEX OF PERSON TO TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_TO_TAG + "1 "
            + PREFIX_PERSON_TO_TAG + "John Doe";
    private static final String MESSAGE_SUCCESS = "%1$s is now untagged to Event: %2$s";
    private static final String MESSAGE_SUCCESS_2 = "%1$s is already untagged to Event: %2$s";
    private static final String MESSAGE_INVALID_EVENT = "This event doesn't exists in the Calendar!";
    private static final String MESSAGE_INVALID_PERSON = "This Person doesn't exists in the Calendar!";

    private final Index eventIndex;
    private final Name personName;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public UnTagEventCommand(Index eventIndex, Name personName) {
        requireAllNonNull(eventIndex, personName);

        this.eventIndex = eventIndex;
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithName(personName.toString())) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        List<Event> lastShownList = model.getEvents();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT);
        }

        Person taggingPerson = model.getPersonWithName(personName.toString());

        if (!model.isPersonTaggedToEvent(eventIndex, taggingPerson)) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_2, taggingPerson, eventIndex));
        }

        model.untagPersonFromEvent(eventIndex, taggingPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, taggingPerson, eventIndex));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnTagEventCommand // instanceof handles nulls
                && eventIndex.equals(((UnTagEventCommand) other).eventIndex)
                && personName.equals(((UnTagEventCommand) other).personName)); // state check
    }
}
