package seedu.address.logic.parser.fish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;
import static seedu.address.model.date.DateUtil.parseFeedingIntervalDays;
import static seedu.address.model.date.DateUtil.parseFeedingIntervalHours;

import java.util.Comparator;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishSortCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fish.Fish;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FishSortCommandParser implements Parser<FishSortCommand> {
    /* Compares names and sorts them lexicographically from small to large */
    public static final Comparator<Fish> NAME_COMPARATOR = Comparator.comparing(f
            -> f.getName().toString());
    /* Compares last fed date and sorts them lexicographically from small to large */
    public static final Comparator<Fish> LAST_FED_COMPARATOR = Comparator.comparing(f
            -> f.getLastFedDateTime().getLocalDateTime());
    /* Compares species and sorts them lexicographically from small to large */
    public static final Comparator<Fish> SPECIES_COMPARATOR = Comparator.comparing(f
            -> f.getSpecies().toString());
    /* Compares feeding intervals and sorts them lexicographically from small to large */
    public static final Comparator<Fish> FEEDING_COMPARATOR = (f1, f2) -> {
        int d1 = Integer.parseInt(f1.getFeedingInterval().days);
        int h1 = Integer.parseInt(f1.getFeedingInterval().hours);
        int d2 = Integer.parseInt(f2.getFeedingInterval().days);
        int h2 = Integer.parseInt(f2.getFeedingInterval().hours);
        if (d1 == d2) {
            if (h1 == h2) return 0;
            return h1 > h2 ? 1 : -1;
        } else {
            return d1 > d2 ? 1 : -1;
        }
    };
    /* Compares tank names and sorts them lexicographically from small to large */
    public static final Comparator<Fish> TANK_COMPARATOR = Comparator.comparing(f
            -> f.getTank().getTankName().toString());

    /**
     * Parses the given {@code String} of arguments in the context of the FishSortCommand
     * and returns an FishSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FishSortCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_BY, PREFIX_TANK);
        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_BY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishSortCommand.MESSAGE_USAGE));
        }
        // Checks for a value, skips if no tankIndex was given
        String index = argMultimap.getValue(PREFIX_TANK).orElse(null);
        Index tankIndex = null;
        if (index != null) {
            tankIndex = ParserUtil.parseIndex(index);
        }

        String sortBy = argMultimap.getValue(PREFIX_SORT_BY).get();

        switch (sortBy) {
        case "n":
            return new FishSortCommand(NAME_COMPARATOR, tankIndex);
        case "lfd":
            return new FishSortCommand(LAST_FED_COMPARATOR, tankIndex);
        case "s":
            return new FishSortCommand(SPECIES_COMPARATOR, tankIndex);
        case "fi":
            return new FishSortCommand(FEEDING_COMPARATOR, tankIndex);
        case "tk":
            return new FishSortCommand(TANK_COMPARATOR, tankIndex);
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FishSortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
