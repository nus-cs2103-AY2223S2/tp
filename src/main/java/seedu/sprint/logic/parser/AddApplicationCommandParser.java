package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddApplicationCommand object.
 */
public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicationCommand
     * and returns an AddApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddApplicationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = ParserUtil.parseCompanyName(
                argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        CompanyEmail companyEmail = ParserUtil.parseCompanyEmail(
                argMultimap.getValue(PREFIX_COMPANY_EMAIL).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Application application = new Application(role, companyName, companyEmail, status, null, tagList);

        return new AddApplicationCommand(application);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
