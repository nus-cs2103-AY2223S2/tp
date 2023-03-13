package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReviewCommand;
import seedu.address.logic.commands.ReviewTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsExactKeywordsPredicate;
import seedu.address.model.task.SubjectContainsExactKeywordsPredicate;

/**
 * Parses input arguments and creates a new ReviewCommand object
 */
public class ReviewCommandParser implements Parser<ReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReviewCommand
     * and returns an ReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReviewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewTaskCommand.MESSAGE_USAGE));
        }

        String[] subjectKeywords = trimmedArgs.split("\\s+");

        return new ReviewCommand(new NameContainsExactKeywordsPredicate((Arrays.asList(subjectKeywords))));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
