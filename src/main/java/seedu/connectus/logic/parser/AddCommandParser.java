package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.Set;
import java.util.stream.Stream;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_REMARK, PREFIX_MODULE,
            PREFIX_SOCMED_INSTAGRAM, PREFIX_SOCMED_TELEGRAM, PREFIX_SOCMED_WHATSAPP, PREFIX_CCA, PREFIX_MAJOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Person person = new Person(name);

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            person.setPhone(phone);
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            person.setEmail(email);
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            person.setAddress(address);
        }

        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
            person.setBirthday(birthday);
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            Set<Remark> remarkList = ParserUtil.parseRemarks(argMultimap.getAllValues(PREFIX_REMARK));
            person.setRemarks(remarkList);
        }

        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            Set<Module> moduleList = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE));
            person.setModules(moduleList);
        }
        if (argMultimap.getValue(PREFIX_CCA).isPresent()) {
            Set<Cca> ccaList = ParserUtil.parseCcas(argMultimap.getAllValues(PREFIX_CCA));
            person.setCcas(ccaList);
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            Set<Major> majorList = ParserUtil.parseMajors(argMultimap
                    .getAllValues(PREFIX_MAJOR));
            person.setMajors(majorList);
        }

        {
            person.setSocialMedia(ParserUtil.parseSocialMedia(argMultimap));
        }

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
