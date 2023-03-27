package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entity.Entity;

/**
 * Adds a person to the address book.
 */
public class KillCommand extends Command {

    public static final String COMMAND_WORD = "kill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an entity from Reroll.";

    public static final String MESSAGE_SUCCESS = "Existing entity deleted: %1$s";


    private final String entityName;

    private final String classification;


    /**
     * Creates a KillCommand to delete the specified {@code Entity}
     */
    public KillCommand(String name, String classification) {
        requireAllNonNull(name, classification);
        this.entityName = name;
        this.classification = classification;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entity> ListByClassification = model.listCharacters();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof KillCommand // instanceof handles nulls
                && toDelete.equals(((KillCommand) other).toDelete));
    }
}