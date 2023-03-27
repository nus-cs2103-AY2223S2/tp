package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.commons.util.CollectionUtil;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Edits the details of an existing client in Mycelium.
 */
public class UpdateClientCommand extends Command {
    public static final String COMMAND_ACRONYM = "uc";

    public static final String MESSAGE_USAGE = COMMAND_ACRONYM + ": Edits a client in Mycelium.\n"

            + "Compulsory Arguments: "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "CLIENT EMAIL\n"
            + "Options (At least one): "
            + CliSyntax.PREFIX_CLIENT_NEW_EMAIL + "NEW CLIENT EMAIL\n"
            + CliSyntax.PREFIX_SOURCE + "CLIENT SOURCE "
            + CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER + "MOBILE NUMBER "
            + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "YEAR OF BIRTH\n"

            + "Example: " + COMMAND_ACRONYM + " "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "alice_baker@bakers.com "
            + CliSyntax.PREFIX_CLIENT_NEW_EMAIL + "patrick_bateman@pandp.com"
            + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "2001 "
            + CliSyntax.PREFIX_SOURCE + "PierceAndPierce "
            + CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER + "21255563";

    public static final String MESSAGE_SUCCESS = "Client edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    // To be checked
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book.";

    public final Email email;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * Creates an UpdateClientCommand to edit the specified {@code Client}.
     * @param email of the client in the filtered client list to edit.
     * @param editClientDescriptor details to edit the client with.
     */
    public UpdateClientCommand(Email email, EditClientDescriptor editClientDescriptor) {
        requireNonNull(email);
        requireNonNull(editClientDescriptor);

        this.email = email;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * @param clientToEdit the client to edit.
     * @param editClientDescriptor the details to edit the client with.
     * @return a client with the details of {@code clientToEdit} edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;
        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Optional<YearOfBirth> updatedYearOfBirth = editClientDescriptor.getYearOfBirth()
                .or(clientToEdit::getYearOfBirth);
        Optional<NonEmptyString> updatedSource = editClientDescriptor.getSource().or(clientToEdit::getSource);
        Optional<Phone> updatedMobileNumber = editClientDescriptor.getMobileNumber().or(clientToEdit::getMobileNumber);
        return new Client(updatedName, updatedEmail, updatedYearOfBirth, updatedSource, updatedMobileNumber);
    }

    /**
     * Executes the UpdateClientCommand and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Client> uniqueClient = model.getUniqueClient(c -> c.getEmail().equals(email));

        if (!uniqueClient.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT);
        }
        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Client editedClient = createEditedClient(uniqueClient.get(), editClientDescriptor);

        if (model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(uniqueClient.get(), editedClient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedClient));
    }

    /**
     * Returns true if both commands have the same email and editClientDescriptor.
     * @param other object to be compared.
     * @return true if both commands have the same email and editClientDescriptor.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateClientCommand)) {
            return false;
        }

        // state check
        UpdateClientCommand e = (UpdateClientCommand) other;
        return email.equals(e.email)
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Returns a hashcode for the command.
     * @return a hashcode for the command.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, editClientDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditClientDescriptor {
        private Optional<Name> name;
        private Optional<Email> email;
        private Optional<YearOfBirth> yearOfBirth;
        private Optional<NonEmptyString> source;
        private Optional<Phone> mobileNumber;

        /**
         * Creates an EditClientDescriptor with all fields optionally null.
         */
        public EditClientDescriptor() {
            name = Optional.empty();
            email = Optional.empty();
            yearOfBirth = Optional.empty();
            source = Optional.empty();
            mobileNumber = Optional.empty();
        }

        /**
         * Creates an EditClientDescriptor with all fields optionally null.
         *
         * @param toCopy the EditClientDescriptor to copy.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setYearOfBirth(toCopy.yearOfBirth);
            setSource(toCopy.source);
            setMobileNumber(toCopy.mobileNumber);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, email, yearOfBirth, source, mobileNumber);
        }

        /**
         * Returns an Optional containing the name.
         *
         * @return an Optional containing the name.
         */
        public Optional<Name> getName() {
            return name;
        }

        /**
         * Sets the name to the given name. This assumes name given could be null.
         *
         * @param name the name to set.
         */
        public void setName(Name name) {
            this.name = Optional.ofNullable(name);
        }

        /**
         * Sets the name to the given name. This ensures the name variable is wrapped is an Optional.
         *
         * @param name the name to set.
         */
        public void setName(Optional<Name> name) {
            requireNonNull(name);
            this.name = name;
        }

        /**
         * Returns an Optional containing the email.
         *
         * @return an Optional containing the email.
         */
        public Optional<Email> getEmail() {
            return email;
        }

        /**
         * Sets the email to the given email. This assumes email given could be null.
         *
         * @param email the email to set.
         */
        public void setEmail(Email email) {
            this.email = Optional.ofNullable(email);
        }

        /**
         * Sets the email to the given email. This ensures the email variable is wrapped is an Optional.
         *
         * @param email the email to set.
         */
        public void setEmail(Optional<Email> email) {
            requireNonNull(email);
            this.email = email;
        }

        /**
         * Returns an Optional containing the year of birth.
         *
         * @return an Optional containing the year of birth.
         */
        public Optional<YearOfBirth> getYearOfBirth() {
            return yearOfBirth;
        }

        /**
         * Sets the year of birth to the given year of birth. This assumes year of birth given could be null.
         *
         * @param yearOfBirth the year of birth to set.
         */
        public void setYearOfBirth(YearOfBirth yearOfBirth) {
            this.yearOfBirth = Optional.ofNullable(yearOfBirth);
        }

        /**
         * Sets the year of birth to the given year of birth.
         * This ensures the year of birth variable is wrapped is an Optional.
         *
         * @param yearOfBirth the year of birth to set.
         */
        public void setYearOfBirth(Optional<YearOfBirth> yearOfBirth) {
            requireNonNull(yearOfBirth);
            this.yearOfBirth = yearOfBirth;
        }

        /**
         * Returns an Optional containing the source.
         *
         * @return an Optional containing the source.
         */
        public Optional<NonEmptyString> getSource() {
            return source;
        }

        /**
         * Sets the source to the given source. This assumes source given could be null.
         *
         * @param source the source to set.
         */
        public void setSource(NonEmptyString source) {
            this.source = Optional.ofNullable(source);
        }

        /**
         * Sets the source to the given source. This ensures the source variable is wrapped is an Optional.
         *
         * @param source the source to set.
         */
        public void setSource(Optional<NonEmptyString> source) {
            requireNonNull(source);
            this.source = source;
        }

        /**
         * Returns an Optional containing the mobile number.
         *
         * @return an Optional containing the mobile number.
         */
        public Optional<Phone> getMobileNumber() {
            return mobileNumber;
        }

        /**
         * Sets the mobile number to the given mobile number. This assumes mobile number given could be null.
         *
         * @param mobileNumber the mobile number to set.
         */
        public void setMobileNumber(Phone mobileNumber) {
            this.mobileNumber = Optional.ofNullable(mobileNumber);
        }

        /**
         * Sets the mobile number to the given mobile number.
         * This ensures the mobile number variable is wrapped is an Optional.
         *
         * @param mobileNumber the mobile number to set.
         */
        public void setMobileNumber(Optional<Phone> mobileNumber) {
            requireNonNull(mobileNumber);
            this.mobileNumber = mobileNumber;
        }

        /**
         * Returns true if both EditClientDescriptor objects have the same name. The {@code instanceOf} check is used to
         * handle nulls.
         *
         * @param other the other EditClientDescriptor object.
         * @return true if both EditClientDescriptor objects have the same name.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getYearOfBirth().equals(e.getYearOfBirth())
                    && getSource().equals(e.getSource())
                    && getMobileNumber().equals(e.getMobileNumber());
        }

        /**
         * Returns the hashcode of the EditClientDescriptor object.
         *
         * @return the hashcode of the EditClientDescriptor object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(name, email, yearOfBirth, source, mobileNumber);
        }
    }
}
