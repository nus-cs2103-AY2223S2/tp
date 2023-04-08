package seedu.ultron.logic.parser;

import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.ultron.logic.commands.AddCommand;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.opening.Company;
import seedu.ultron.model.opening.Email;
import seedu.ultron.model.opening.Keydate;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.Position;
import seedu.ultron.model.opening.Remark;
import seedu.ultron.model.opening.Status;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_EMAIL,
                        PREFIX_STATUS, PREFIX_REMARK, PREFIX_KEYDATE);

        if (arePrefixesAbsent(argMultimap, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_EMAIL, PREFIX_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        if (!isCompanyPresent(argMultimap, PREFIX_COMPANY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Company is missing\nExample: "
                    + PREFIX_COMPANY + "Shopee "));
        }
        if (!isPositionPresent(argMultimap, PREFIX_POSITION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Position is missing\nExample: "
                    + PREFIX_POSITION + "Backend Developer "));
        }
        if (!isStatusPresent(argMultimap, PREFIX_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Status is missing\nExample: "
                    + PREFIX_STATUS + "APPLIED "));
        }
        if (!isEmailPresent(argMultimap, PREFIX_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Email is missing\nExample: "
                    + PREFIX_EMAIL + "hr@shopee.com "));
        }
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Optional<Remark> remark = argMultimap.getValue(PREFIX_REMARK).map(ParserUtil::parseRemark);
        List<Keydate> keydateList = ParserUtil.parseKeydates(argMultimap.getAllValues(PREFIX_KEYDATE));

        Opening opening = new Opening(position, company, email, status, remark.orElse(null), keydateList);

        return new AddCommand(opening);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> !argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isPositionPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    private static boolean isCompanyPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    private static boolean isEmailPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    private static boolean isStatusPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
