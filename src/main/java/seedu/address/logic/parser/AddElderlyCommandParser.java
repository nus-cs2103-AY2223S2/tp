package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddElderlyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.Person;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.person.information.NRIC;
import seedu.address.model.tag.Tag;

public class AddElderlyCommandParser implements Parser<AddElderlyCommand> {

    public AddElderlyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NRIC, PREFIX_AGE, PREFIX_RISK,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC, PREFIX_AGE, PREFIX_RISK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddElderlyCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        NRIC nric = ParserUtil.parseNRIC(argMultimap.getValue(PREFIX_NRIC).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        RiskLevel risk = ParserUtil.parseRiskLevel(argMultimap.getValue(PREFIX_RISK).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Elderly person = new Elderly(name, phone, email, address, nric, age, risk, tagList);

        return new AddElderlyCommand(person);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}









