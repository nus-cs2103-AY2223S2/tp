package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddInterviewDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InterviewDate;

/**
 * Parses input arguments and creates a new AddInterviewDateCommand object
 */
public class AddInterviewDateCommandParser implements Parser<AddInterviewDateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewDateCommand
     * and returns an AddInterviewDateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInterviewDateCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInterviewDateCommand.MESSAGE_USAGE));
        }

        InterviewDate interviewDate = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_DATE).get());

        return new AddInterviewDateCommand(index, interviewDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
