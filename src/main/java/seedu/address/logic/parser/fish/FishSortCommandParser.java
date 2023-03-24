package seedu.address.logic.parser.fish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.fish.FishSortCommand;
import seedu.address.logic.parser.Parser;
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
            -> f.getLastFedDate().getLocalDate());
    /* Compares species and sorts them lexicographically from small to large */
    public static final Comparator<Fish> SPECIES_COMPARATOR = Comparator.comparing(f
            -> f.getSpecies().toString());
    /* Compares feeding intervals and sorts them lexicographically from small to large */
    public static final Comparator<Fish> FEEDING_COMPARATOR = (f1, f2) -> {
        String c1 = f1.getFeedingInterval().days + f1.getFeedingInterval().hours;
        String c2 = f2.getFeedingInterval().days + f2.getFeedingInterval().hours;
        return c1.compareTo(c2);
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
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishSortCommand.MESSAGE_USAGE));
        }
        switch (args.trim().toLowerCase()) {
        case "n":
            return new FishSortCommand(NAME_COMPARATOR);
        case "lfd":
            return new FishSortCommand(LAST_FED_COMPARATOR);
        case "s":
            return new FishSortCommand(SPECIES_COMPARATOR);
        case "fi":
            return new FishSortCommand(FEEDING_COMPARATOR);
        case "tk":
            return new FishSortCommand(TANK_COMPARATOR);
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FishSortCommand.MESSAGE_USAGE));
        }
    }
}
