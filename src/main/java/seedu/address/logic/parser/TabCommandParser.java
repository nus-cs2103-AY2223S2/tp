package seedu.address.logic.parser;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates a new {@code TabCommand}.
 */
public class TabCommandParser implements Parser<TabCommand> {
    @Override
    public TabCommand parse(String userInput) throws ParseException {
        Objects.requireNonNull(userInput);

        ArgumentMultimap multimap = ArgumentTokenizer.tokenize(userInput);
        Index index;
        try {
            index = ParserUtil.parseIndex(multimap.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    TabCommand.MESSAGE_USAGE), e);
        }
        return new TabCommand(index);
    }
}
