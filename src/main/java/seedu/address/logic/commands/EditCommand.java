package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UiSwitchMode;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;

/**
 * Enters edit mode to edit the details of a given entity.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit|e";

    public static final String MESSAGE_SUCCESS = "Entered Edit Mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the entity identified by classification and name.\n"
            + "Parameters: CLASSIFICATION (char, mob or item) NAME (name of entity)\n"
            + "Example: " + "edit item short dagger";

    private final String toEditName;
    private final String toEditClassification;

    /**
     * Creates an EditCommand to enter Edit Mode for the specified {@code Entity}
     */
    public EditCommand(String toEditClassification, String toEditName) {
        this.toEditClassification = toEditClassification;
        this.toEditName = toEditName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Entity> entities;
        switch (toEditClassification) {
        case "c":
        case "char":
            entities = model.getReroll().getEntities().getCharList();
            break;
        case "m":
        case "mob":
            entities = model.getReroll().getEntities().getMobList();
            break;
        case "i":
        case "item":
            entities = model.getReroll().getEntities().getItemList();
            break;
        default:
            throw new CommandException("Invalid Classification!");
        }
        for (Entity entity :entities) {
            if (entity.getName().fullName.equals(toEditName)) {
                model.setCurrentSelectedEntity(entity);
                return new CommandResult(MESSAGE_SUCCESS, false, false, UiSwitchMode.VIEW);
            }
        }

        throw new CommandException(String.format("No such entity in %s!", toEditClassification));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && toEditClassification.equals(((EditCommand) other).toEditClassification)
                && toEditName.equals(((EditCommand) other).toEditName));
    }
}

