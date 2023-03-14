package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CreateHomeworkExam object
 */
public class CreateExamCommandParser implements Parser<CreateExamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateExamCommand
     * and returns a CreateExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateExamCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EXAM, PREFIX_STARTTIME, PREFIX_ENDTIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EXAM, PREFIX_STARTTIME, PREFIX_ENDTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateExamCommand.MESSAGE_USAGE));
        }

        String examDescription = argMultimap.getValue(PREFIX_EXAM).get();
        LocalDateTime startTime = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_STARTTIME).get());
        LocalDateTime endTime = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_ENDTIME).get());
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

        return new CreateExamCommand(new NameContainsKeywordsPredicate(nameKeywords),
                examDescription, startTime, endTime);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
