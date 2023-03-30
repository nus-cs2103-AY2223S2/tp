package seedu.ultron.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.EditCommand;
import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.opening.Keydate;


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
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_COMPANY,
                        PREFIX_EMAIL, PREFIX_STATUS, PREFIX_KEYDATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditOpeningDescriptor editOpeningDescriptor = new EditOpeningDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editOpeningDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editOpeningDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editOpeningDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editOpeningDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseKeydatesForEdit(argMultimap.getAllValues(PREFIX_KEYDATE)).ifPresent(editOpeningDescriptor::setKeydates);

        if (!editOpeningDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editOpeningDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<List<Keydate>> parseKeydatesForEdit(Collection<String> keydates) throws ParseException {
        assert keydates != null;

        if (keydates.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> keydateList = keydates.size() == 1
                && keydates.contains("") ? Collections.emptyList() : keydates;
        return Optional.of(ParserUtil.parseKeydates(keydateList));
    }

}
