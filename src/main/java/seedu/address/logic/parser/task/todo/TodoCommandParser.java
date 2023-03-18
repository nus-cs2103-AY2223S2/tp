package seedu.address.logic.parser.task.todo;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.task.todo.TodoCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;
import seedu.address.model.todo.ApplicationDeadline;
import seedu.address.model.todo.InternshipTodo;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new TodoCommand object
 */
public class TodoCommandParser implements Parser<TodoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TodoCommand
     * and returns an TodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TodoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = ParserUtil.parseCompanyName(
                                                    argMultimap.getValue(PREFIX_COMPANY_NAME).orElse(null));
        JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).orElse(null));
        ApplicationDeadline deadline = ParserUtil.parseDeadline(
                argMultimap.getValue(PREFIX_DEADLINE).orElse(null));

        InternshipTodo todo = new InternshipTodo(companyName, jobTitle, deadline);

        return new TodoCommand(todo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
