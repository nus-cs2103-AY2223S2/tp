package seedu.address.logic.parser;

import seedu.address.logic.commands.ListRegionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Region.Regions;

/**
 * Parses input arguments and creates a new ListRegionCommand object
 */
public class ListRegionCommandParser implements Parser<ListRegionCommand> {

    @Override
    public ListRegionCommand parse(String userInput) throws ParseException {
        Regions inputRegion = ParserUtil.parseRegion(userInput);
        return new ListRegionCommand(inputRegion);
    }
}
