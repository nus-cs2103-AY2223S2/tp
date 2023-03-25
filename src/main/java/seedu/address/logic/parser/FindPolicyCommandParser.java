package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import java.util.Arrays;
import seedu.address.logic.commands.FindPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindPolicyCommandParser implements Parser<FindPolicyCommand> {

    @Override
    public FindPolicyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPolicyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPolicyCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
