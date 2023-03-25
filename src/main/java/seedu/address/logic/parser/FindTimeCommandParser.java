package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindTimeCommand;
import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

public class FindTimeCommandParser implements Parser<FindTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTimeCommand
     * and returns a FindTimeCommand object for execution.
     *
     * @param arguments String representing user input
     * @throws ParseException if {@code arguments} does not conform the expected format
     */
    @Override
    public FindTimeCommand parse(String arguments) throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_STARTDATETIME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTimeCommand.MESSAGE_USAGE), pe);
        }

        // TODO: Change parsing function
        // TODO: Refactor the following lines, especially date parsing and Optional
        boolean isDateSpecified = argumentMultimap.getValue(PREFIX_STARTDATETIME).isPresent();
        LocalDate date;
        if (isDateSpecified) {
            date = LocalDateTime.parse(argumentMultimap.getValue(PREFIX_STARTDATETIME).get()).toLocalDate();
        } else {
            date = LocalDate.now();
        }
        return new FindTimeCommand(index, date);
    }
}
