package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_KEYDATE;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommandNew;
import seedu.address.logic.commands.EditCommandNew.EditOpeningDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.opening.Date;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParserNew implements ParserNew<EditCommandNew> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommandNew parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_COMPANY,
                        PREFIX_EMAIL, PREFIX_STATUS, PREFIX_KEYDATE);

        Index index;

        try {
            index = ParserUtilNew.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommandNew.MESSAGE_USAGE), pe);
        }

        EditOpeningDescriptor editOpeningDescriptor = new EditOpeningDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editOpeningDescriptor.setPosition(ParserUtilNew.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editOpeningDescriptor.setCompany(ParserUtilNew.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editOpeningDescriptor.setEmail(ParserUtilNew.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editOpeningDescriptor.setStatus(ParserUtilNew.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseDatesForEdit(argMultimap.getAllValues(PREFIX_KEYDATE)).ifPresent(editOpeningDescriptor::setDates);

        if (!editOpeningDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommandNew.MESSAGE_NOT_EDITED);
        }

        return new EditCommandNew(index, editOpeningDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Date>> parseDatesForEdit(Collection<String> dates) throws ParseException {
        assert dates != null;

        if (dates.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateSet = dates.size() == 1 && dates.contains("") ? Collections.emptySet() : dates;
        return Optional.of(ParserUtilNew.parseDates(dateSet));
    }

}
