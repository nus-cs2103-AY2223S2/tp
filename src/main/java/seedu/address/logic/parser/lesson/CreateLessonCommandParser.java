package seedu.address.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneEndTime;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneLesson;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneStartTime;
import static seedu.address.logic.parser.ParserUtil.checkUniqueNotNUllName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.lesson.CreateLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;

/**
 * Parses input arguments and creates a new CreateLessonCommand object
 */
public class CreateLessonCommandParser implements Parser<CreateLessonCommand> {
    private List<String> names = new ArrayList<>();
    /**
     * Parses the given {@code String} of arguments in the context of the CreateLessonCommand
     * and returns an CreateLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LESSON, PREFIX_STARTTIME, PREFIX_ENDTIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LESSON, PREFIX_STARTTIME, PREFIX_ENDTIME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateLessonCommand.MESSAGE_USAGE));
        }
        checkUniqueNotNUllName(argMultimap);
        checkMaxOneStartTime(argMultimap);
        checkMaxOneEndTime(argMultimap);
        checkMaxOneLesson(argMultimap);

        String lessonName = argMultimap.getValue(PREFIX_LESSON).get();
        if (lessonName.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateLessonCommand.MESSAGE_USAGE));
        }
        LocalDateTime startTime = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_STARTTIME).get());
        LocalDateTime endTime = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_ENDTIME).get());
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);

        if (endTime.isBefore(startTime)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateLessonCommand.MESSAGE_DATE));
        }

        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            if (name.trim().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateLessonCommand.MESSAGE_USAGE));
            }
            name = name.trim();
            nameKeywords.set(i, name);
        }
        names = nameKeywords;

        return new CreateLessonCommand(names, new NamePredicate(nameKeywords),
            lessonName, startTime, endTime);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
