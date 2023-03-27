package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The type View command parser.
 */
public class ViewCommandParser implements Parser<ViewFileCommand> {
    @Override
    public ViewFileCommand parse(String userInput) throws ParseException {
        try {
            String[] tokens = userInput.trim().split("\\s+");
            if (tokens.length != 2) {
                throw new ParseException("Invalid number of arguments for view command.");
            }
            Index index = ParserUtil.parseIndex(tokens[0]);
            int fileNumber = Integer.parseInt(tokens[1]);
            return new ViewFileCommand(index, fileNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewFileCommand.MESSAGE_USAGE), pe);
        } catch (NumberFormatException nfe) {
            throw new ParseException("Invalid file number: " + nfe.getMessage(), nfe);
        }
    }
}
