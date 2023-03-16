package seedu.address.logic.parser.fish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDING_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.UnassignedTank;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FishAddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FishAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LAST_FED_DATE, PREFIX_SPECIES,
                        PREFIX_FEEDING_INTERVAL, PREFIX_TANK,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LAST_FED_DATE, PREFIX_SPECIES,
                PREFIX_FEEDING_INTERVAL, PREFIX_TANK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        LastFedDate lastFedDate = ParserUtil.parseLastFedDate(argMultimap.getValue(PREFIX_LAST_FED_DATE).get());
        Species species = ParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get());
        FeedingInterval feedingInterval = ParserUtil.parseFeedingInterval(argMultimap.getValue(PREFIX_FEEDING_INTERVAL)
                .get());
        // checks for tank Index
        Index tankIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TANK).get());
        Tank tempTank = new UnassignedTank(null, null);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Fish fish = new Fish(name, lastFedDate, species, feedingInterval, tempTank, tagList);

        return new FishAddCommand(fish, tankIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
