package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEARN;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UnlearnCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for the {@link UnlearnCommand} command
 */
public class UnlearnCommandParser implements Parser<UnlearnCommand> {
    @Override
    public UnlearnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LEARN);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlearnCommand.MESSAGE_USAGE), ive);
        }

        String lesson = argMultimap.getValue(PREFIX_LEARN).orElse("");

        return new UnlearnCommand(index, lesson);
    }
}
