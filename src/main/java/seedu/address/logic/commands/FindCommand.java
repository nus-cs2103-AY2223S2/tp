package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameAndPhoneContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords such as name, phone number or both (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: Format 1: KEYWORDS(either NAME or PHONE NUMBER);\n "
            + "Format 2: n/NAME p/PHONE NUMBER(both must referring to the same person)\n"
            + "Example for Format 1: " + COMMAND_WORD + " alice bob charlie OR 12345678\n"
            + "Example for Format 2: " + COMMAND_WORD + "n/alice bob p/12345678";

    private NameContainsKeywordsPredicate namePredicate = null;
    private PhoneContainsKeywordsPredicate phonePredicate = null;
    private NameAndPhoneContainsKeywordsPredicate nameAndPhoneContainsKeywordsPredicate = null;

    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
    }

    public FindCommand(PhoneContainsKeywordsPredicate phonePredicate) {
        this.phonePredicate = phonePredicate;
    }

    public FindCommand(NameAndPhoneContainsKeywordsPredicate nameAndPhoneContainsKeywordsPredicate) {
        this.nameAndPhoneContainsKeywordsPredicate = nameAndPhoneContainsKeywordsPredicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (nameAndPhoneContainsKeywordsPredicate != null) {
            model.updateFilteredPersonList(nameAndPhoneContainsKeywordsPredicate);
        } else if (namePredicate != null) {
            model.updateFilteredPersonList(namePredicate);
        } else {
            model.updateFilteredPersonList(phonePredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindCommand)) {
            return false;
        }
        FindCommand otherCmd = (FindCommand) other;
        boolean sameNamePredicate = (namePredicate == null) ? otherCmd.namePredicate == null
                : namePredicate.equals(((FindCommand) other).namePredicate);
        boolean samePhonePredicate = (phonePredicate == null) ? otherCmd.phonePredicate == null
                : phonePredicate.equals(((FindCommand) other).phonePredicate);
        boolean sameNamePhonePredicate = (nameAndPhoneContainsKeywordsPredicate == null)
                ? otherCmd.nameAndPhoneContainsKeywordsPredicate == null
                : nameAndPhoneContainsKeywordsPredicate
                .equals(((FindCommand) other).nameAndPhoneContainsKeywordsPredicate);
        return sameNamePredicate && samePhonePredicate && sameNamePhonePredicate;
    }
}
