package seedu.powercards.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand.EditCardDescriptor;
import seedu.powercards.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCardCommand object
 */
public class EditCardCommandParser implements Parser<EditCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCardCommand
     * and returns an EditCardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCardCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE), pe);
        }

        EditCardCommand.EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editCardDescriptor.setQuestion(ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            editCardDescriptor.setAnswer(ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get()));
        }

        // treated the same way as question and answer field, but allows null value
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editCardDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (!editCardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCardCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCardCommand(index, editCardDescriptor);
    }

}
