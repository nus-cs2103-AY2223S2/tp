package seedu.loyaltylift.logic.parser;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.logic.commands.SetPointsCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.customer.Points;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;

public class SetPointsCommandParser implements Parser<SetPointsCommand> {
    public SetPointsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POINTS);

        Index index;
        Integer points;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            points = Integer.valueOf(argMultimap.getValue(PREFIX_POINTS).orElse(""));
        } catch (IllegalValueException | NumberFormatException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetPointsCommand.MESSAGE_USAGE), ive);
        }

        return new SetPointsCommand(index, new Points(points));
    }
}
