package seedu.address.logic.parser.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneDate;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneDone;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneExam;
import static seedu.address.logic.parser.ParserUtil.checkUniqueNotNUllName;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exam.ViewExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Exam;
import seedu.address.model.student.ExamDatePredicate;
import seedu.address.model.student.ExamDonePredicate;
import seedu.address.model.student.ExamPredicate;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new ViewExamCommand object
 */
public class ViewExamCommandParser implements Parser<ViewExamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewExamCommand
     * and returns an ViewExamCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewExamCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_EXAM,
            PREFIX_DONE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewExamCommand.MESSAGE_USAGE));
        }

        Predicate<Student> namePredicate;
        Predicate<Exam> examPredicate = exam -> true;
        Predicate<Exam> donePredicate = exam -> true;
        boolean defaultPredicateFlag;
        List<String> nameList = new ArrayList<>();

        // If name is present, create a predicate to filter by name
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            checkUniqueNotNUllName(argMultimap);
            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            // for all the names, trim the name and only take the first word
            for (int i = 0; i < nameKeywords.size(); i++) {
                String name = nameKeywords.get(i);
                name = name.trim();
                if (name.trim().isEmpty()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewExamCommand.MESSAGE_USAGE));
                }
                nameKeywords.set(i, name);
            }
            nameList = nameKeywords;
            namePredicate = new NamePredicate(nameKeywords);
            defaultPredicateFlag = false;
        } else {
            namePredicate = PREDICATE_SHOW_ALL_STUDENTS;
            defaultPredicateFlag = true;
        }

        if (argMultimap.getValue(PREFIX_EXAM).isPresent()) {
            checkMaxOneExam(argMultimap);
            String exam = argMultimap.getValue(PREFIX_EXAM).get();
            examPredicate = new ExamPredicate(exam);
        }

        if (argMultimap.getValue(PREFIX_DONE).isPresent()) {
            checkMaxOneDone(argMultimap);
            String done = argMultimap.getValue(PREFIX_DONE).get();
            if (!done.equals("done") && !done.equals("not done")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewExamCommand.MESSAGE_USAGE));
            }
            donePredicate = new ExamDonePredicate(done);
        }

        // If date is present, create a predicate to filter by date
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            checkMaxOneDate(argMultimap);
            String date = argMultimap.getValue(PREFIX_DATE).get();
            LocalDate targetDate = ParserUtil.parseDate(date);
            ExamDatePredicate datePredicate = new ExamDatePredicate(targetDate);
            return new ViewExamCommand(nameList, namePredicate, datePredicate, examPredicate, donePredicate,
                defaultPredicateFlag);
        } else {
            return new ViewExamCommand(nameList, namePredicate, examPredicate, donePredicate, defaultPredicateFlag);
        }
    }
}
