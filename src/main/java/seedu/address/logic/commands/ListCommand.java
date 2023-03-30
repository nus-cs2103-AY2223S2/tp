package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entity.Classification;

/**
 * Lists all entities in the address book to the user according to the given classification.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list|l";

    public static final String MESSAGE_SUCCESS = "Listed all entities";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the entities of a provided classification";

    public static final String MESSAGE_INVALID_ENTITY_TYPE = "This entity type is invalid.";

    private final Classification classification;
    public ListCommand(Classification classification) {
        this.classification = classification;
    }

    public ListCommand() {
        classification = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (classification == null) {
            model.resetFilteredEntityList();
        } else if (classification.isCharacter()) {
            model.listCharacters();
        } else if (classification.isItem()) {
            model.listItems();
        } else if (classification.isMob()) {
            model.listMobs();
        } else {
            throw new CommandException(MESSAGE_INVALID_ENTITY_TYPE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
