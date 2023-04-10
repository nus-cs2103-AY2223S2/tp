package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new {@code ViewCommand} object
 */
public class ViewCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);
        List<Index> indexList = new ArrayList<>();

        String[] parsedIndexList = args.trim().split("\\s+");
        for (String i: parsedIndexList) {
            System.out.println(i);
            try {
                indexList.add(ParserUtil.parseIndex(i));
            } catch (IllegalValueException ive) {
                if (Integer.valueOf(i) <= 0) {
                    throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
                } else {
                    throw new ParseException(String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_ARGUMENTS), ive);
                }
            }
        }

        return new ViewCommand(indexList);
    }
}
