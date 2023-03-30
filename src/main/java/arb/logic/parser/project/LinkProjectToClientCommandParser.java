package arb.logic.parser.project;

import arb.commons.core.index.Index;
import arb.logic.commands.project.LinkProjectToClientCommand;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkProjectToClientCommand object
 */
public class LinkProjectToClientCommandParser implements Parser<LinkProjectToClientCommand> {
    /**
     * Parses the given {@code String} or arguments in the context of the LinkProjectToClientCommand
     * and returns a LinkProjectToClientCommand for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public LinkProjectToClientCommand parse(String args) throws ParseException {
        try {
            String[] wordsInArgs = args.trim().split(" ");
            Index index = ParserUtil.parseIndexWithZero(wordsInArgs[0]);
            return new LinkProjectToClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    "Invalid index! " + LinkProjectToClientCommand.MESSAGE_USAGE, pe);
        }
    }
}
