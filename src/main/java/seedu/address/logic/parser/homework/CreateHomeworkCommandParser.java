package seedu.address.logic.parser.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.checkNotNullNames;
import static seedu.address.logic.parser.ParserUtil.checkUniqueNotNUllDeadline;
import static seedu.address.logic.parser.ParserUtil.checkUniqueNotNUllHomework;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.homework.CreateHomeworkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;

/**
 * Parses input arguments and creates a new CreateHomeworkCommand object
 */
public class CreateHomeworkCommandParser implements Parser<CreateHomeworkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateHomeworkCommand
     * and returns a CreateHomeworkCommand object for execution.
     *
     * @param args the user input to be parsed.
     * @return CreateHomeworkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateHomeworkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_HOMEWORK, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_HOMEWORK, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateHomeworkCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);

        checkNotNullNames(nameKeywords);
        checkUniqueNotNUllHomework(argMultimap);
        checkUniqueNotNUllDeadline(argMultimap);

        String homeworkName = argMultimap.getValue(PREFIX_HOMEWORK).get();
        LocalDateTime deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new ParseException(Messages.MESSAGE_DEADLINE_IN_PAST);
        }

        return new CreateHomeworkCommand(nameKeywords, new NamePredicate(nameKeywords),
                homeworkName, deadline);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     *
     * @param argumentMultimap the argument multimap to check for prefixes.
     * @param prefixes the prefixes to be checked.
     * @return true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
