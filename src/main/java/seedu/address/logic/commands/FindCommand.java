package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.util.SampleDataUtil.SAMPLE_PERSON;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.ui.result.ResultDisplay;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Finds all contacts matching any of the specified keywords (case-insensitive)",
                    "in the specified fields, and displays them in the list.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    PREFIX_NAME.toString("NAME", true, true),
                    PREFIX_PHONE.toString("PHONE", true, true),
                    PREFIX_EMAIL.toString("EMAIL", true, true),
                    PREFIX_ADDRESS.toString("ADDRESS", true, true),
                    PREFIX_GENDER.toString("GENDER", true, true),
                    PREFIX_RACE.toString("RACE", true, true),
                    PREFIX_COMMS.toString("COMMUNICATION_CHANNEL", true, true),
                    PREFIX_MAJOR.toString("MAJOR", true, true),
                    PREFIX_FACULTY.toString("FACULTY", true, true),
                    PREFIX_MODULES.toString("MODULE", true, true),
                    PREFIX_TAG.toString("TAG", true, true))
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD,
                    PREFIX_NAME.toString(SAMPLE_PERSON.getName().toString()),
                    PREFIX_MAJOR.toString(SAMPLE_PERSON.getMajor().toString()))
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "At least one field should be specified.",
                    "The indices of the contacts in the resulting list may be different.",
                    "For more information on prefixes, refer to the user guide using the help command.");

    private final PersonContainsKeywordsPredicate predicate;

    public FindCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
