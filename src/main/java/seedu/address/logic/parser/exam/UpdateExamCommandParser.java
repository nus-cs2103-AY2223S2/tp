package seedu.address.logic.parser.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exam.UpdateExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Grade;
import seedu.address.model.student.NamePredicate;

/**
 * An UpdateHomeworkCommandParser that parses input arguments and creates a new UpdateHomeworkCommand object
 */
public class UpdateExamCommandParser implements Parser<UpdateExamCommand> {
    private List<String> names = new ArrayList<>();
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateHomeworkCommand
     * and returns an UpdateHomeworkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateExamCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX, PREFIX_EXAM, PREFIX_STARTTIME,
                PREFIX_ENDTIME, PREFIX_WEIGHT, PREFIX_GRADE);

        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_EXAM)
            && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_STARTTIME)
            && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_ENDTIME)
            && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_WEIGHT)
            && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_GRADE))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateExamCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            name = name.trim();
            if (name.trim().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateExamCommand.MESSAGE_USAGE));
            }
            //            int spaceIndex = name.indexOf(" ");
            //            if (spaceIndex != -1) {
            //                name = name.substring(0, spaceIndex);
            //            }
            nameKeywords.set(i, name);
        }
        names = nameKeywords;

        if (nameKeywords.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "Only one name is allowed for update exam command."));
        }

        // if exam name is not present, set it to null, else parse it
        Optional<String> examName = Optional.empty();
        if (argMultimap.getValue(PREFIX_EXAM).isPresent()) {
            examName = Optional.of(argMultimap.getValue(PREFIX_EXAM).get());
        }

        // if deadline is not present, set it to null, else parse it
        Optional<LocalDateTime> startTime = Optional.empty();
        if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
            startTime = Optional.of(ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get()));
        }

        Optional<LocalDateTime> endTime = Optional.empty();
        if (argMultimap.getValue(PREFIX_ENDTIME).isPresent()) {
            endTime = Optional.of(ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_ENDTIME).get()));
        }

        Optional<Double> weight = Optional.empty();
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            weight = Optional.of(ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHT).get()));
        }

        Optional<Grade> grade = Optional.empty();
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            grade = Optional.of(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        return new UpdateExamCommand(names, index, new NamePredicate(nameKeywords),
            examName, startTime, endTime, weight, grade);
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
