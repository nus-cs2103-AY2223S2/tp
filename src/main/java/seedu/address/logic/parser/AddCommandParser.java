package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    // public static final String VALID_PHONE_PLACEHOLDER = "11111111";
    // public static final String VALID_EMAIL_PLACEHOLDER = "amy@example.com";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = ParserUtil.parseCompanyName(
                                                    argMultimap.getValue(PREFIX_COMPANY_NAME).orElse(null));
        JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).orElse(null));

        InternshipApplication application = new InternshipApplication(companyName, jobTitle);

        return new AddCommand(application);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
