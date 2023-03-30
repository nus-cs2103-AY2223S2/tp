package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Edits the details of an existing client in Mycelium.
 */
public class UpdateClientCommand extends Command {
    public static final String COMMAND_ACRONYM = "uc";

    public static final String MESSAGE_USAGE = COMMAND_ACRONYM + ": Edits a client in Mycelium.\n"

        + "Compulsory Arguments: "
        + CliSyntax.PREFIX_CLIENT_EMAIL + "CLIENT EMAIL\n"
        + "Optional Arguments: "
        + CliSyntax.PREFIX_CLIENT_NEW_EMAIL + "NEW CLIENT EMAIL "
        + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "YEAR OF BIRTH "
        + CliSyntax.PREFIX_SOURCE + "CLIENT SOURCE "
        + CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER + "MOBILE NUMBER\n"

        + "Example: " + COMMAND_ACRONYM + " "
        + CliSyntax.PREFIX_CLIENT_EMAIL + "topg@gmail.com "
        + CliSyntax.PREFIX_CLIENT_NEW_EMAIL + "patrick_bateman@pandp.com"
        + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "2000";

    public static final String MESSAGE_SUCCESS = "Updated Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Client not updated.";
    // To be checked
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists.";

    public final Email email;
    private final UpdateClientDescriptor updateClientDescriptor;

    /**
     * Creates an UpdateClientCommand to edit the specified {@code Client}.
     *
     * @param email                  of the client in the filtered client list to edit.
     * @param updateClientDescriptor details to edit the client with.
     */
    public UpdateClientCommand(Email email, UpdateClientDescriptor updateClientDescriptor) {
        requireNonNull(email);
        requireNonNull(updateClientDescriptor);

        this.email = email;
        this.updateClientDescriptor = updateClientDescriptor;
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     *
     * @param clientToEdit           client to be edited.
     * @param updateClientDescriptor details to edit the client with.
     * @return a client with the details of {@code clientToEdit}.
     */
    private static Optional<Client> createUpdatedClient(Client clientToEdit,
                                                        UpdateClientDescriptor updateClientDescriptor) {
        assert clientToEdit != null;
        if (!updateClientDescriptor.isAnyFieldEdited()) {
            return Optional.empty();
        }
        Name updatedName = updateClientDescriptor.getName().orElse(clientToEdit.getName());
        Email updatedEmail = updateClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Optional<YearOfBirth> updatedYearOfBirth = updateClientDescriptor.getYearOfBirth()
            .or(clientToEdit::getYearOfBirth);
        Optional<NonEmptyString> updatedSource = updateClientDescriptor.getSource().or(clientToEdit::getSource);
        Optional<Phone> updatedMobileNumber = updateClientDescriptor
            .getMobileNumber().or(clientToEdit::getMobileNumber);
        Client client = new Client(updatedName, updatedEmail, updatedYearOfBirth, updatedSource, updatedMobileNumber);
        if (client.equals(clientToEdit)) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    /**
     * Executes the UpdateClientCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TabSwitchAction action = new TabSwitchAction(TabSwitchAction.TabSwitch.CLIENT);
        Optional<Client> uniqueClient = model.getUniqueClient(c -> c.getEmail().equals(email));

        if (!uniqueClient.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT, action);
        }
        Optional<Client> updatedClient = createUpdatedClient(uniqueClient.get(), updateClientDescriptor);
        if (updatedClient.isEmpty()) {
            throw new CommandException(MESSAGE_NOT_EDITED, action);
        }
        // Ensures that new email is not a mandatory option
        if (updateClientDescriptor.email.isPresent() && model.hasClient(updatedClient.get())) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT, action);
        }
        model.setClient(uniqueClient.get(), updatedClient.get());
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedClient.get()), action);
    }

    /**
     * Returns true if both commands have the same email and editClientDescriptor.
     *
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
            && updateClientDescriptor.equals(e.updateClientDescriptor);
    }

    /**
     * Returns a hashcode for the command.
     *
     * @return a hashcode for the command.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, updateClientDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UpdateClientDescriptor {
        private Optional<Name> name;
        private Optional<Email> email;
        private Optional<YearOfBirth> yearOfBirth;
        private Optional<NonEmptyString> source;
        private Optional<Phone> mobileNumber;

        /**
         * Creates an EditClientDescriptor with all fields optionally null.
         */
        public UpdateClientDescriptor() {
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
        public UpdateClientDescriptor(UpdateClientDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setYearOfBirth(toCopy.yearOfBirth);
            setSource(toCopy.source);
            setMobileNumber(toCopy.mobileNumber);
        }

        /**
         * Creates an EditClientDescriptor with all fields optionally null.
         *
         * @param toCopy the Client to copy.
         */
        public UpdateClientDescriptor(Client toCopy) {
            setName(toCopy.getName());
            setEmail(toCopy.getEmail());
            setYearOfBirth(toCopy.getYearOfBirth());
            setSource(toCopy.getSource());
            setMobileNumber(toCopy.getMobileNumber());
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return Stream.of(name, email, yearOfBirth, source, mobileNumber)
                .anyMatch(Optional::isPresent);
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
            if (!(other instanceof UpdateClientDescriptor)) {
                return false;
            }

            // state check
            UpdateClientDescriptor e = (UpdateClientDescriptor) other;

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
