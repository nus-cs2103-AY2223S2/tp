package seedu.address.logic.commands;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;

/**
 * Deletes an applicant from a listing identified using it's displayed index from the listing book.
 */
public class DeleteApplicantCommand extends Command {
    public static final String COMMAND_WORD = "del_app";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an applicant from a listing identified by "
            + "the index number used in the displayed listing book.\n"
            + "Parameters: INDEX (must be a positive integer within the range of the number of listings shown) "
            + PREFIX_APPLICANT_WITH_ID + "APPLICANT\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_APPLICANT_WITH_ID + "John Doe\n"
            + "*If there are duplicated names, specify the id by adding the 4-digit unique identifier after the name.\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_APPLICANT_WITH_ID + "John Doe#2103\n";

    public static final String MESSAGE_SUCCESS = "Applicant: %1$s has been deleted from %2$s!";
    public static final String MESSAGE_APPLICANT_NOT_FOUND = "Applicant %1$s cannot be found in %2$s.";
    public static final String MESSAGE_AMBIGUOUS_APPLICANT = "There are multiple applicants with the name %1s in %2$s, "
            + "\nspecify the 4-digit "
            + "unique identifier after the name.\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_APPLICANT_WITH_ID + "John Doe#2103\n";

    private final Index targetIndex;
    private final String targetApplicantId;

    /**
     * Creates a DeleteApplicantCommand to remove an applicant from a listing.
     * @param targetIndex index of the listing to delete the applicant from
     * @param targetApplicantId id of the applicant to be deleted
     */
    public DeleteApplicantCommand(Index targetIndex, String targetApplicantId) {
        requireNonNull(targetIndex);
        requireNonNull(targetApplicantId);

        this.targetIndex = targetIndex;
        this.targetApplicantId = targetApplicantId;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getDisplayedListingBook();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToDeleteApplicantFrom = lastShownList.get(targetIndex.getZeroBased());

        Optional<Applicant> applicantToDelete = getApplicantToDeleteObject(listingToDeleteApplicantFrom);

        if (applicantToDelete.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_APPLICANT_NOT_FOUND, targetApplicantId,
                    listingToDeleteApplicantFrom.getTitle()));
        }

        Listing editedListing = createEditedListingWithoutApplicant(listingToDeleteApplicantFrom,
                applicantToDelete.get());

        model.setListing(listingToDeleteApplicantFrom, editedListing);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetApplicantId,
                listingToDeleteApplicantFrom.getTitle()));

    }

    private Optional<Applicant> getApplicantToDeleteObject(Listing listing) throws CommandException {
        ArrayList<Applicant> applicants = listing.getApplicants();
        Optional<Applicant> applicantToDelete;
        if (targetApplicantId.length() > 5
                && targetApplicantId.charAt(targetApplicantId.length() - 5) == '#') {
            String targetName = targetApplicantId.substring(0, targetApplicantId.length() - 5);
            int targetHashCode = parseInt(targetApplicantId.substring(targetApplicantId.length() - 4));
            applicantToDelete = applicants.stream().filter(applicant -> applicant.getName().fullName.equals(targetName)
                    && applicant.hashCode() == targetHashCode).findFirst();
        } else {
            List<Applicant> applicantsWithSameName = applicants.stream().filter(
                    applicant -> applicant.getName().fullName.equals(targetApplicantId)).collect(Collectors.toList());
            if (applicantsWithSameName.size() > 1) {
                throw new CommandException(String.format(MESSAGE_AMBIGUOUS_APPLICANT, targetApplicantId,
                        listing.getTitle()));
            }

            applicantToDelete = applicantsWithSameName.stream().findFirst();
        }
        return applicantToDelete;
    }

    /**
     * Create an identical listing with applicant removed.
     * @param listing previous listing for reference
     * @param applicantToDelete applicant to delete
     * @return
     */
    private Listing createEditedListingWithoutApplicant(Listing listing, Applicant applicantToDelete) {
        ArrayList<Applicant> finalApplicants = new ArrayList<>();
        listing.getApplicants().forEach(applicant -> {
            if (applicant != applicantToDelete) {
                finalApplicants.add(applicant);
            }
        });

        Listing editedListing = new Listing(listing.getTitle(),
                listing.getDescription(), finalApplicants, listing.getPlatforms());

        return editedListing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteApplicantCommand)) {
            return false;
        }

        DeleteApplicantCommand that = (DeleteApplicantCommand) o;

        if (!targetIndex.equals(that.targetIndex)) {
            return false;
        }
        return targetApplicantId.equals(that.targetApplicantId);
    }

}
