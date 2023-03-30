package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new {@code ViewCommand} object
 */
public class ExportCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        List<Index> indexList = new ArrayList<>();

        String[] parsedIndexList = args.trim().split("\\s+");
        for (String i: parsedIndexList) {
            System.out.println(i);
            try {
                indexList.add(ParserUtil.parseIndex(i));
            } catch (IllegalValueException ive) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_ARGUMENTS), ive);
            }
        }

        return new ExportCommand(indexList);
    }
}
