package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_SET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_EVENT_SET + "EVENT INDEX]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_EVENT_SET + "1 "
            + PREFIX_EVENT_SET + "2 ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;
    private final Set<Index> eventIndexList;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person, Set<Index> eventIndexList) {
        requireNonNull(person);
        this.toAdd = person;
        this.eventIndexList = eventIndexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        if (!eventIndexList.isEmpty()) {
            Set<Event> eventSet = new HashSet<>();
            List<Event> lastShownList = model.getFilteredEventList();
            for (Index eventIndex: eventIndexList) {
                if (eventIndex.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
                }
                Event eventToAdd = lastShownList.get(eventIndex.getZeroBased());
                if (!eventSet.contains(eventToAdd)) {
                    eventSet.add(eventToAdd);
                }
            }
            Person personWithEvent = new Person(toAdd.getName(), toAdd.getPhone(), toAdd.getEmail(),
                    toAdd.getAddress(), eventSet);
            model.addPerson(personWithEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS, personWithEvent));
        } else {
            model.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
