package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.careflow.logic.commands.patientcommands.FindCommand;
import seedu.careflow.logic.parser.Parser;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    @Override
    public FindCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            seedu.careflow.logic.commands.patientcommands.FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new seedu.careflow.logic.commands.patientcommands.FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
