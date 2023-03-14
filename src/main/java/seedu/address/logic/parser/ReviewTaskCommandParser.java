package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ReviewTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.SubjectContainsExactKeywordsPredicate;

/**
 * Parses input arguments and creates a new ReviewTaskCommand object
 */
public class ReviewTaskCommandParser implements Parser<ReviewTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReviewTaskCommand
     * and returns an ReviewTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReviewTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewTaskCommand.MESSAGE_USAGE));
        }

        String[] subjectKeywords = trimmedArgs.split("\\s+");

        return new ReviewTaskCommand(new SubjectContainsExactKeywordsPredicate(Arrays.asList(subjectKeywords)));

    }

}
