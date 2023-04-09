package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;

/**
 * Adds an entity to Reroll.
 */
public class MakeCommand extends Command {

    public static final String COMMAND_WORD = "make|m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entity to the management system. ";

    public static final String MESSAGE_SUCCESS = "New entity added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTITY = "This entity already exists in Reroll";


    private final Entity toAdd;


    /**
     * Creates an AddCommand to add the specified {@code Entity}
     */
    public MakeCommand(Entity entity) {
        requireNonNull(entity);
        toAdd = entity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasEntity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTITY);
        }
        model.addEntity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MakeCommand // instanceof handles nulls
            && toAdd.equals(((MakeCommand) other).toAdd));
    }
}
