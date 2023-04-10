package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;

/**
 * Adds a listing to the listing book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a listing to the listing book.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_APPLICANT + "APPLICANT]..."
            + "[" + PREFIX_PLATFORM + "PLATFORM]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Cool job title "
            + PREFIX_DESCRIPTION + "Informative job description "
            + PREFIX_APPLICANT + "John "
            + PREFIX_APPLICANT + "Sam"
            + PREFIX_PLATFORM + "Linkedin";

    public static final String MESSAGE_SUCCESS = "Added new listing:%1$s";
    public static final String MESSAGE_DUPLICATE_LISTING = "This listing already exists in the listing book";

    private final Listing listingToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Listing}
     */
    public AddCommand(Listing listingToAdd) {
        requireNonNull(listingToAdd);
        this.listingToAdd = listingToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasListing(listingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LISTING);
        }

        model.addListing(listingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, listingToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && listingToAdd.equals(((AddCommand) other).listingToAdd));
    }
}
