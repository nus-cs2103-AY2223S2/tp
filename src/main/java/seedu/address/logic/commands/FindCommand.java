package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.predicates.FullMatchKeywordsPredicate;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(
            PREFIX_NAME.asOptional().asRepeatable(),
            PREFIX_PHONE.asOptional().asRepeatable(),
            PREFIX_EMAIL.asOptional().asRepeatable(),
            PREFIX_ADDRESS.asOptional().asRepeatable(),
            PREFIX_EDUCATION.asOptional().asRepeatable(),
            PREFIX_TELEGRAM.asOptional().asRepeatable(),
            PREFIX_REMARK.asOptional().asRepeatable(),
            PREFIX_TAG.asOptional().asRepeatable(),
            PREFIX_MODULE.asOptional().asRepeatable()
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain ALL of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + ARGUMENT_PREFIXES.stream()
                    .map(Prefix::toString)
                    .collect(Collectors.joining(" "))
            + "\nExample: " + COMMAND_WORD + " n/alice n/tan t/hasSubmitted";

    private final FullMatchKeywordsPredicate predicate;

    public FindCommand(FullMatchKeywordsPredicate predicate) {
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
