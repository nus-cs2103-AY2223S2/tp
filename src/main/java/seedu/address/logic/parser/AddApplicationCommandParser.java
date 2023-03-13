package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;


/**
 * Parses input arguments and creates a new AddApplicationCommand object
 */
public class AddApplicationCommandParser implements ApplicationParser<AddApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicationCommand
     * and returns an AddApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddApplicationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        Role role = ApplicationParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        CompanyName companyName = ApplicationParserUtil.parseCompanyName(
                argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        CompanyEmail companyEmail = ApplicationParserUtil.parseCompanyEmail(
                argMultimap.getValue(PREFIX_COMPANY_EMAIL).get());
        Status status = ApplicationParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        Application application = new Application(role, companyName, companyEmail, status);

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
