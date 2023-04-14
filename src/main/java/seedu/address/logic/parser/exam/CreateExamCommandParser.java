package seedu.address.logic.parser.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneEndTime;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneExam;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneGrade;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneStartTime;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneWeight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.exam.CreateExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Grade;
import seedu.address.model.student.NamePredicate;

/**
 * Parses input arguments and creates a new CreateHomeworkExam object
 */
public class CreateExamCommandParser implements Parser<CreateExamCommand> {
    private List<String> names = new ArrayList<>();
    /**
     * Parses the given {@code String} of arguments in the context of the CreateExamCommand
     * and returns a CreateExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateExamCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EXAM, PREFIX_STARTTIME, PREFIX_ENDTIME,
                        PREFIX_WEIGHT, PREFIX_GRADE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EXAM, PREFIX_STARTTIME, PREFIX_ENDTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateExamCommand.MESSAGE_USAGE));
        }

        checkMaxOneExam(argMultimap);
        checkMaxOneStartTime(argMultimap);
        checkMaxOneEndTime(argMultimap);
        checkMaxOneWeight(argMultimap);
        checkMaxOneGrade(argMultimap);

        String examDescription = argMultimap.getValue(PREFIX_EXAM).get();
        if (examDescription.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateExamCommand.MESSAGE_USAGE));
        }
        LocalDateTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get());
        LocalDateTime endTime = ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_ENDTIME).get());
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);

        Double weightage = null;
        //if the weightage of an exam is provided, store the weight
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            weightage = ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHT).get());
        }

        Grade grade = null;
        //if the grade of an exam is provided, store the grade
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        }

        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            name = name.trim();
            if (name.trim().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateExamCommand.MESSAGE_USAGE));
            }
            nameKeywords.set(i, name);
        }
        names = nameKeywords;

        return new CreateExamCommand(names, new NamePredicate(nameKeywords),
                examDescription, startTime, endTime, weightage, grade);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
