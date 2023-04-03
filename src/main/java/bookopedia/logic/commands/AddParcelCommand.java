package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_EXISTING_PARCEL;
import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.logic.parser.CliSyntax;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Person;

/**
 * Adds parcel to a person.
 */
public class AddParcelCommand extends Command {
    public static final String COMMAND_WORD = "add_pc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds parcel to an existing person. "
            + "Parameters: INDEX (must be a positive integer) "
            + CliSyntax.PREFIX_PARCEL + "PARCEL (must be alphanumeric) ";
    public static final String MESSAGE_RETURN_STATUS_ADD_PC = "Cannot add parcel to a return delivery!";
    public static final String MESSAGE_DONE_STATUS_ADD_PC = "Cannot add parcel to a done delivery!";
    public static final String MESSAGE_SUCCESS = "Added %1$s to %2$s's delivery";

    private final Index targetIndex;
    private final Parcel newParcel;

    /**
     * Creates addParcel command.
     * @param targetIndex the index of the Person to modify
     * @param newParcel the new parcels to be added for the Person
     */
    public AddParcelCommand(Index targetIndex, Parcel newParcel) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newParcel = newParcel;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddParcel = lastShownList.get(targetIndex.getZeroBased());

        if (personToAddParcel.getDeliveryStatus() == DeliveryStatus.RETURN) {
            throw new CommandException(MESSAGE_RETURN_STATUS_ADD_PC);
        } else if (personToAddParcel.getDeliveryStatus() == DeliveryStatus.DONE) {
            throw new CommandException(MESSAGE_DONE_STATUS_ADD_PC);
        }

        final Set<Parcel> updatedParcels = new HashSet<>();
        updatedParcels.addAll(personToAddParcel.getParcels());

        int initialSize = updatedParcels.size();
        updatedParcels.add(newParcel);

        if (initialSize == updatedParcels.size()) {
            throw new CommandException(String.format(MESSAGE_EXISTING_PARCEL, newParcel.toString()));
        }

        Person updatedPerson = new Person(personToAddParcel.getName(), personToAddParcel.getPhone(),
                personToAddParcel.getEmail(), personToAddParcel.getAddress(), updatedParcels,
                personToAddParcel.getDeliveryStatus(), personToAddParcel.getNoOfDeliveryAttempts());

        model.setPerson(personToAddParcel, updatedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newParcel.toString(), personToAddParcel.getName()),
                true, updatedPerson, model.getIndexOf(updatedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // state check
        AddParcelCommand e = (AddParcelCommand) other;

        return targetIndex.equals(e.targetIndex) && newParcel.equals(e.newParcel);
    }
}
