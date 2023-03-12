package seedu.fitbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GOAL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.EditCommand;
import seedu.fitbook.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_APPOINTMENT, PREFIX_WEIGHT, PREFIX_GENDER, PREFIX_GOAL, PREFIX_CALORIE, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editClientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_CALORIE).isPresent()) {
            editClientDescriptor.setCalorie(ParserUtil.parseCalorie(argMultimap.getValue(PREFIX_CALORIE).get()));
        }
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            editClientDescriptor.setWeight(ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editClientDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_GOAL).isPresent()) {
            editClientDescriptor.setGoal(ParserUtil.parseGoal(argMultimap.getValue(PREFIX_GOAL).get()));
        }
        parseAppointmentsForEdit(argMultimap.getAllValues(PREFIX_APPOINTMENT))
                .ifPresent(editClientDescriptor::setAppointments);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editClientDescriptor::setTags);
        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Appointment>> parseAppointmentsForEdit(Collection<String> appointments) throws ParseException {
        assert appointments != null;

        if (appointments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> appointmentSet = appointments.size() == 1 && appointments.contains("")
                ? Collections.emptySet() : appointments;
        return Optional.of(ParserUtil.parseAppointments(appointmentSet));
    }
}
