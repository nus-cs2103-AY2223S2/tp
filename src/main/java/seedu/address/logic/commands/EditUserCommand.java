package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import javafx.beans.property.ReadOnlyObjectProperty;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editpersoncommandsparser.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.Tag;
import seedu.address.model.user.User;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "edituser";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    final EditPersonDescriptor editUserDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditUserCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        this.editUserDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyObjectProperty<User> userWrapper = model.getUserData().getUser();

        User user = userWrapper.getValue();
        User editedUser = createEditedUser(user, editUserDescriptor);


        model.setUser(editedUser);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static User createEditedUser(User user, EditPersonDescriptor editPersonDescriptor) {
        assert user != null;

        Name updatedName = editPersonDescriptor.getName().orElse(user.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(user.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(user.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(user.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(user.getGender());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(user.getMajor());
        Modules updatedModules = editPersonDescriptor.getModules().orElse(user.getModules());
        Race updatedRace = editPersonDescriptor.getRace().orElse(user.getRace());
        CommunicationChannel updatedComms = editPersonDescriptor.getComms().orElse(user.getComms());
        Favorite currentFavorite = user.getIsFavorite();

        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(user.getTags());

        return new User(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedGender, updatedMajor, updatedModules, updatedRace, updatedTags, updatedComms, currentFavorite);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return this.editUserDescriptor.equals(e.editPersonDescriptor);
    }

}
