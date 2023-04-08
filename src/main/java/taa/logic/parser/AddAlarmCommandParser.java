package taa.logic.parser;

import static taa.logic.parser.CliSyntax.PREFIX_COMMENT;
import static taa.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import taa.commons.core.Messages;
import taa.logic.commands.AddAlarmCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.alarm.Alarm;

/**
 * Defines the parser for the add alarm command.
 */
public class AddAlarmCommandParser {

    /**
     * Parses the add alarm command and returns an AddAlarmCommand instance after execution
     * @param string user input to be parsed
     * @return the parsed AddAlarmCommand instance
     * @throws ParseException for invalid user input
     */
    public AddAlarmCommand parse(String string) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(string, PREFIX_TIME, PREFIX_COMMENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_TIME)
                || !arePrefixesPresent(argMultimap, PREFIX_COMMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAlarmCommand.MESSAGE_USAGE));
        }
        int minutes;
        Object obj = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        if (obj instanceof Integer && (int) obj > 0) {
            minutes = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        } else {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAlarmCommand.MESSAGE_USAGE));
        }
        String comment = argMultimap.getValue(PREFIX_COMMENT).get();

        Alarm alarm = new Alarm(minutes, comment);

        return new AddAlarmCommand(alarm);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
