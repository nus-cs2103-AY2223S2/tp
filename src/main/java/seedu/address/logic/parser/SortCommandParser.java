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

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    // maps the prefixes to their comparators
    private static final HashMap<Prefix, Comparator<Person>> COMPARATOR_HASH_MAP = new HashMap<>() {{
        put(PREFIX_ADDRESS, Comparator.comparing(Person::getAddress));
        put(PREFIX_EMAIL, Comparator.comparing(Person::getEmail));
        put(PREFIX_GROUP_TAG, Comparator.comparing(Person::getGroupTags));
        put(PREFIX_MODULE_TAG, Comparator.comparing(Person::getModuleTags));
        put(PREFIX_NAME, Comparator.comparing(Person::getName));
        put(PREFIX_PHONE, Comparator.comparing(Person::getPhone));
        put(PREFIX_TELEGRAM_HANDLE, Comparator.comparing(Person::getTelegramHandle));
    }};

    // use this as the default sorting direction
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

        // the prefix positions are ordered from first to last
        // the argument multimap gives an unordered list so we cannot use it here
        List<PrefixPosition> prefixPositions =
                ArgumentTokenizer.findAllPrefixPositions(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_GROUP_TAG, PREFIX_MODULE_TAG);

        // processes comparators from first to last (first goes first)
        // creates one chained comparator
        Comparator<Person> comparator = prefixPositions.stream()
                .map(pp -> getComparatorFromPrefixPosition(pp, args))
                .reduce(Comparator.comparing(Person::getName), this::combineComparators); // combines the comparators

        return new SortCommand(comparator);
    }

    /**
     * Converts a prefix into a comparator that accounts for ascending or descending order.
     * Comparator is used for sorting the persons.
     */
    private Comparator<Person> getComparatorFromPrefixPosition(PrefixPosition prefixPosition, String args) {
        Comparator<Person> comparator = COMPARATOR_HASH_MAP.get(prefixPosition.getPrefix());

        // comparators are by default in ascending order
        if (!isAscending(prefixPosition, args)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    /**
     * Checks if a prefix is meant to be sorted in ascending or descending order.
     */
    private boolean isAscending(PrefixPosition prefixPosition, String args) {
        // default value when the first character is neither 'a' nor 'd'
        boolean isAscending = IS_DEFAULT_ASCENDING_HASH_MAP.get(prefixPosition.getPrefix());

        // checks the character immediately after the prefix
        int startPosition = prefixPosition.getStartPosition();
        int prefixLength = prefixPosition.getPrefix().getPrefix().length();
        int firstCharacterIndex = startPosition + prefixLength;

        if (firstCharacterIndex < args.length()) {
            char firstCharacter = Character.toLowerCase(args.charAt(firstCharacterIndex));

            isAscending |= firstCharacter == 'a';
            isAscending &= firstCharacter != 'd';
        }

        return isAscending;
    }

    /**
     * Combines two comparators together.
     * Checks {@code primary} comparator first.
     * If {@code primary} comparator returns equal, then compare using {@code secondary} comparator.
     *
     * @param primary the main comparator.
     * @param secondary the comparator to use in case {@code primary} returns equal.
     */
    private Comparator<Person> combineComparators(Comparator<Person> secondary, Comparator<Person> primary) {
        return primary.thenComparing(secondary);
    }
}
