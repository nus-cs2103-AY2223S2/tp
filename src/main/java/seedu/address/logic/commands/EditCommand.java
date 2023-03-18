package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.experimental.model.ReadOnlyEntities;
import seedu.address.experimental.model.RerollEntities;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UiSwitchMode;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_SUCCESS = "Entered Edit Mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the person identified by the index number used in the displayed person list.\n"
        + "Parameters: CLASSIFICATION (char, mob or item) NAME (name of entity)\n"
        + "Example: " + COMMAND_WORD + " item short dagger";

    private final String toEditName;
    private final String toEditClassification;

    public EditCommand(String toEditClassification, String toEditName) {
        this.toEditClassification = toEditClassification;
        this.toEditName = toEditName;
    }

    @Override
    public CommandResult execute(seedu.address.experimental.model.Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyEntities rerollEntities;
        switch (toEditClassification) {
        case "char":
            rerollEntities = model.getReroll().getCharacters();
            break;
        case "mob":
            rerollEntities = model.getReroll().getMobs();
            break;
        case "item":
            rerollEntities = model.getReroll().getItems();
            break;
        default:
            throw new CommandException("Invalid Classification!");
        }
        for (Object entity : rerollEntities.getEntityList()) {
            if (((Entity) entity).getName().fullName.equals(toEditName)) {
                model.setCurrentSelectedEntity((Entity) entity);
                return new CommandResult(MESSAGE_SUCCESS, false, false, UiSwitchMode.VIEW);
            }
        }

        throw new CommandException(String.format("No such entity in %s!", toEditClassification));
    }
}

