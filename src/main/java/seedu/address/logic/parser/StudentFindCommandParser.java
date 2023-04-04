package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.student.StudentFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
/**
 * Parses input arguments and creates a new StudentFindCommand object
 */
public class StudentFindCommandParser implements Parser<StudentFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StudentFindCommand
     * and returns a StudentFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StudentFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String studentClass = trimmedArgs.split(" ")[0];
        trimmedArgs = trimmedArgs.replaceFirst(studentClass, "").trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new StudentFindCommand(studentClass,
                new StudentNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
