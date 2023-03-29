package seedu.roles.logic.parser;

import static seedu.roles.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.roles.logic.commands.CompanyCommand;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.model.job.CompanyContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CompanyCommand object
 */
public class CompanyCommandParser implements Parser<CompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompanyCommand
     * and returns a CompanyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompanyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompanyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new CompanyCommand(new CompanyContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
