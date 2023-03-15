package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ModuleTag;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + Prefix.NAME + "NAME "
            + Prefix.PHONE + "PHONE "
            + Prefix.EMAIL + "EMAIL "
            + Prefix.ADDRESS + "ADDRESS "
            + "[" + Prefix.GROUP_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + Prefix.NAME + "John Doe "
            + Prefix.PHONE + "98765432 "
            + Prefix.EMAIL + "johnd@example.com "
            + Prefix.ADDRESS + "311, Clementi Ave 2, #02-25 "
            + Prefix.GROUP_TAG + "friends "
            + Prefix.GROUP_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person candidatePerson;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        this.candidatePerson = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        this.candidatePerson.setCommonModules(userModuleTags);

        if (model.hasPerson(this.candidatePerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        // No duplicate person inside EduMate, then qualifies for a contact index
        // the only place in the entire code that can set Contact Index.
        IndexHandler indexHandler = new IndexHandler(model);
        this.candidatePerson.setContactIndex(indexHandler.assignIndex());
        model.addPerson(this.candidatePerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.candidatePerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && candidatePerson.equals(((AddCommand) other).candidatePerson));
    }
}
