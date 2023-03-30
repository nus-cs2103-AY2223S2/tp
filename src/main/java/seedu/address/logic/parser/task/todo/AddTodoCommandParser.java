package seedu.address.logic.parser.task.todo;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.task.todo.AddTodoCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.InternshipTodo;

/**
 * Parses input arguments and creates a new AddTodoCommand object
 */
public class AddTodoCommandParser implements Parser<AddTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTodoCommand
     * and returns an AddTodoCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTodoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = ParserUtil.parseCompanyName(
                                                    argMultimap.getValue(PREFIX_COMPANY_NAME).orElse(null));
        JobTitle jobTitle = ParserUtil.parseJobTitle(
                argMultimap.getValue(PREFIX_JOB_TITLE).orElse(null));
        ApplicationDeadline deadline = ParserUtil.parseDeadline(
                argMultimap.getValue(PREFIX_DEADLINE).orElse(null));

        InternshipTodo todo = new InternshipTodo(
                companyName, jobTitle, deadline);

        return new AddTodoCommand(todo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
