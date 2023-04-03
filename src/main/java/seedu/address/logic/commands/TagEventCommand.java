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
import seedu.address.ui.result.ResultDisplay;

/**
 * Tags a Person to an Event
 */
public class TagEventCommand extends Command {
    public static final String COMMAND_WORD = "tagpersonevent";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Tags a contact to an event.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    PREFIX_EVENT_TO_TAG + "EVENT_INDEX",
                    PREFIX_PERSON_TO_TAG + "CONTACT_NAME")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE,
                    COMMAND_WORD,
                    PREFIX_EVENT_TO_TAG + "1",
                    PREFIX_PERSON_TO_TAG + "John Doe")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "Note that the given contact name must be exact.");
    private static final String MESSAGE_SUCCESS = "%1$s is now tagged to the event indexed as %2$s";
    private static final String MESSAGE_SUCCESS_2 = "%1$s is already tagged to the event indexed as %2$s";
    private static final String MESSAGE_INVALID_EVENT = "This event doesn't exist!";
    private static final String MESSAGE_INVALID_PERSON = "This contact doesn't exist!";

    private final Index eventIndex;
    private final Name personName;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public TagEventCommand(Index eventIndex, Name personName) {
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

        if (model.isPersonTaggedToEvent(eventIndex, taggingPerson)) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_2, this.personName, eventIndex.getOneBased()));
        }

        model.tagPersonToEvent(eventIndex, taggingPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.personName, eventIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagEventCommand // instanceof handles nulls
                && eventIndex.equals(((TagEventCommand) other).eventIndex)
                && personName.equals(((TagEventCommand) other).personName)); // state check
    }
}
