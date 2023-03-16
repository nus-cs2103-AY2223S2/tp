package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;

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

        String homeworkName = argMultimap.getValue(PREFIX_HOMEWORK).get();
        LocalDateTime deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);

        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            name = name.trim();
            int spaceIndex = name.indexOf(" ");
            if (spaceIndex != -1) {
                name = name.substring(0, spaceIndex);
            }
            nameKeywords.set(i, name);
        }

        return new CreateHomeworkCommand(new NameContainsKeywordsPredicate(nameKeywords),
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
