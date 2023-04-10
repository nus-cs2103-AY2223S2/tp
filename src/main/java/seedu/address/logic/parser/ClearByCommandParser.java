package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.ClearByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.JobTitle;

/**
 * Parses input arguments and creates a new ClearByCommand object
 */
public class ClearByCommandParser implements Parser<ClearByCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearByCommand
     * and returns an ClearByCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearByCommand parse(String args) throws ParseException {
        Prefix prefix;

        try {
            String trimArgs = args.trim();
            prefix = new Prefix(trimArgs.substring(0, 2));
        } catch (IndexOutOfBoundsException ite) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearByCommand.MESSAGE_NO_PARAMETER));
        }

        if (!(prefix.equals(PREFIX_COMPANY_NAME) || prefix.equals(PREFIX_JOB_TITLE) || prefix.equals(PREFIX_STATUS))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearByCommand.MESSAGE_INVALID_PARAMETER));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, prefix);

        if (!arePrefixesPresent(argMultimap, prefix)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearByCommand.MESSAGE_USAGE));
        }

        return parseParameter(prefix, argMultimap);
    }

    private ClearByCommand parseParameter(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        if (prefix.equals(PREFIX_COMPANY_NAME)) {
            CompanyName companyName = ParserUtil.parseCompanyName(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).orElse(null));

            return new ClearByCommand(companyName);
        } else if (prefix.equals(PREFIX_JOB_TITLE)) {
            JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).orElse(null));

            return new ClearByCommand(jobTitle);
        } else if (prefix.equals(PREFIX_STATUS)) {
            InternshipStatus status = ParserUtil.parseInternshipStatus(
                    argMultimap.getValue(PREFIX_STATUS).orElse(null));

            return new ClearByCommand(status);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearByCommand.MESSAGE_INVALID_PARAMETER));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
