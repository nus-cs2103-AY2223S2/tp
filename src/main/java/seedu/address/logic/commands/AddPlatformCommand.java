package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.platform.Platform;


/**
 * Adds a platform to a listing identified using it's displayed index from the listing book.
 */
public class AddPlatformCommand extends Command {
    public static final String COMMAND_WORD = "add_plat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a platform to a listing in the listing book.\n"
            + "Parameters: INDEX (must be a positive integer within the range of the number of listings shown) "
            + PREFIX_PLATFORM + "PLATFORM\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PLATFORM + "LinkedIn";


    public static final String MESSAGE_SUCCESS = "Platform %1$s added to listing %2$s";
    public static final String MESSAGE_DUPLICATE_PLATFORM = "This listing already contains the provided platform";

    private final Index targetIndex;
    private final Platform platformToAdd;

    /**
     * Creates an AddPlatformCommand to add the specified {@code Platform} to {@code Listing}
     * @param targetIndex the displayed index of the listing to change
     * @param platformToAdd the platform to be added
     */
    public AddPlatformCommand(Index targetIndex, Platform platformToAdd) {
        requireNonNull(targetIndex);
        requireNonNull(platformToAdd);

        this.targetIndex = targetIndex;
        this.platformToAdd = platformToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Listing> lastShownList = model.getDisplayedListingBook();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToAddPlatformTo = lastShownList.get(targetIndex.getZeroBased());

        if (listingToAddPlatformTo.getPlatforms().stream()
                .anyMatch(platform -> platform.isSamePlatform(platformToAdd))) {
            throw new CommandException(MESSAGE_DUPLICATE_PLATFORM);
        }

        Listing editedListing = createListingWithPlatform(listingToAddPlatformTo, platformToAdd);

        model.setListing(listingToAddPlatformTo, editedListing);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                platformToAdd.getPlatformName().fullPlatformName,
                listingToAddPlatformTo.getTitle().fullTitle));
    }

    private Listing createListingWithPlatform(Listing listingToChange, Platform platformToAdd) {
        ArrayList<Platform> oldPlatforms = listingToChange.getPlatforms();
        ArrayList<Platform> newPlatforms = new ArrayList<>();
        for (Platform platforms : oldPlatforms) {
            newPlatforms.add(platforms);
        }
        newPlatforms.add(platformToAdd);

        return new Listing(listingToChange.getTitle(), listingToChange.getDescription(),
                listingToChange.getApplicants(), newPlatforms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddPlatformCommand)) {
            return false;
        }

        AddPlatformCommand that = (AddPlatformCommand) o;

        if (!targetIndex.equals(that.targetIndex)) {
            return false;
        }
        return platformToAdd.equals(that.platformToAdd);
    }
}
