package seedu.address.logic.parser.tank.readings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMMONIA_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMPERATURE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tank.readings.ReadingsAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.DateUtil;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.Temperature;


/**
 * Parses input arguments and creates a new ReadingAddCommand object
 */
public class ReadingAddCommandParser implements Parser<ReadingsAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReadingsAddCommand
     * and returns an ReadingsAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadingsAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TANK, PREFIX_AMMONIA_LEVEL, PREFIX_PH,
                PREFIX_TEMPERATURE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TANK, PREFIX_AMMONIA_LEVEL, PREFIX_PH, PREFIX_TEMPERATURE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadingsAddCommand.MESSAGE_USAGE));
        }
        Index tankIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TANK).get());
        String currentDateTime = DateUtil.getCurrentDateTime();
        String ammoniaValue = argMultimap.getValue(PREFIX_AMMONIA_LEVEL).get();
        String pHValue = argMultimap.getValue(PREFIX_PH).get();
        String tempValue = argMultimap.getValue(PREFIX_TEMPERATURE).get();
        AmmoniaLevel ammoniaLevel = ParserUtil.parseAmmoniaLevel(ammoniaValue, currentDateTime);
        PH pH = ParserUtil.parsePH(pHValue, currentDateTime);
        Temperature temp = ParserUtil.parseTemperature(tempValue, currentDateTime);
        return new ReadingsAddCommand(ammoniaLevel, pH, temp, tankIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
