package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;

import seedu.address.logic.commands.ListRegionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Region.Regions;

/**
 * Parses input arguments and creates a new ListRegionCommand object
 */
public class ListRegionCommandParser implements Parser<ListRegionCommand> {

    @Override
    public ListRegionCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_ARGUMENTS, ListRegionCommand.MESSAGE_USAGE)
            );
        }

        Regions inputRegion = ParserUtil.parseRegion(userInput);
        return new ListRegionCommand(inputRegion);
    }

}
