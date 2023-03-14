package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    private static final int INPUT_INDEX = 0;
    private static final int TAG_INDEX = 1;

    /**
     * Creates a DeleteTagCommand to parse the argument from user input
     * and delete the corresponding tag from the person.
     * 
     * @param args The input from user.
     * @return DeleteTagCommand with the Person and Tag objects correspond to input.
     * @throws ParseException If the command is in invalid format.
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }

        try {
            String[] nameKeywords = trimmedArgs.split(" ");
            Index index = ParserUtil.parseIndex(nameKeywords[INPUT_INDEX]);
            Tag tag = ParserUtil.parseTag(nameKeywords[TAG_INDEX]);

            return new DeleteTagCommand(index, tag);
        } catch (ParseException pe) {
            throw new ParseException( 
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }
    }

}
