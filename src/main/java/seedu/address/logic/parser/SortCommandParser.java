package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class SortCommandParser implements Parser<SortCommand> {
    private static final HashMap<Prefix, Comparator<Person>> COMPARATOR_HASH_MAP = new HashMap<>() {{
        put(PREFIX_ADDRESS, Comparator.comparing(Person::getAddress));
        put(PREFIX_EMAIL, Comparator.comparing(Person::getEmail));
        put(PREFIX_GROUP_TAG, Comparator.comparing(Person::getGroupTags));
        put(PREFIX_MODULE_TAG, Comparator.comparing(Person::getModuleTags));
        put(PREFIX_NAME, Comparator.comparing(Person::getName));
        put(PREFIX_PHONE, Comparator.comparing(Person::getPhone));
        put(PREFIX_TELEGRAM_HANDLE, Comparator.comparing(Person::getTelegramHandle));
    }};

    private static final HashMap<Prefix, Boolean> IS_DEFAULT_ASCENDING_HASH_MAP = new HashMap<>() {{
        put(PREFIX_ADDRESS, true);
        put(PREFIX_EMAIL, true);
        put(PREFIX_GROUP_TAG, false);
        put(PREFIX_MODULE_TAG, false);
        put(PREFIX_NAME, true);
        put(PREFIX_PHONE, true);
        put(PREFIX_TELEGRAM_HANDLE, true);
    }};

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        List<PrefixPosition> prefixPositions =
                ArgumentTokenizer.findAllPrefixPositions(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_GROUP_TAG, PREFIX_MODULE_TAG);

        Comparator<Person> comparator = prefixPositions.stream()
                .map(pp -> getComparatorFromPrefixPosition(pp, args))
                .reduce(Comparator.comparing(Person::getName), this::combineComparators);

        return new SortCommand(comparator);
    }

    private Comparator<Person> getComparatorFromPrefixPosition(PrefixPosition prefixPosition, String args) {
        Comparator<Person> comparator = COMPARATOR_HASH_MAP.get(prefixPosition.getPrefix());

        if (!isAscending(prefixPosition, args)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    private boolean isAscending(PrefixPosition prefixPosition, String args) {
        boolean isAscending = IS_DEFAULT_ASCENDING_HASH_MAP.get(prefixPosition.getPrefix());

        int startPosition = prefixPosition.getStartPosition();
        int prefixLength = prefixPosition.getPrefix().getPrefix().length();
        char firstCharacter = Character.toLowerCase(args.charAt(startPosition + prefixLength));

        isAscending |= firstCharacter == 'a';
        isAscending &= firstCharacter != 'd';

        return isAscending;
    }

    private Comparator<Person> combineComparators(Comparator<Person> secondary, Comparator<Person> primary) {
        return primary.thenComparing(secondary);
    }
}
