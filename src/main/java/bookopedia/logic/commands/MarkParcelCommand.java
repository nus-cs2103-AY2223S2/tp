package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PARCEL_DISPLAYED_INDEX;
import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static bookopedia.commons.core.Messages.MESSAGE_NO_PARCELS;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.logic.parser.CliSyntax;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.ParcelStatus;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Person;

/**
 * Marks a parcel from a delivery with status.
 */
public class MarkParcelCommand extends Command {
    public static final String COMMAND_WORD = "mark_pc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a parcel from a delivery as fragile or/and bulky. "
            + "\nParameters: INDEX of Delivery (a positive integer) "
            + CliSyntax.PREFIX_PARCEL + "INDEX of Parcel (a positive integer within list) "
            + CliSyntax.PREFIX_STATUS + "STATUS (fragile/bulky)";
    public static final String MESSAGE_RETURN_STATUS_MARK_PC = "Cannot mark parcel in a return delivery!";
    public static final String MESSAGE_DONE_STATUS_MARK_PC = "Cannot mark parcel in a done delivery!";
    public static final String MESSAGE_SUCCESS = "Marked %1$s's parcel %2$s as %3$s";

    private final Index targetIndex;
    private final Index parcelIndex;
    private final ParcelStatus newStatus;

    /**
     * Creates mark command.
     * @param targetIndex the index of the Person to modify
     * @param parcelIndex the index of the Parcel to modify
     * @param newStatus the new status for the Parcel
     */
    public MarkParcelCommand(Index targetIndex, Index parcelIndex, ParcelStatus newStatus) {
        requireNonNull(targetIndex);
        requireNonNull(parcelIndex);
        this.targetIndex = targetIndex;
        this.parcelIndex = parcelIndex;
        this.newStatus = newStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person targetPerson = lastShownList.get(targetIndex.getZeroBased());

        if (targetPerson.getDeliveryStatus() == DeliveryStatus.RETURN) {
            throw new CommandException(MESSAGE_RETURN_STATUS_MARK_PC);
        } else if (targetPerson.getDeliveryStatus() == DeliveryStatus.DONE) {
            throw new CommandException(MESSAGE_DONE_STATUS_MARK_PC);
        }

        // No parcels
        if (targetPerson.getParcels().size() == 0) {
            throw new CommandException(String.format(MESSAGE_NO_PARCELS,
                    targetPerson.getName()));
        }

        // Parcel index given by user exceed list size
        if (parcelIndex.getZeroBased() >= targetPerson.getParcels().size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_PARCEL_DISPLAYED_INDEX,
                    targetPerson.getParcels().size()));
        }

        final Set<Parcel> updatedParcels = new HashSet<>();
        final String[] targetParcelName = {""};
        AtomicInteger count = new AtomicInteger();

        targetPerson.getParcels().stream()
                .sorted(Comparator.comparing(parcel -> parcel.parcelName))
                .forEach(parcel -> {
                    if (count.getAndIncrement() == parcelIndex.getZeroBased()) {
                        targetParcelName[0] = parcel.toString();
                        if (newStatus == ParcelStatus.FRAGILE) {
                            parcel.setFragile();
                        }
                        if (newStatus == ParcelStatus.BULKY) {
                            parcel.setBulky();
                        }
                    }
                    updatedParcels.add(parcel);
                });

        Person updatedPerson = new Person(targetPerson.getName(), targetPerson.getPhone(),
                targetPerson.getEmail(), targetPerson.getAddress(), updatedParcels,
                targetPerson.getDeliveryStatus(), targetPerson.getNoOfDeliveryAttempts());

        model.setPerson(targetPerson, updatedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                updatedPerson.getName(), targetParcelName[0], newStatus.toString()),
                true, updatedPerson, model.getIndexOf(updatedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkParcelCommand)) {
            return false;
        }

        // state check
        MarkParcelCommand e = (MarkParcelCommand) other;

        return targetIndex.equals(e.targetIndex)
                && parcelIndex.equals(e.parcelIndex)
                && newStatus.equals(e.newStatus);
    }
}
