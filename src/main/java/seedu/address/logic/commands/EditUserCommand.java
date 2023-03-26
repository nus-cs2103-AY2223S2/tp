package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.beans.property.ReadOnlyObjectProperty;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditUserCommand extends EditPersonCommand {

    public static final String COMMAND_WORD = "edituser";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the user. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    private final PersonDescriptor editUserDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditUserCommand(PersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        this.editUserDescriptor = new PersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyObjectProperty<User> userWrapper = model.getUserData().getData();

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
    private static User createEditedUser(User user, PersonDescriptor editPersonDescriptor) {
        assert user != null;
        Person updatedUser = createEditedPerson(user, editPersonDescriptor);
        UniqueEventList currentEvents = user.getEvents();

        return new User(updatedUser, currentEvents);
    }

    public PersonDescriptor getEditUserDescriptor() {
        return this.editUserDescriptor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserCommand)) {
            return false;
        }

        // state check
        EditUserCommand e = (EditUserCommand) other;
        return this.getEditUserDescriptor().equals(e.getEditUserDescriptor());
    }

}
