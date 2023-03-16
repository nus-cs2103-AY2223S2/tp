package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.EditVolunteerDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;

/**
 * Edits the details of an existing volunteer in FriendlyLink.
 */
public class EditVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "edit_volunteer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the volunteer identified "
            + "by the index number used in the displayed volunteer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC_VOLUNTEER + "NRIC] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_REGION + "REGION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_AGE + "33 ";

    public static final String MESSAGE_EDIT_VOLUNTEER_SUCCESS = "Edited volunteer: %1$s";

    private final Index index;
    private final EditVolunteerDescriptor editVolunteerDescriptor;

    /**
     * @param index of the volunteer in the filtered volunteer list to edit
     * @param editVolunteerDescriptor details to edit the volunteer with
     */
    public EditVolunteerCommand(Index index, EditVolunteerDescriptor editVolunteerDescriptor) {
        requireNonNull(index);
        requireNonNull(editVolunteerDescriptor);

        this.index = index;
        this.editVolunteerDescriptor = new EditVolunteerDescriptor(editVolunteerDescriptor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommandResult execute(Model model) throws CommandException {
        if (!editVolunteerDescriptor.isAnyFieldEdited()) {
            throw new CommandException(Messages.MESSAGE_NOT_EDITED);
        }

        requireNonNull(model);
        List<Volunteer> lastShownList = model.getFilteredVolunteerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
        }

        Volunteer volunteerToEdit = lastShownList.get(index.getZeroBased());
        Volunteer editedVolunteer = EditVolunteerDescriptor.createEditedVolunteer(
                volunteerToEdit, editVolunteerDescriptor);

        if (!volunteerToEdit.isSamePerson(editedVolunteer) && model.hasVolunteer(editedVolunteer)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_VOLUNTEER);
        }

        model.setVolunteer(volunteerToEdit, editedVolunteer);
        model.updateFilteredVolunteerList((Predicate<Volunteer>) PREDICATE_SHOW_ALL);
        return new CommandResult(String.format(MESSAGE_EDIT_VOLUNTEER_SUCCESS, editedVolunteer));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVolunteerCommand)) {
            return false;
        }

        // state check
        EditVolunteerCommand e = (EditVolunteerCommand) other;
        return index.equals(e.index)
                && editVolunteerDescriptor.equals(e.editVolunteerDescriptor);
    }
}
