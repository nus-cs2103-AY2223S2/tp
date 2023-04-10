package seedu.medinfo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_DISCHARGE;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.medinfo.commons.core.index.Index;
import seedu.medinfo.logic.commands.EditCommand;
import seedu.medinfo.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medinfo.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code EditCommand}
     * and returns an {@code EditCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_WARD, PREFIX_DISCHARGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditCommand.EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editPatientDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_WARD).isPresent()) {
            editPatientDescriptor.setWard(ParserUtil.parseWardName(argMultimap.getValue(PREFIX_WARD).get()));
        }
        if (argMultimap.getValue(PREFIX_DISCHARGE).isPresent()) {
            editPatientDescriptor.setDischarge(ParserUtil.parseDischarge(argMultimap.getValue(PREFIX_DISCHARGE).get()));
        }

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPatientDescriptor);
    }

}
