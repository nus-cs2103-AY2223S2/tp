package seedu.address.logic.parser.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.ParserUtil.checkNotNullNames;
import static seedu.address.logic.parser.ParserUtil.checkUniqueNotNullStatus;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.homework.ViewHomeworkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.HomeworkIsCompletePredicate;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new ViewHomeworkCommand object
 */
public class ViewHomeworkCommandParser implements Parser<ViewHomeworkCommand> {
    private List<String> names = new ArrayList<>();

    /**
     * Checks if the list of strings contains an empty string.
     *
     * @param list the list of strings to be checked.
     * @return true if the list does not contain an empty string, false otherwise.
     */
    private boolean checkEmptyString(List<String> list) {
        for (String s : list) {
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ViewHomeworkCommand
     * and returns a ViewHomeworkCommand object for execution.
     *
     * @param args the user input to be parsed into a ViewHomeworkCommand object.
     * @return a ViewHomeworkCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewHomeworkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewHomeworkCommand.MESSAGE_USAGE));
        }

        Predicate<Student> namePredicate;
        boolean defaultPredicateFlag;

        // If name is present, create a predicate to filter by name
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            checkNotNullNames(nameKeywords);

            names = nameKeywords;
            namePredicate = new NamePredicate(nameKeywords);
            defaultPredicateFlag = false;
        } else {
            namePredicate = PREDICATE_SHOW_ALL_STUDENTS;
            defaultPredicateFlag = true;
        }

        // If status is present, create a predicate to filter by status
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            checkUniqueNotNullStatus(argMultimap);

            String status = argMultimap.getValue(PREFIX_STATUS).get();
            boolean isCompleted = ParserUtil.parseStatus(status);
            HomeworkIsCompletePredicate statusPredicate = new HomeworkIsCompletePredicate(isCompleted);
            return new ViewHomeworkCommand(names, namePredicate, statusPredicate, defaultPredicateFlag);
        } else {
            return new ViewHomeworkCommand(names, namePredicate, defaultPredicateFlag);
        }
    }
}
