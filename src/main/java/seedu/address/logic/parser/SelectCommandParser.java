package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input and creates a new {@code SelectCommand}.
 */
public class SelectCommandParser implements Parser<SelectCommand> {
    @Override
    public SelectCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap multimap = ArgumentTokenizer.tokenize(userInput);
        Index index;
        try {
            index = ParserUtil.parseIndex(multimap.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectCommand.MESSAGE_USAGE), e);
        }
        return new SelectCommand(index);
    }
}
