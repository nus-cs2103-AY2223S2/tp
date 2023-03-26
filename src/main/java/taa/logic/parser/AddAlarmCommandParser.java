package taa.logic.parser;

import taa.commons.core.Messages;
import taa.logic.commands.AddAlarmCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.Alarm;

import java.util.stream.Stream;

import static taa.logic.parser.CliSyntax.*;

public class AddAlarmCommandParser {

    public AddAlarmCommand parse(String string) throws ParseException{
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(string, PREFIX_TIME, PREFIX_COMMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ""));
        }

        int minutes = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        String comment = argMultimap.getValue(PREFIX_COMMENT).get();

        Alarm alarm = new Alarm(minutes, comment);

        return new AddAlarmCommand(alarm);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
