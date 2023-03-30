package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.FieldsContainKeywordsPredicate;

/**
 * Finds and lists all persons in ConnectUS whose fields contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers."
            + "Parameters: KEYWORD [MORE KEYWORDS]..."
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SOCMED_INSTAGRAM + "INSTAGRAM] "
            + "[" + PREFIX_SOCMED_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_SOCMED_WHATSAPP + "WHATSAPP] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_MODULE + "MODULE]... "
            + "[" + PREFIX_CCA + "CCA]... "
            + "[" + PREFIX_CCA_POSITION + "CCA_POSITION]... "
            + "[" + PREFIX_REMARK + "REMARK]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS" + " "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_NO_KEYWORDS = "At least one keyword must be provided.";

    private final FieldsContainKeywordsPredicate predicate;

    public SearchCommand(FieldsContainKeywordsPredicate predicate) {
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
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
