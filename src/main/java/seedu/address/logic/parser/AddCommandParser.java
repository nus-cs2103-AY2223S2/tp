package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.function.Function;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final String DEFAULT_PHONE = "00000000";
    private static final String DEFAULT_STATION = "Kent Ridge";
    private static final Function<Name, String> DEFAULT_EMAIL_MAPPER =
            name -> String.format("%s@gmail.com", name.getValue().toLowerCase().replace(" ", ""));
    private static final Function<Name, String> DEFAULT_TELEGRAM_HANDLE_MAPPER =
            name -> String.format("@%s00000", name.getValue().toLowerCase().replace(" ", ""));

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, Prefix.NAME, Prefix.PHONE, Prefix.EMAIL,
                        Prefix.STATION, Prefix.TELEGRAM_HANDLE,
                        Prefix.GROUP_TAG, Prefix.MODULE_TAG);

        if (!argMultimap.getPreamble().isEmpty() || argMultimap.getValue(Prefix.NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap
                .getValue(Prefix.NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap
                .getValue(Prefix.PHONE).orElse(DEFAULT_PHONE));
        Email email = ParserUtil.parseEmail(argMultimap
                .getValue(Prefix.EMAIL).orElse(DEFAULT_EMAIL_MAPPER.apply(name)));
        Station station = ParserUtil.parseStation(argMultimap
                .getValue(Prefix.STATION).orElse(DEFAULT_STATION));
        TelegramHandle telegramHandle = ParserUtil.parseTelegramHandle(argMultimap
                .getValue(Prefix.TELEGRAM_HANDLE).orElse(DEFAULT_TELEGRAM_HANDLE_MAPPER.apply(name)));
        ContactIndex placeholderContactIndex = new ContactIndex(Integer.MAX_VALUE);
        Set<GroupTag> groupTagSet = ParserUtil.parseGroupTags(argMultimap.getAllValues(Prefix.GROUP_TAG));
        Set<ModuleTag> moduleTagSet = ParserUtil.parseModuleTags(argMultimap.getAllValues(Prefix.MODULE_TAG));
        Person person = new Person(name, phone, email, station, telegramHandle,
                placeholderContactIndex, groupTagSet, moduleTagSet);

        return new AddCommand(person);
    }

}
