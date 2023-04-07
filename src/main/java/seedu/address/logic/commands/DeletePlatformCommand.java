package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.platform.Platform;

/**
 * Deletes a platform from a listing identified using it's displayed index from the listing book.
 */
public class DeletePlatformCommand extends Command {
    public static final String COMMAND_WORD = "del_plat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a platform from a listing identified by "
            + "the index number used in the displayed listing book.\n"
            + "Parameters: INDEX (must be a positive integer within the range of the number of listings shown) "
            + PREFIX_PLATFORM + "PLATFORM\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_PLATFORM + "LinkedIn\n";

    public static final String MESSAGE_SUCCESS = "Platform: %1$s has been deleted from %2$s!";
    public static final String MESSAGE_PLATFORM_NOT_FOUND = "Platform %1$s cannot be found in %2$s.";
    private final Index targetIndex;
    private final String targetPlatform;

    /**
     * Creates a DeletePlatformCommand to remove a platform from a listing.
     * @param targetIndex index of the listing to delete the platform from
     * @param targetPlatform name of the platform to be deleted
     */
    public DeletePlatformCommand(Index targetIndex, String targetPlatform) {
        requireNonNull(targetIndex);
        requireNonNull(targetPlatform);

        this.targetIndex = targetIndex;
        this.targetPlatform = targetPlatform;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getDisplayedListingBook();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToDeletePlatformFrom = lastShownList.get(targetIndex.getZeroBased());

        Optional<Platform> platformToDelete = getPlatformToDeleteObject(listingToDeletePlatformFrom);

        if (platformToDelete.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PLATFORM_NOT_FOUND, targetPlatform,
                    listingToDeletePlatformFrom.getTitle()));
        }

        Listing editedListing = createEditedListingWithoutPlatform(listingToDeletePlatformFrom,
                platformToDelete.get());

        model.setListing(listingToDeletePlatformFrom, editedListing);

        return new CommandResult(String.format(MESSAGE_SUCCESS, platformToDelete.get(),
                listingToDeletePlatformFrom.getTitle()));

    }

    private Optional<Platform> getPlatformToDeleteObject(Listing listing) throws CommandException {
        ArrayList<Platform> platforms = listing.getPlatforms();
        Optional<Platform> platformToDelete;
        List<Platform> platformsFound = platforms.stream().filter(
                platform -> platform.getPlatformName().fullPlatformName.equals(targetPlatform))
                        .collect(Collectors.toList());
        platformToDelete = platformsFound.stream().findFirst();

        return platformToDelete;
    }

    /**
     * Create an identical listing with platform removed.
     * @param listing previous listing for reference
     * @param platformToDelete platform to delete
     * @return
     */
    private Listing createEditedListingWithoutPlatform(Listing listing, Platform platformToDelete) {
        ArrayList<Platform> finalPlatforms = new ArrayList<>();
        listing.getPlatforms().forEach(platform -> {
            if (platform != platformToDelete) {
                finalPlatforms.add(platform);
            }
        });

        Listing editedListing = new Listing(listing.getTitle(),
                listing.getDescription(), listing.getApplicants(), finalPlatforms);

        return editedListing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeletePlatformCommand)) {
            return false;
        }

        DeletePlatformCommand that = (DeletePlatformCommand) o;

        if (!targetIndex.equals(that.targetIndex)) {
            return false;
        }
        return targetPlatform.equals(that.targetPlatform);
    }
}
