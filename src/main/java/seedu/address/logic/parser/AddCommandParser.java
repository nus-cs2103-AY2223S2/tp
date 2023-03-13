package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, Prefix.NAME, Prefix.PHONE, Prefix.EMAIL,
                        Prefix.ADDRESS, Prefix.TELEGRAM_HANDLE,
                        Prefix.GROUP_TAG, Prefix.MODULE_TAG);

        if (!arePrefixesPresent(argMultimap, Prefix.NAME, Prefix.ADDRESS, Prefix.PHONE, Prefix.EMAIL,
                Prefix.TELEGRAM_HANDLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(Prefix.NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(Prefix.PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(Prefix.EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(Prefix.ADDRESS).get());
        TelegramHandle telegramHandle = ParserUtil
                .parseTelegramHandle(argMultimap.getValue(Prefix.TELEGRAM_HANDLE).get());
        Set<GroupTag> groupTagList = ParserUtil.parseGroupTags(argMultimap.getAllValues(Prefix.GROUP_TAG));
        Set<ModuleTag> moduleTagList = ParserUtil.parseModuleTags(argMultimap.getAllValues(Prefix.MODULE_TAG));
        Person person = new Person(name, phone, email, address, telegramHandle, groupTagList, moduleTagList);

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
