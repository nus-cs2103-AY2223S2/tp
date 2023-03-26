package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_ELDERLY;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * Edits the details of an existing volunteer in FriendlyLink.
 */
public class EditVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "edit_volunteer";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();

    static {
        COMMAND_PROMPTS.put(PREFIX_NAME, "<name>");
        COMMAND_PROMPTS.put(PREFIX_NRIC_VOLUNTEER, "<nric>");
        COMMAND_PROMPTS.put(PREFIX_ADDRESS, "<address>");
        COMMAND_PROMPTS.put(PREFIX_PHONE, "<phone>");
        COMMAND_PROMPTS.put(PREFIX_EMAIL, "<email>");
        COMMAND_PROMPTS.put(PREFIX_TAG, "<tag>");
        COMMAND_PROMPTS.put(PREFIX_REGION, "<region>");
        COMMAND_PROMPTS.put(PREFIX_BIRTH_DATE, "<birth_date>");
        COMMAND_PROMPTS.put(PREFIX_AVAILABILITY, "<start_date,end_date>");
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the volunteer identified "
            + "by the index number used in the displayed volunteer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC + "NRIC] "
            + "[" + PREFIX_BIRTH_DATE + "BIRTH DATE] "
            + "[" + PREFIX_REGION + "REGION] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_MEDICAL_TAG + "MEDICAL_TAG]... "
            + "[" + PREFIX_AVAILABILITY + "DATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_BIRTH_DATE + "1989-02-01 ";

    public static final String MESSAGE_EDIT_VOLUNTEER_SUCCESS = "Edited volunteer: %1$s";

    private final Index index;
    private final EditDescriptor editDescriptor;

    /**
     * Creates an {@code EditVolunteerCommand} to edit a volunteer.
     *
     * @param index Index of the volunteer in the filtered volunteer list to edit.
     * @param editDescriptor Details to edit the volunteer with.
     */
    public EditVolunteerCommand(Index index, EditDescriptor editDescriptor) {
        requireAllNonNull(index, editDescriptor);
        this.index = index;
        this.editDescriptor = new EditDescriptor(editDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!editDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NO_FIELD_PROVIDED);
        }

        requireNonNull(model);
        List<Volunteer> lastShownList = model.getFilteredVolunteerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
        }
        assert index.getZeroBased() >= 0 : "index should not be negative";

        Volunteer volunteerToEdit = lastShownList.get(index.getZeroBased());
        Volunteer editedVolunteer = EditDescriptor.createEditedVolunteer(
                volunteerToEdit, editDescriptor);

        Nric editedNric = editedVolunteer.getNric();
        if (!volunteerToEdit.isSamePerson(editedVolunteer) && model.hasVolunteer(editedNric)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS);
        }
        if (model.hasElderly(editedNric)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_IN_ELDERLY);
        }

        model.setVolunteer(volunteerToEdit, editedVolunteer);
        model.refreshAllFilteredLists();
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
                && editDescriptor.equals(e.editDescriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, editDescriptor);
    }
}
