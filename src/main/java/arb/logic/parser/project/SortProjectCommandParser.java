package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.ArgumentMultimap.areAllPrefixesPresent;
import static arb.logic.parser.CliSyntax.PREFIX_OPTION;

import arb.commons.core.sorting.ProjectSortingOption;
import arb.logic.commands.project.SortProjectCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortProjectCommand object
 */
public class SortProjectCommandParser implements Parser<SortProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortProjectCommand
     * and returns a SortProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OPTION);

        if (!areAllPrefixesPresent(argMultimap, PREFIX_OPTION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
        }

        ProjectSortingOption sorter = ParserUtil.parseSortingOption(argMultimap.getValue(PREFIX_OPTION).get());

        return new SortProjectCommand(sorter);
    }

}
