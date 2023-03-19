package seedu.address.logic.commands;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.exceptions.AmbiguousApplicantException;
import seedu.address.model.listing.exceptions.ApplicantNotFoundException;

/**
 * Deletes an applicant from a listing identified using it's displayed index from the listing book.
 */
public class DeleteApplicantCommand extends Command {
    public static final String COMMAND_WORD = "delete_applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an applicant from a listing identified by "
            + "the index number used in the displayed listing book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Applicant: %1$s has been from %2$s!";

    private final Index targetIndex;
    private final String targetApplicantName;

    /**
     * Creates a DeleteApplicantCommand to remove a applicant from a listing.
     * @param targetIndex index of the listing to delete the applicant from
     * @param targetApplicantName name of the applicant to be deleted
     */
    public DeleteApplicantCommand(Index targetIndex, String targetApplicantName) {
        this.targetIndex = targetIndex;
        this.targetApplicantName = targetApplicantName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getFilteredListingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToDeleteApplicantFrom = lastShownList.get(targetIndex.getZeroBased());
        ArrayList<Applicant> applicants = listingToDeleteApplicantFrom.getApplicants();

        Optional<Applicant> applicantToDelete = null;
        if (targetApplicantName.substring(-5).matches("#\\d{4}")) {
            String targetName = targetApplicantName.substring(0, -5);
            int targetHashCode = parseInt(targetApplicantName.substring(-4));
            applicantToDelete = applicants.stream().filter(applicant -> applicant.getName().fullName == targetName
                    && applicant.hashCode() == targetHashCode).findFirst();
        } else {
            Stream<Applicant> applicantsWithSameName = applicants.stream().filter(
                    applicant -> applicant.getName().fullName == targetApplicantName);
            if (applicantsWithSameName.count() > 1) {
                throw new AmbiguousApplicantException();
            }
            applicantToDelete = applicantsWithSameName.findFirst();
        }

        if (applicantToDelete.isEmpty()) {
            throw new ApplicantNotFoundException();
        }

        listingToDeleteApplicantFrom.deleteApplicant(applicantToDelete.get());

        return new CommandResult(String.format(MESSAGE_SUCCESS, applicantToDelete.get(), listingToDeleteApplicantFrom));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicantCommand) other).targetIndex)); // state check
    }
}
