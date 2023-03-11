package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.parent.Parent;

/**
 * A class for "parent Class Name add" command"
 */
public class ParentAddCommand extends ParentCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "parent CLASS_NAME (of student) " + COMMAND_WORD
            + ": Adds a parent to the address book. "
            + "Parameters: "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER (of student) "
            + PREFIX_NAME + "NAME "
            + PREFIX_RELATIONSHIP + "RELATIONSHIP "
            + "["
            + PREFIX_AGE + "AGE "
            + PREFIX_IMAGEPARENT + "IMAGE PARENT "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "]\n"
            + "Example: " + "parent 1A " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Niu "
            + PREFIX_RELATIONSHIP + "Father "
            + PREFIX_AGE + "30 "
            + PREFIX_IMAGEPARENT + "C:// "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "tanahcow@gmail.com ";

    public static final String MESSAGE_SUCCESS = "New parent added:";

    private final Parent parent;

    /**
     * Creates an AddCommand to add the specified {@code Person}
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
