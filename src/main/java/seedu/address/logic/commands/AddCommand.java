package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Adds a Pet to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a pet to the address book. "
            + "Parameters: "
            + PREFIX_OWNER_NAME + "OWNER_NAME "
            + PREFIX_NAME + "PET_NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OWNER_NAME + "Alice "
            + PREFIX_NAME + "Doggo "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "example@gmail.com"
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "Dog "
            + PREFIX_TAG + "Chihuahua";

    public static final String MESSAGE_SUCCESS = "New pet added: %1$s";
    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the address book";

    private final Pet toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Pet}
     */
    public AddCommand(Pet pet) {
        requireNonNull(pet);
        toAdd = pet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPet(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PET);
        }

        model.addPet(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
