package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEventCommand object.
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_FILE, PREFIX_TUTORIAL,
                        PREFIX_CONSULTATION, PREFIX_LAB);

        Index index;
        boolean isTutorial;
        boolean isLab;
        boolean isConsultation;
        boolean isOldDate;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_INDEX,
                    EditEventCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_TUTORIAL).isPresent()
                && !argMultimap.getValue(PREFIX_LAB).isPresent()
                && !argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE));
        }

        EditEventCommand.EditEventDescriptor editEventDescriptor = new EditEventCommand.EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseTutorialName(
                    argMultimap.getValue(PREFIX_TUTORIAL).get()));
            isTutorial = true;
            isLab = false;
            isConsultation = false;
        } else if (argMultimap.getValue(PREFIX_LAB).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseLabName(argMultimap.getValue(PREFIX_LAB).get()));
            isTutorial = false;
            isLab = true;
            isConsultation = false;
        } else {
            editEventDescriptor.setEventName(ParserUtil.parseLabName(
                    argMultimap.getValue(PREFIX_CONSULTATION).get()));
            isTutorial = false;
            isLab = false;
            isConsultation = true;
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent() && argMultimap.getValue(PREFIX_LAB).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEditEventDate(argMultimap.getValue(PREFIX_DATE).get(), 2));
        } else if (argMultimap.getValue(PREFIX_DATE).isPresent()
                && argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEditEventDate(argMultimap.getValue(PREFIX_DATE).get(), 1));
        } else if (argMultimap.getValue(PREFIX_DATE).isPresent()
                && argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEditEventDate(argMultimap.getValue(PREFIX_DATE).get(), 1));
        }

        isOldDate = argMultimap.getValue(PREFIX_DATE).isEmpty();

        if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
            editEventDescriptor.setAttachments(ParserUtil.parseEventFile(argMultimap.getValue(PREFIX_FILE).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor, isTutorial, isLab, isConsultation, isOldDate);
    }
}
