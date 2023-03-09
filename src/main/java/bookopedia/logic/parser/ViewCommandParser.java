package bookopedia.logic.parser;

import bookopedia.commons.core.index.Index;
import bookopedia.commons.exceptions.IllegalValueException;
import bookopedia.logic.commands.ViewCommand;
import bookopedia.logic.parser.exceptions.ParseException;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static java.util.Objects.requireNonNull;

public class ViewCommandParser implements Parser<ViewCommand> {
    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(userInput);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE));
        }
        return new ViewCommand(index);
    }
}
