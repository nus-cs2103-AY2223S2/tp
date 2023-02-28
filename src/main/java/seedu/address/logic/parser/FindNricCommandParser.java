package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNricCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NricMatchesKeywordsPredicate;

public class FindNricCommandParser implements Parser<FindNricCommand> {

    public FindNricCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String nricKeywords = trimmedArgs;

        return new FindNricCommand(new NricMatchesKeywordsPredicate(nricKeywords));
    }
}
