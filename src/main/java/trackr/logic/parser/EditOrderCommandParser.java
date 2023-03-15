package trackr.logic.parser;


import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.*;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditOrderCommand;
import trackr.logic.commands.EditOrderCommand.EditOrderDescriptor;
import trackr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditOrderCommand object
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditOrderCommand
     * and returns an EditOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNAME, PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE), pe);
        }

        EditOrderDescriptor editOrderDescriptor = new EditOrderDescriptor();
        if (argMultimap.getValue(PREFIX_ORDERNAME).isPresent()) {
            editOrderDescriptor.setOrderName(
                    ParserUtil.parseOrderName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editOrderDescriptor.setOrderDeadline(
                    ParserUtil.parseOrderDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editOrderDescriptor.setOrderStatus(
                    ParserUtil.parseOrderStatus(argMultimap.getValue(PREFIX_STATUS)));
        }

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }

}
