package seedu.calidr.logic.parser;

import static seedu.calidr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.calidr.logic.commands.PageCommand;
import seedu.calidr.logic.parser.exceptions.ParseException;
import seedu.calidr.model.PageType;

/**
 * Parses input arguments and creates a new PageCommand object
 */
public class PageCommandParser implements Parser<PageCommand> {
    @Override
    public PageCommand parse(String args) throws ParseException {
        PageType pageType;
        try {
            pageType = ParserUtil.parsePageType(args);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PageCommand.MESSAGE_USAGE));
        }
        return new PageCommand(pageType);
    }
}
