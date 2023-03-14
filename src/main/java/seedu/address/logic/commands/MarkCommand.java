package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Timing;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_EVENT_SUCCESS = "Marked event: %1$s";

    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToMark = lastShownList.get(targetIndex.getZeroBased());
        Event markedEvent = createMarkedEvent(eventToMark);
        model.markEvent(eventToMark, markedEvent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MARK_EVENT_SUCCESS, eventToMark));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToMark}
     * edited with {@code editPersonDescriptor}.
     */
    private static Event createMarkedEvent(Event eventToMark) {
        assert eventToMark != null;

        Name updatedName = eventToMark.getName();
        Rate updatedRate = eventToMark.getRate();
        Address updatedAddress = eventToMark.getAddress();
        Set<Tag> updatedTags = eventToMark.getTags();
        Timing updatedTiming = eventToMark.getTiming();
        Contact updatedContact = eventToMark.getContact();

        Event updatedEvent = new Event(updatedName, updatedRate, updatedAddress, updatedTiming, updatedTags);
        updatedEvent.mark();
        updatedEvent.linkContact(updatedContact);
        return updatedEvent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndex.equals(((MarkCommand) other).targetIndex)); // state check
    }
}
