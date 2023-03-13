package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    // maps the prefixes to their comparators
    private static final HashMap<Prefix, Comparator<Person>> COMPARATOR_HASH_MAP = new HashMap<>() {{
            put(Prefix.ADDRESS, Comparator.comparing(Person::getAddress));
            put(Prefix.EMAIL, Comparator.comparing(Person::getEmail));
            put(Prefix.GROUP_TAG, Comparator.comparing(Person::getGroupTags));
            put(Prefix.MODULE_TAG, Comparator.comparing(Person::getModuleTags));
            put(Prefix.NAME, Comparator.comparing(Person::getName));
            put(Prefix.PHONE, Comparator.comparing(Person::getPhone));
            put(Prefix.TELEGRAM_HANDLE, Comparator.comparing(Person::getTelegramHandle));
        }};

    // use this as the default sorting direction
    private static final HashMap<Prefix, Boolean> IS_ASCENDING_DEFAULT_HASH_MAP = new HashMap<>() {{
            put(Prefix.ADDRESS, true);
            put(Prefix.EMAIL, true);
            put(Prefix.GROUP_TAG, false);
            put(Prefix.MODULE_TAG, false);
            put(Prefix.NAME, true);
            put(Prefix.PHONE, true);
            put(Prefix.TELEGRAM_HANDLE, true);
        }};

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.trim().equals("index")) {
            Comparator<Person> indexComparator = new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getContactIndex().compareTo(o2.getContactIndex());
                }
            };
            return new SortCommand(indexComparator, "Reverting to original index!");
        }
        // the prefix positions are ordered from first to last
        // the argument multimap gives an unordered list so we cannot use it here
        List<PrefixPosition> prefixPositions = ArgumentTokenizer
                .findAllPrefixPositions(args, Prefix.NAME, Prefix.PHONE, Prefix.EMAIL, Prefix.ADDRESS,
                        Prefix.TELEGRAM_HANDLE, Prefix.GROUP_TAG, Prefix.MODULE_TAG);

        // we reverse here because of the nature of the reduce function
        // we want the name comparator to go last,
        // so if we have m/ g/ as our comparator, then we will process them as n/ g/ m/
        prefixPositions.sort(Comparator.comparing(PrefixPosition::getStartPosition).reversed());

        // stream of comparators based on pp
        Stream<Comparator<Person>> comparatorStream = prefixPositions.stream()
                .map(pp -> COMPARATOR_HASH_MAP.get(pp.getPrefix()));

        // stream of whether the prefix sort needs to be ascending
        Stream<Boolean> isAscendingStream = prefixPositions.stream()
                .map(pp -> isAscending(pp, args));

        // processes comparators from first to last (first goes first)
        // creates one chained comparator
        Comparator<Person> comparator = CollectionUtil
                .zip(comparatorStream, isAscendingStream, this::reverseComparatorIfDescending)
                .reduce(Comparator.comparing(Person::getName), this::combineComparators);

        // converts the prefixes into their name descriptors
        // n/ -> Name for example
        Stream<String> comparatorNameStream = prefixPositions.stream()
                .map(pp -> pp.getPrefix().getDescription());

        // we recompute this because streams can only be used once
        isAscendingStream = prefixPositions.stream()
                .map(pp -> isAscending(pp, args));

        // string to show to the user
        String comparatorDesc = CollectionUtil
                .zip(comparatorNameStream, isAscendingStream, this::getComparatorDesc)
                .collect(Collectors.joining("\n"));

        return new SortCommand(comparator, comparatorDesc);
    }

    /**
     * Checks if a prefix is meant to be sorted in ascending or descending order.
     */
    private boolean isAscending(PrefixPosition pp, String args) {
        // default value when the first character is neither 'a' nor 'd'
        boolean isAscending = IS_ASCENDING_DEFAULT_HASH_MAP.get(pp.getPrefix());

        // checks the character immediately after the prefix
        int startPosition = pp.getStartPosition();
        int prefixLength = pp.getPrefix().getPrefix().length();
        int firstCharIndex = startPosition + prefixLength;

        if (firstCharIndex < args.length()) {
            char firstChar = Character.toLowerCase(args.charAt(firstCharIndex));

            isAscending |= firstChar == 'a';
            isAscending &= firstChar != 'd';
        }

        return isAscending;
    }

    /**
     * Reverses the comparator when necessary.
     */
    private Comparator<Person> reverseComparatorIfDescending(Comparator<Person> comparator, boolean isAscending) {
        if (!isAscending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Formats the string to be displayed to the user.
     */
    private String getComparatorDesc(String comparatorName, boolean isAscending) {
        return String.format("%s: %s", comparatorName,
                isAscending ? "Ascending" : "Descending");
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
