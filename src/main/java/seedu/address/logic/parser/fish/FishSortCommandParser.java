package seedu.address.logic.parser.fish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.fish.FishSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fish.Fish;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FishSortCommandParser {
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
            Comparator<Fish> nameComparator = new Comparator<Fish>() {
                @Override
                public int compare(Fish f1, Fish f2) {
                    return f1.getName().toString().compareTo(f2.getName().toString());
                }
            };
            return new FishSortCommand(nameComparator);
        case "lfd":
            Comparator<Fish> lastFedComparator = new Comparator<Fish>() {
                @Override
                public int compare(Fish f1, Fish f2) {
                    return f1.getLastFedDate().getLocalDate().compareTo(f2.getLastFedDate().getLocalDate());
                }
            };
            return new FishSortCommand(lastFedComparator);
        case "s":
            Comparator<Fish> speciesComparator = new Comparator<Fish>() {
                @Override
                public int compare(Fish f1, Fish f2) {
                    return f1.getSpecies().toString().compareTo(f2.getSpecies().toString());
                }
            };
            return new FishSortCommand(speciesComparator);
        case "fi":

        case "tk":

        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FishSortCommand.MESSAGE_USAGE));
        }
    }
}
