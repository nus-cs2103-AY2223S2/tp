package arb.logic.parser.project;

import arb.commons.core.index.Index;
import arb.logic.commands.project.MarkProjectCommand;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MarkProjectCommandParser implements Parser<MarkProjectCommand> {
    public MarkProjectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkProjectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkProjectCommand.MESSAGE_USAGE), pe);
        }
    }
}
