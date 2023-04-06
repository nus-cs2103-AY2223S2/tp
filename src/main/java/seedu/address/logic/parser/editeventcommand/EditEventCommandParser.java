package seedu.address.logic.parser.editeventcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEventCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);

        EventDescriptor editEventDescriptor = this.parseForTags(args);

        return new EditEventCommand(editEventDescriptor);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventDescriptor for edit commands to use.
     * @throws ParseException if the user input does not conform the expected format
     */
    private EventDescriptor parseForTags(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_DESCRIPTION, PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME, PREFIX_RECURRENCE);

        Index index;
        EventDescriptor editEventDescriptor = new EventDescriptor();

        try {
            index = this.parseIndex(argMultimap.getPreamble());
            editEventDescriptor.setIndex(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, this.getMessageUsage()), pe
            );
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editEventDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE_TIME).isPresent()) {
            editEventDescriptor.setStartDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE_TIME).isPresent()) {
            editEventDescriptor.setEndDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_RECURRENCE).isPresent()) {
            editEventDescriptor.setRecurrence(
                    ParserUtil.parseRecurrence(argMultimap.getValue(PREFIX_RECURRENCE).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return editEventDescriptor;
    }

    private Index parseIndex(String index) throws ParseException {
        return ParserUtil.parseIndex(index);
    }

    private String getMessageUsage() {
        return EditEventCommand.MESSAGE_USAGE;
    }
}
