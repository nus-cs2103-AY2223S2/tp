package seedu.address.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneDate;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneDone;
import static seedu.address.logic.parser.ParserUtil.checkMaxOneLesson;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.lesson.ViewLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.LessonBelongsToDatePredicate;
import seedu.address.model.student.LessonDonePredicate;
import seedu.address.model.student.LessonSubjectPredicate;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new ViewLessonCommand object
 */
public class ViewLessonCommandParser implements Parser<ViewLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewLessonCommand
     * and returns an ViewLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_LESSON,
            PREFIX_DONE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewLessonCommand.MESSAGE_USAGE));
        }

        Predicate<Student> namePredicate;
        Predicate<Lesson> lessonPredicate = lesson -> true;
        Predicate<Lesson> donePredicate = lesson -> true;
        List<String> nameList = new ArrayList<>();
        boolean defaultPredicateFlag;

        // If name is present, create a predicate to filter by name
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {

            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            // for all the names, trim the name and only take the first word
            for (int i = 0; i < nameKeywords.size(); i++) {
                String name = nameKeywords.get(i);
                name = name.trim();
                if (name.trim().isEmpty()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewLessonCommand.MESSAGE_USAGE));
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

        if (argMultimap.getValue(PREFIX_LESSON).isPresent()) {
            checkMaxOneLesson(argMultimap);
            String lessonSubject = argMultimap.getValue(PREFIX_LESSON).get();
            lessonPredicate = new LessonSubjectPredicate(lessonSubject);
        }

        if (argMultimap.getValue(PREFIX_DONE).isPresent()) {
            checkMaxOneDone(argMultimap);
            String done = argMultimap.getValue(PREFIX_DONE).get();
            if (!done.equals("done") && !done.equals("not done")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewLessonCommand.MESSAGE_USAGE));
            }
            donePredicate = new LessonDonePredicate(done);
        }

        // If date is present, create a predicate to filter by status
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            checkMaxOneDate(argMultimap);
            String date = argMultimap.getValue(PREFIX_DATE).get();
            LocalDate targetDate = ParserUtil.parseDate(date);
            LessonBelongsToDatePredicate datePredicate = new LessonBelongsToDatePredicate(targetDate);
            return new ViewLessonCommand(nameList, namePredicate, datePredicate, lessonPredicate, donePredicate,
                defaultPredicateFlag);
        } else {
            return new ViewLessonCommand(nameList, namePredicate, lessonPredicate, donePredicate,
                defaultPredicateFlag);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewLessonCommandParser // instanceof handles nulls
            && this.equals(other)); // state check
    }
}
