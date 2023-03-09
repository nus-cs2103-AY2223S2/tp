package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_DATE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommandNew;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.opening.Company;
import seedu.address.model.opening.Date;
import seedu.address.model.opening.Email;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandNew.MESSAGE_USAGE));
        }

        Position position = ParserUtilNew.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Company company = 
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, address, remark, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
