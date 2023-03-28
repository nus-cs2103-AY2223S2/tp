package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.experimental.model.Model;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.util.ComposedPredicate;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Requested Entities have been cleared!";
    private final boolean useSelected;
    private final Classification classification;

    /**
     * @param useSelected    to use the selected list or the entire entity list
     * @param classification type of classification to remove
     */
    public ClearCommand(boolean useSelected, Classification classification) {
        this.useSelected = useSelected;
        this.classification = classification;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<? super Entity> result;
        if (useSelected) {
            result = model.getCurrentPredicate();
        } else {
            result = model.PREDICATE_SHOW_ALL_ENTITIES;
        }
        result = new ComposedPredicate(result, model.getClassificationPredicate(classification));
        List<Entity> toDelete = model.getSnapshotEntities(result);
        model.deleteEntities(toDelete);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
