package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;

/**
 * Adds a person to the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete|d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an entity from Reroll.";

    public static final String MESSAGE_SUCCESS = "Existing entity deleted: %1$s";

    private final Name entityName;

    private final Classification classification;

    /**
     * Creates a KillCommand to delete the specified {@code Entity}
     */
    public DeleteCommand(Name name, Classification classification) {
        requireAllNonNull(name, classification);
        this.entityName = name;
        this.classification = classification;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Entity> listByClassification = model.getListByClassification(classification);
        Entity toDelete = listByClassification.stream()
                .filter(entity -> entity.getName().equals(entityName))
                .findFirst()
                .orElseThrow(() -> new CommandException("No such entity found!"));
        requireNonNull(toDelete);
        model.deleteEntity(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && entityName.equals(((DeleteCommand) other).entityName)
                && classification.equals(((DeleteCommand) other).classification));
    }
}
