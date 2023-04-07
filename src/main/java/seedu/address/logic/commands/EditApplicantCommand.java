package seedu.address.logic.commands;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
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
 * Edits a listing in the listing book.
 */
public class EditApplicantCommand extends Command {

    public static final String COMMAND_WORD = "edit_app";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an applicant's details for a listing. "
            + "Ordered by the index number of the listing used in the displayed listing book.\n"
            + "      Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer within the range of the number of listings shown) "
            + PREFIX_APPLICANT_WITH_ID + "OLD_APPLICANT "
            + PREFIX_APPLICANT + "NEW_APPLICANT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPLICANT_WITH_ID + "John "
            + PREFIX_APPLICANT + "Sam";

    public static final String MESSAGE_SUCCESS = "Applicant: %1$s has been edited to %2$s in %3$s";
    public static final String MESSAGE_APPLICANT_NOT_FOUND = "Applicant %1$s cannot be found in %2$s.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This listing already contains the provided applicant";
    public static final String MESSAGE_AMBIGUOUS_APPLICANT = "There are multiple applicants with the name %1s in %2$s, "
            + "\nspecify the 4-digit "
            + "unique identifier after the name.\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPLICANT_WITH_ID + "John Doe#2103 "
            + PREFIX_APPLICANT + "Sam";
    private final Index targetIndex;
    private final String oldApplicantId;

    private final Applicant newApplicant;

    /**
     * Creates an EditCommand to edit the specified {@code Listing}
     */
    public EditApplicantCommand(Index targetIndex, String oldApplicantId, Applicant newApplicant) {
        requireNonNull(targetIndex);
        requireNonNull(oldApplicantId);
        requireNonNull(newApplicant);

        this.targetIndex = targetIndex;
        this.oldApplicantId = oldApplicantId;
        this.newApplicant = newApplicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getDisplayedListingBook();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToEditApplicantFrom = lastShownList.get(targetIndex.getZeroBased());

        Optional<Applicant> oldApplicant = getOldApplicantObject(listingToEditApplicantFrom);

        if (listingToEditApplicantFrom.getApplicants().stream().filter(
                        applicant -> applicant.equals(newApplicant) && applicant.hashCode() == newApplicant.hashCode())
                .count() > 0) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        if (oldApplicant.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_APPLICANT_NOT_FOUND, oldApplicantId,
                    listingToEditApplicantFrom.getTitle()));
        }

        Listing editedListing = createEditedListing(listingToEditApplicantFrom,
                oldApplicant.get(), newApplicant);

        model.setListing(listingToEditApplicantFrom, editedListing);

        return new CommandResult(String.format(MESSAGE_SUCCESS, oldApplicantId, newApplicant.getName().fullName,
                listingToEditApplicantFrom.getTitle()));

    }

    private Optional<Applicant> getOldApplicantObject(Listing listing) throws CommandException {
        ArrayList<Applicant> applicants = listing.getApplicants();
        Optional<Applicant> oldApplicant;
        if (oldApplicantId.length() > 5
                && oldApplicantId.charAt(oldApplicantId.length() - 5) == '#') {
            String targetName = oldApplicantId.substring(0, oldApplicantId.length() - 5);
            int targetHashCode = parseInt(oldApplicantId.substring(oldApplicantId.length() - 4));
            oldApplicant = applicants.stream().filter(applicant -> applicant.getName().fullName.equals(targetName)
                    && applicant.hashCode() == targetHashCode).findFirst();
        } else {
            List<Applicant> applicantsWithSameName = applicants.stream().filter(
                    applicant -> applicant.getName().fullName.equals(oldApplicantId)).collect(Collectors.toList());
            if (applicantsWithSameName.size() > 1) {
                throw new CommandException(String.format(MESSAGE_AMBIGUOUS_APPLICANT, oldApplicantId,
                        listing.getTitle()));
            }

            oldApplicant = applicantsWithSameName.stream().findFirst();
        }
        return oldApplicant;
    }

    private Listing createEditedListing(Listing listing, Applicant oldApplicant, Applicant newApplicant) {
        ArrayList<Applicant> finalApplicants = new ArrayList<>();
        listing.getApplicants().forEach(applicant -> {
            if (applicant != oldApplicant) {
                finalApplicants.add(applicant);
            } else {
                finalApplicants.add(newApplicant);
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
        if (!(o instanceof EditApplicantCommand)) {
            return false;
        }

        EditApplicantCommand that = (EditApplicantCommand) o;

        if (!targetIndex.equals(that.targetIndex)) {
            return false;
        }
        return oldApplicantId.equals(that.oldApplicantId) && newApplicant.equals(that.newApplicant);
    }
}
