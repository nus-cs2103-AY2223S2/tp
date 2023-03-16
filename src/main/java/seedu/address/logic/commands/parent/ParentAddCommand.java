package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.parent.Parent;

/**
 * A class for "parent add" command"
 */
public class ParentAddCommand extends ParentCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "parent " + COMMAND_WORD
            + ": Adds a parent to the address book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONEPARENT + "PHONE "
            + "["
            + PREFIX_PARENTAGE + "AGE "
            + PREFIX_IMAGEPARENT + "IMAGE PARENT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "]\n"
            + "Example: \n" + "parent " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567 "
            + PREFIX_PARENTAGE + "30 "
            + PREFIX_IMAGEPARENT + "C:// "
            + PREFIX_EMAIL + "tanahcow@gmail.com "
            + PREFIX_ADDRESS + "Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456)";

    public static final String MESSAGE_SUCCESS = "New parent added:";

    private final Parent parent;

    /**
     * Creates an AddCommand to add the specified {@code Parent}
     */
    public ParentAddCommand(Parent parent) {
        requireNonNull(parent);
        this.parent = parent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasParent(parent)) {
            throw new DuplicatePersonException();
        }

        model.addParent(parent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, parent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentAddCommand // instanceof handles nulls
                && parent.equals(((ParentAddCommand) other).parent));
    }
}
