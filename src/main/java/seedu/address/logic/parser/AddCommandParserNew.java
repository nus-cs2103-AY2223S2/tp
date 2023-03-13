package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_KEYDATE;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_STATUS;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommandNew;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.opening.Company;
import seedu.address.model.opening.Date;
import seedu.address.model.opening.Email;
import seedu.address.model.opening.Opening;
import seedu.address.model.opening.Position;
import seedu.address.model.opening.Remark;
import seedu.address.model.opening.Status;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParserNew implements ParserNew<AddCommandNew> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandNew parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_EMAIL,
                        PREFIX_STATUS, PREFIX_REMARK, PREFIX_KEYDATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_EMAIL, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandNew.MESSAGE_USAGE));
        }

        Position position = ParserUtilNew.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Company company = ParserUtilNew.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Email email = ParserUtilNew.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Status status = ParserUtilNew.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Remark remark = ParserUtilNew.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Set<Date> dateList = ParserUtilNew.parseDates(argMultimap.getAllValues(PREFIX_KEYDATE));

        Opening opening = new Opening(position, company, email, status, remark, dateList);

        return new AddCommandNew(opening);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
