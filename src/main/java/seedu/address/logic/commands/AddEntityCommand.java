package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;

/**
 * Adds a person to the address book.
 */
public class AddEntityCommand extends Command {

    public static final String COMMAND_WORD = "make";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entity to the management system. "
        + "Parameters: "
        + PREFIX_CLASSIFICATION + "CLASSIFICATION "
        + PREFIX_NAME + "NAME"
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_CLASSIFICATION + "char "
        + PREFIX_NAME + "Leeroy Jenkins"
        + "[" + PREFIX_TAG + "Orc]...";
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Entity toAdd;


    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddEntityCommand(Entity entity) {
        requireNonNull(entity);
        toAdd = entity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddEntityCommand // instanceof handles nulls
            && toAdd.equals(((AddEntityCommand) other).toAdd));
    }
}
