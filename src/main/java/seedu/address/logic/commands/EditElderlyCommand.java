package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;

/**
 * Edits the details of an existing elderly in FriendlyLink.
 */
public class EditElderlyCommand extends Command {

    public static final String COMMAND_WORD = "edit_elderly";

    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();

    static {
        COMMAND_PROMPTS.put(PREFIX_NAME, "<name>");
        COMMAND_PROMPTS.put(PREFIX_NRIC_ELDERLY, "<nric>");
        COMMAND_PROMPTS.put(PREFIX_ADDRESS, "<address>");
        COMMAND_PROMPTS.put(PREFIX_PHONE, "<phone>");
        COMMAND_PROMPTS.put(PREFIX_EMAIL, "<email>");
        COMMAND_PROMPTS.put(PREFIX_TAG, "<tag>");
        COMMAND_PROMPTS.put(PREFIX_REGION, "<region>");
        COMMAND_PROMPTS.put(PREFIX_AGE, "<age>");
        COMMAND_PROMPTS.put(PREFIX_RISK, "<risk>");
        COMMAND_PROMPTS.put(PREFIX_AVAILABILITY, "<start_date,end_date>");
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the elderly identified "
            + "by the index number used in the displayed elderly list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC_ELDERLY + "NRIC] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_REGION + "REGION] "
            + "[" + PREFIX_RISK + "RISK] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_AVAILABILITY + "START_DATE,END_DATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_AGE + "73 ";

    public static final String MESSAGE_EDIT_ELDERLY_SUCCESS = "Edited Elderly: %1$s";

    private final Index index;
    private final EditElderlyDescriptor editElderlyDescriptor;

    /**
     * @param index of the elderly in the filtered elderly list to edit
     * @param editElderlyDescriptor details to edit the elderly with
     */
    public EditElderlyCommand(Index index, EditElderlyDescriptor editElderlyDescriptor) {
        requireNonNull(index);
        requireNonNull(editElderlyDescriptor);

        this.index = index;
        this.editElderlyDescriptor = new EditElderlyDescriptor(editElderlyDescriptor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommandResult execute(Model model) throws CommandException {
        if (!editElderlyDescriptor.isAnyFieldEdited()) {
            throw new CommandException(Messages.MESSAGE_NOT_EDITED);
        }

        requireNonNull(model);
        List<Elderly> lastShownList = model.getFilteredElderlyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ELDERLY_DISPLAYED_INDEX);
        }

        Elderly elderlyToEdit = lastShownList.get(index.getZeroBased());
        Elderly editedElderly = EditElderlyDescriptor.createEditedElderly(elderlyToEdit, editElderlyDescriptor);

        if (!elderlyToEdit.isSamePerson(editedElderly) && model.hasElderly(editedElderly)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_ELDERLY);
        }

        model.setElderly(elderlyToEdit, editedElderly);
        model.updateFilteredElderlyList((Predicate<Elderly>) PREDICATE_SHOW_ALL);
        return new CommandResult(String.format(MESSAGE_EDIT_ELDERLY_SUCCESS, editedElderly));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditElderlyCommand)) {
            return false;
        }

        // state check
        EditElderlyCommand e = (EditElderlyCommand) other;
        return index.equals(e.index)
                && editElderlyDescriptor.equals(e.editElderlyDescriptor);
    }
}
