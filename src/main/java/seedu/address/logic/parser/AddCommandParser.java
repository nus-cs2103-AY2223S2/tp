package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalCondition;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_AGE, PREFIX_TAG, PREFIX_SCHEDULE, PREFIX_MEDICAL);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Optional<String> medicalAge = argMultimap.getValue(PREFIX_AGE);
        Optional<String> medicalString = argMultimap.getValue(PREFIX_MEDICAL);
        Optional<String> time = argMultimap.getValue(PREFIX_SCHEDULE);
        Person person;
        if (medicalString.isEmpty()) {
            if (medicalAge.isEmpty()) {
                if (time.isEmpty()) {
                    person = new Person(name, phone, email, address, tagList);
                } else {
                    person = new Person(name, phone, email, address, tagList, ParserUtil.parseTime(time.get()));
                }
            } else {
                Age age = ParserUtil.parseAge(medicalAge.get().toString());
                if (time.isEmpty()) {
                    person = new Person(name, phone, email, address, age, tagList);
                } else {
                    person = new Person(name, phone, email, address, age, tagList, ParserUtil.parseTime(time.get()));
                }
            }
        } else {
            MedicalCondition medicalCondition = ParserUtil.parseMedicalCond(medicalString.get());
            if (medicalAge.isEmpty()) {
                if (time.isEmpty()) {
                    person = new Person(name, phone, email, address, tagList, medicalCondition);
                } else {
                    person = new Person(name, phone, email, address, tagList,
                        ParserUtil.parseTime(time.get()), medicalCondition);
                }
            } else {
                Age age = ParserUtil.parseAge(medicalAge.get().toString());
                if (time.isEmpty()) {
                    person = new Person(name, phone, email, address, age, tagList, medicalCondition);
                } else {
                    person = new Person(name, phone, email, address, age, tagList,
                        ParserUtil.parseTime(time.get()), medicalCondition);
                }
            }
        }
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
