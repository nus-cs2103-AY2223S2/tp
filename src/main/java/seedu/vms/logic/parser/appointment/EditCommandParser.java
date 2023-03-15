package seedu.vms.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.appointment.EditCommand;
import seedu.vms.logic.commands.appointment.EditCommand.EditAppointmentDescriptor;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            editAppointmentDescriptor.setPatient(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
            editAppointmentDescriptor.setStartTime(ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_ENDTIME).isPresent()) {
            editAppointmentDescriptor.setEndTime(ParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_VACCINATION).isPresent()) {
            editAppointmentDescriptor.setVaccine(ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_VACCINATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editAppointmentDescriptor);
    }
}
